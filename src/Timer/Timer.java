package Timer;

import graph.Graph;
import transport.Transport;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import Star.NodoEstrela;
import Star.Star;

public class Timer {

    private final static int DEFAULT_GRAPH_SIZE = 9;

    private final static int DEFAULT_DFS_LIMIT=5;

    private Graph graph;

    private List<DataRecord> data;


    public Timer() {
        data = new ArrayList<DataRecord>();
    }

    public void run() {
        this.initGraph();
        this.testBFS();
        this.testDFS();
        this.testGreedyFirst();
        this.testDFSIterative();

        this.printData();
        this.saveData();
        
        this.estrella();

    }


    public void run(int size) {
        initGraph(size);
        this.testBFS();
        this.testDFS();
        this.testGreedyFirst();
        this.testDFSIterative();
        this.printData();

        this.saveData();
        this.estrella();


    }

    private void initGraph(int size) {
        this.graph = new Graph(size);
        for (int i = 0; i < size; i++) {
            graph.addNode(String.valueOf(i));
        }


        for (int i = 0; i < size; i++) {
            graph.addEdge(String.valueOf(i), String.valueOf(new Random().nextInt(size)), new Random().nextInt(50));
            graph.addEdge(String.valueOf(i), String.valueOf(new Random().nextInt(size)), new Random().nextInt(50));
            graph.addEdge(String.valueOf(i), String.valueOf(new Random().nextInt(size)), new Random().nextInt(50));
        }

        graph.floyd();

        System.out.println("Graph inizialized");


    }

    private void printData() {
        List<DataRecord> bfs= new ArrayList<DataRecord>();
        List<DataRecord> dfs= new ArrayList<DataRecord>();
        List<DataRecord> dfsi= new ArrayList<DataRecord>();
        for (DataRecord dataR : data) {
            switch (dataR.getAlgorithm()){
                case "BFS":
                    bfs.add(dataR);
                case "DFS":
                    dfs.add(dataR);
                case "DFS-Iterative":
                    dfsi.add(dataR);

            }
            System.out.println(dataR.toString());
        }

        int correct=0;
        for (DataRecord dataR : dfs) {
            if(dataR.isBestAnswer()){
                correct++;
            }
        }
        int correcti=0;
        for (DataRecord dataR : dfsi) {
            if(dataR.isBestAnswer()){
                correcti++;
            }
        }
        System.out.println("DFS: RATE OF SUCCESS: "+ ((double) correct/dfs.size())*100+"%");
        System.out.println("DFS iterative: RATE OF SUCCESS: "+ ((double) correcti/dfsi.size())*100+"%");


    }

