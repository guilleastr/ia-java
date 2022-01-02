package graph;

import java.util.*;

public class Graph {


    private List<Node> nodes;

    private int[][] matrix;



    // standard constructor, getters, setters
    public Graph(int size){
        this.matrix=new int[size][size];
        this.nodes=new ArrayList<>();
    }

    public void addNode(String label) {
        this.nodes.add(new Node(label));
    }


    public void addEdge(String label1, String label2, int distancia) {
        int x=this.getNodePos(label1);
        int y= this.getNodePos(label2);
        this.matrix[x][y]=distancia;
        this.matrix[y][x]=distancia;

    }

    public int getDistance(String label1, String label2){
        int x=this.getNodePos(label1);
        int y= this.getNodePos(label2);
        return this.matrix[x][y];
    }


    public List<Node> getNodes(){
        return new ArrayList<>(this.nodes);
    }

   private int getNodePos(String label){
        int count=0;
        for(Node node: this.nodes){
            if(node.is(label)){
                return count;
            }
            count++;
        }
        return -1;
   }


    public int[] dijkstra(String label) {

        int[] d = innitD(label);
        String[] P = innitP(label);

        boolean[] s = new boolean[this.getSize()];
        int w = getPivot(d, s);
        while (w != -1) {
            s[w] = true;
            for (int i = 0; i < this.getSize(); i++) {
                if (!s[i] && matrix[w][i]>0) {
                    if (d[w] + matrix[w][i] < d[i]) {
                        d[i] = d[w] + matrix[w][i];
                        P[i] = this.getNodes().get(w).getLabel();
                    }
                }
            }
            w = getPivot(d, s);
        }

        return d;

    }

    private int[] innitD(String label) {
        int size=this.getNodes().size();
        int[] d = new int[size];
        for (int i = 0; i < size; i++) {
            if (i == this.getNodePos(label)) {
                d[i] = 0;
            } else if (this.existEdge(label, this.getNodes().get(i).getLabel())) {
                d[i] = this.getDistance(label  , this.getNodes().get(i).getLabel());
            } else {
                d[i] = Integer.MAX_VALUE;
            }
        }

        return d;
    }

    private String[] innitP(String label) {
        int size= this.getNodes().size();
        String[] P =  new String[size];

        for (int i = 0; i < size; i++) {
            if (this.existEdge(label, this.getNodes().get(i).getLabel()) || this.getNodePos(label) == i) {
                P[i] = label;
            }
        }

        return P;
    }

    private int getPivot(int[] d, boolean[] s) {

        int minPos = -1;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < this.getSize(); i++) {
            if (d[i] < minValue && !s[i]) {
                minPos = i;
                minValue=d[i];
            }
        }

        return minPos;

    }

    private int getSize(){
        return this.getNodes().size();
    }


    private boolean existEdge(String label1, String label2) {
        int x=this.getNodePos(label1);
        int y= this.getNodePos(label2);
        return this.matrix[x][y]>0;
    }


}
