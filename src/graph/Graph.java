package graph;

import java.util.*;

public class Graph {


    private List<Node> nodes;

    private int[][] matrix;

    private int[][] A;
    private String[][] P;


    private final static String ORIGIN = "GREEN DISTRIBUTION";
    private final static int DEFAULT_LIMIT = 5;


    // standard constructor, getters, setters
    public Graph(int size) {
        this.matrix = new int[size][size];
        this.nodes = new ArrayList<>();

    }

    public void addNode(String label) {
        this.nodes.add(new Node(label));
    }

    public void addEdge(String label1, String label2, int distancia) {
        if (this.existEdge(label1, label2)) {
            return;
        }
        int x = this.getNodePos(label1);
        int y = this.getNodePos(label2);
        this.matrix[x][y] = distancia;
        this.matrix[y][x] = distancia;

    }

    public int getDistance(String label1, String label2) {
        int x = this.getNodePos(label1);
        int y = this.getNodePos(label2);
        return this.matrix[x][y];
    }



    public List<Node> getNodes() {
        return new ArrayList<>(this.nodes);
    }

    private int getNodePos(String label) {
        int count = 0;
        for (Node node : this.nodes) {
            if (node.is(label)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    private boolean existEdge(String label1, String label2) {
        int x = this.getNodePos(label1);
        int y = this.getNodePos(label2);
        return this.matrix[x][y] > 0;
    }

    public int[] dijkstra(String label) {

        int[] d = innitD(label);
        String[] P = innitP(label);

        boolean[] s = new boolean[this.getSize()];
        int w = getPivot(d, s);

        while (w != -1) {
            s[w] = true;
            for (int i = 0; i < this.getSize(); i++) {
                if (!s[i] && matrix[w][i] > 0) {
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
        int size = this.getNodes().size();
        int[] d = new int[size];
        for (int i = 0; i < size; i++) {
            if (i == this.getNodePos(label)) {
                d[i] = 0;
            } else if (this.existEdge(label, this.getNodes().get(i).getLabel())) {
                d[i] = this.getDistance(label, this.getNodes().get(i).getLabel());
            } else {
                d[i] = Integer.MAX_VALUE;
            }
        }

        return d;
    }

    private String[] innitP(String label) {
        int size = this.getNodes().size();
        String[] P = new String[size];

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
                minValue = d[i];
            }
        }

        return minPos;

    }

    public int floyd() {
        if (this.getSize() == 0) return -1;
        A = innitA();
        P = innitP();

        for (int k = 0; k < this.getSize(); k++)
            for (int i = 0; i < this.getSize(); i++)
                for (int j = 0; j < this.getSize(); j++)
                    if (A[i][k] + A[k][j] < A[i][j] && A[i][k] != Integer.MAX_VALUE && A[k][j] != Integer.MAX_VALUE) {
                        A[i][j] = A[i][k] + A[k][j];
                        P[i][j] = this.nodes.get(k).getLabel();
                    }

        return 0;

    }

    public List<String> sendEstafeta(String destin) {
        List<String> path = path(ORIGIN, destin);
        path.addAll(path(destin, ORIGIN));
        return path;
    }

    public List<String> sendMultipleEstefeta(List<String> destins) {
        List<String> path = new ArrayList<>();
        String last = ORIGIN;
        while (destins.size() > 0) {
            String next = nextLocation(ORIGIN, destins);
            destins.remove(next);
            path.addAll(path(last, next));
            last = next;

        }


        return path;
    }

    private String nextLocation(String origen, List<String> left) {
        String node = "";
        int value = Integer.MAX_VALUE;
        for (String str : left) {
            int min = minCostPath(origen, str);
            if (min < value) {
                value = min;
                node = str;
            }
        }
        return node;
    }

    public int minCostPath(String origen, String destino) {
        int value = A[this.getNodePos(origen)][this.getNodePos(destino)];
        if (value > 0 && value != Integer.MAX_VALUE) {
            return value;
        }
        return -1;

    }

    private List<String> path(String originLabel, String destinLabel) {
        List<String> path = new ArrayList<>();

        floyd();
        if (P[this.getNodePos(originLabel)][this.getNodePos(destinLabel)] != null) {
            path.add(originLabel);
            path.addAll(pathFloydIntermedio(originLabel, destinLabel));

        } else {
            path.add(originLabel);
            path.add("INFINITY");
            path.add(destinLabel);
        }
        return path;
    }

    private List<String> pathFloydIntermedio(String origen, String destino) {
        String k1 = P[this.getNodePos(origen)][this.getNodePos(destino)];

        if (A[getNodePos(origen)][getNodePos(destino)] == Integer.MAX_VALUE || A[getNodePos(origen)][getNodePos(destino)] <= 0) {
            return new ArrayList<String>(List.of(new String[]{"INFINITY"}));
        }
        if (A[getNodePos(origen)][getNodePos(destino)] > 0 && (k1 != null && !origen.equals(nodes.get(this.getNodePos(k1)).getLabel()))) {
            List<String> toret = new ArrayList<>();
            toret.addAll(pathFloydIntermedio(origen, nodes.get(this.getNodePos(k1)).getLabel()));
            toret.addAll(pathFloydIntermedio(nodes.get(this.getNodePos(k1)).getLabel(), destino));
            return toret;
        } else {
            return new ArrayList<String>(List.of(new String[]{destino}));

        }

    }

    private String[][] innitP() {
        String[][] p = new String[this.getSize()][this.getSize()];
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (existEdge(this.nodes.get(i).getLabel(), this.nodes.get(j).getLabel())) {
                    p[i][j] = this.nodes.get(i).getLabel();
                } else {
                    p[i][j] = null;
                }
            }
        }
        return p;
    }

    private int[][] innitA() {
        int[][] a = new int[this.getSize()][this.getSize()];
        for (int i = 0; i < this.getSize(); i++) {
            for (int j = 0; j < this.getSize(); j++) {
                if (this.nodes.get(i).equals(this.nodes.get(j))) {
                    a[i][j] = 0;
                } else if (existEdge(this.nodes.get(i).getLabel(), this.nodes.get(j).getLabel())) {
                    a[i][j] = matrix[i][j];
                } else {
                    a[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        this.A = a;
        return a;
    }

    public int getSize() {
        return this.getNodes().size();
    }

    public String[][] getP() {
        return P;
    }


    public int BFS(String origin, String destin) {
        int current = this.getNodePos(origin);
        int finish = this.getNodePos(destin);
        int distance = -1;
        boolean visited[] = initVisitedForSearch(origin);

        int[] distances = this.innitD(origin);
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[current] = true;
        queue.add(current);

        while (!queue.isEmpty()) {
            // Dequeue a vertex from queue and print it
            current = queue.poll();

            visited[current] = true;

            for (int n = 0; n < this.getSize(); n++) {
                if (!visited[n] && distances[n] > 0) {
                    if (distances[n] >= distances[current] + this.matrix[current][n] && this.matrix[current][n] >0) {
                        distances[n] = distances[current] + this.matrix[current][n];
                        queue.add(n);
                    }
                }

            }
        }
        return distances[finish];
    }


    public int DFS(String origin, String destin) {
        boolean[] d = initVisitedForSearch(origin);
        return DFSUtil(origin, destin, this.getNodePos(origin), d, 0);

    }

    public int greedyfirst(String origin, String destination) {
        int  cost=100000;
        System.out.println("buscando desde " + origin + " " + destination);
        int start = getNodePos(origin);
        int end = getNodePos(destination);
        int nextRua = -1;
        boolean visited[] = new boolean[this.getSize()];//almacena las calles que han sido visitada
        visited[start]=true;
        int cont = 0;
        //obtenemos una lista de nodos adyacentes
        List<Node> adyacentes = new ArrayList<>();
        for (int j=0;j<getSize();j++){
            if (matrix[start][j]!=0||matrix[j][start]!=0){
                adyacentes.add(this.nodes.get(j));
            }
        }

        for (Node n:adyacentes){
            if (n.getLabel().equals(destination)){ //uno de los nodos destino es adyacente
                return getDistance(origin,destination);
            }
        }
        return greedyfirstutil(origin,destination,0,0,visited);
    }

    private int greedyfirstutil(String origen, String dest, int total, int veces, boolean[]visited){
        List<Node> adyacentes = new ArrayList<>();
        for (int j=0;j<getSize();j++){
            if (matrix[getNodePos(origen)][j]!=0 && visited[j]==false){
                adyacentes.add(this.nodes.get(j));
                System.out.println("los nodos adyacentes de "+ origen + " son " + this.nodes.get(j).getLabel());
            }
        }
        //sino hay que seguir buscando segun el camino mas corto
        Node siguiente = null;
        int mincost=10000;
        for (Node n:adyacentes){
            int d = getDistance(origen,n.getLabel());
            if (n.getLabel().equals(dest)){
                return total+d;

            } else if (d<mincost&&d!=0) {
                siguiente = n;
                mincost = d;
            }
        }
        if (veces>=20){
            return -1;
        }
        veces++;
        if (siguiente!=null){
            visited[getNodePos(siguiente.getLabel())]=true;
            return greedyfirstutil(siguiente.getLabel(),dest,total+mincost,veces,visited);
        }
        return -1;

    }




    // A function used by DFS
    private int DFSUtil(String origin, String destin, int current, boolean visited[], int distance) {
        if (this.nodes.get(current).is(destin)) return distance;
        // Mark the current node as visited and print it
        visited[current] = true;
        // Recur for all the vertices adjacent to this
        // vertex
        for (int n = 0; n < this.getSize(); n++) {
            if (!visited[n]) return DFSUtil(origin, destin, n, visited, distance + this.matrix[current][n]);
        }
        return -1;
    }


    public int DFS(String origin, String destin, int limit) {
        boolean[] d = initVisitedForSearch(origin);
        if (limit > 0)
            return DFSUtil(origin, destin, this.getNodePos(origin), d, 0, limit);
        return DFSUtil(origin, destin, this.getNodePos(origin), d, 0, DEFAULT_LIMIT);

    }


    private int DFSUtil(String origin, String destin, int current, boolean visited[], int distance, int limit) {
        if (limit > 0) {
            if (this.nodes.get(current).is(destin)) return distance;
            // Mark the current node as visited and print it
            visited[current] = true;
            // Recur for all the vertices adjacent to this
            // vertex
            Iterator<Integer> i = Arrays.stream(matrix[current]).iterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {

                }
                return DFSUtil(origin, destin, n, visited, distance + this.matrix[current][n], limit - 1);
            }
        }
        return 0;
    }

    private boolean[] initVisitedForSearch(String origin) {
        boolean[] d = new boolean[this.getSize()];
        for (int i = 0; i < this.getSize(); i++) {
            if (i == this.getNodePos(origin)) {
                d[i] = true;
            }
        }
        return d;
    }

    // Gulosa/estrela
    // Calcula tiempo de entrega
    public double calculaTiempo(double kg, String transporte, double distancia){
    	double tiempo = 0;
    	double velocidad = 0;
    	int numKg = (int)kg;
		for(int i=0; i<numKg; i++) {
	    	if(transporte.equals("bicicleta")) {
	    		velocidad = velocidad - 0.7;
	    	}
	    	
	    	if(transporte.equals("coche")) {
	    		velocidad = velocidad - 0.5;
	    		
	    	}
	    	
	    	if(transporte.equals("moto")) {
	    		velocidad = velocidad - 0.1;
	    	}
		}
		
		tiempo = distancia * (1/velocidad);
    	
    	return tiempo;
    	
    }


    public void print() {
        for (int i = 0; i<this.matrix.length;i++){
            for(int j=0;j<this.matrix.length;j++){
                System.out.print(this.matrix[i][j]+ "\t");
            }
            System.out.println();
        }

    }
}