    private void saveData(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("data.csv", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (DataRecord dataR : data) {
            writer.println(dataR.toExcel());
        }
        writer.close();
    }


    private void initGraph() {
        this.graph = new Graph(DEFAULT_GRAPH_SIZE);

        String r1 = "AVDA BRAGA";
        String r2 = "AVDA PARIS";
        String r3 = "AVDA LIBERDADE";
        String r4 = "RUA FONTE";
        String r5 = "RUA PORTUGAL";
        String r6 = "AVDA SANTANDER";
        String r7 = "RUA DE MINHO";
        String r8 = "RUA ESPANHA";
        String r9 = "GREEN DISTRIBUTION";

        graph.addNode(r1);
        graph.addNode(r2);
        graph.addNode(r3);
        graph.addNode(r4);
        graph.addNode(r5);
        graph.addNode(r6);
        graph.addNode(r7);
        graph.addNode(r8);
        graph.addNode(r9);

        graph.addEdge(r1, r2, 12);
        graph.addEdge(r1, r3, 20);
        graph.addEdge(r2, r5, 30);
        graph.addEdge(r2, r4, 22);
        graph.addEdge(r4, r6, 10);
        graph.addEdge(r4, r3, 45);
        graph.addEdge(r5, r6, 7);
        graph.addEdge(r6, r7, 9);
        graph.addEdge(r7, r8, 18);
        graph.addEdge(r1, r9, 18);
        graph.addEdge(r9, r6, 7);
        graph.print();
        graph.floyd();
    }
    
    private void estrella() {
 
        long start, end;
    	NodoEstrela head = new NodoEstrela("GREEN DISTRIBUTION", 5);
        head.g = 0;

        NodoEstrela n2 = new NodoEstrela("AVDA PARIS",4);
        NodoEstrela n3 = new NodoEstrela("AVDA LIBERDADE",4);
        NodoEstrela n4 = new NodoEstrela("RUA FONTE",3);
        NodoEstrela n5 = new NodoEstrela("RUA PORTUGAL", 3);
        NodoEstrela n6 = new NodoEstrela("AVDA SANTANDER", 2);
        NodoEstrela n7 = new NodoEstrela("RUA DE MINHO", 1);
        NodoEstrela target = new NodoEstrela("RUA ESPANHA", 0);
        NodoEstrela n9 = new NodoEstrela("" + "AVDA BRAGA", 4);

        head.addBranch(12, n2);
        head.addBranch(20, n3);

        n2.addBranch(30, n5);
        n2.addBranch(22, n4);

        n4.addBranch(10, n6);
        n4.addBranch(45, n3);

        n5.addBranch(7, n6);

        n6.addBranch( 9, n7);
        n7.addBranch(18, target);

        head.addBranch(18, n9);
        n9.addBranch(7, n6);

        start = System.currentTimeMillis();
        NodoEstrela res = Star.aStar(head, target);
        end = System.currentTimeMillis();

        System.out.println("ESTRELLA");

        long time = end - start;

        Star.printPath(res, time, head.getLabel(), target.getLabel());
   
        
    }
    
    private void testDFS() {
        long start, end;
        final String type = "DFS";

        System.out.println(type);

        for (int i = 0; i < graph.getSize(); i++) {
            for (int j = 0; j < graph.getSize(); j++) {
                if(i==j)continue;
                String label1= graph.getNodes().get(i).getLabel();
                String label2= graph.getNodes().get(j).getLabel();

                start = System.nanoTime();
                int distance = graph.DFS(label1,label2);
                end = System.nanoTime();

                addData(start, end, type, label1, label2, distance);
            }
        }
    }

    private void testDFSIterative() {
        long start, end;
        final String type = "DFS-Iterative";

        System.out.println(type);

        for (int i = 0; i < graph.getSize(); i++) {
            for (int j = 0; j < graph.getSize(); j++) {
                if(i==j)continue;
                String label1= graph.getNodes().get(i).getLabel();
                String label2= graph.getNodes().get(j).getLabel();

                start = System.nanoTime();
                int distance = graph.DFS(label1,label2, DEFAULT_DFS_LIMIT);
                end = System.nanoTime();

                addData(start, end, type, label1, label2, distance);
            }
        }
    }


    private void testBFS() {

        long start, end;
        final String type = "BFS";

        System.out.println(type);

        for (int i = 0; i < graph.getSize(); i++) {
            for (int j = 0; j < graph.getSize(); j++) {
                if(i==j)continue;

                String label1= graph.getNodes().get(i).getLabel();
                String label2= graph.getNodes().get(j).getLabel();

                start = System.nanoTime();
                int distance = graph.BFS(label1,label2);
                end = System.nanoTime();
                addData(start, end, type, label1, label2, distance);
            }
        }
    }

    private void testGreedyFirst() {
        long start, end;
        final String type = "GREEDY-FIRST";

        System.out.println(type);

        for (int i = 0; i < graph.getSize(); i++) {
            for (int j = 0; j < graph.getSize(); j++) {
                if(i==j)continue;

                String label1= graph.getNodes().get(i).getLabel();
                String label2= graph.getNodes().get(j).getLabel();

                start = System.currentTimeMillis();
                int distance = graph.greedyfirst(label1,label2);
                end = System.currentTimeMillis();
                addData(start, end, type, label1, label2, distance);



            }
        }
    }

    private void addData(long start, long end, String type, String label1, String label2, int distance) {
        this.data.add(new DataRecord(type, (end- start)/1000, distance,  graph.minCostPath(label1, label2), label1,label2));
    }

    /*
    // Generamos los transportes
    Transport bicicleta = new Transport("bicicleta", 25.0, 50.0); 
    Transport moto = new Transport("moto", 75.0, 90.0);
    Transport coche = new Transport("coche", 300.0, 100.0);
    
    // Tiempos de entrega ---falta la distancia
    double tiempoBicicleta = graph.calculaTiempo(bicicleta.getKg(), bicicleta.getNameTransport(), graph.getDistance(null, null));
    double tiempoMoto = graph.calculaTiempo(moto.getKg(), moto.getNameTransport(), graph.getDistance(null, null));
    double tiempoCoche = graph.calculaTiempo(coche.getKg(), coche.getNameTransport(), graph.getDistance(null, null));*/

}
