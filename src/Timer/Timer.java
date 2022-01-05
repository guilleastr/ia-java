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
import delivery.Entrega;
import delivery.Pedido;

public class Timer {

    private final static int DEFAULT_GRAPH_SIZE = 9;

    private final static int DEFAULT_DFS_LIMIT = 3;

    private Graph graph;

    private List<DataRecord> data;


    public Timer() {
        data = new ArrayList<DataRecord>();
    }

    public void run() {
        this.initGraph();
        this.initGraphEstrella();
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



    private void printData() {
        List<DataRecord> bfs= new ArrayList<DataRecord>();
        List<DataRecord> dfs= new ArrayList<DataRecord>();
        List<DataRecord> dfsi= new ArrayList<DataRecord>();
        List<DataRecord> gdf= new ArrayList<DataRecord>();
        for (DataRecord dataR : data) {

            if (dataR.getAlgorithm().equals("BFS"))
                bfs.add(dataR);
            if (dataR.getAlgorithm().equals("DFS"))
                dfs.add(dataR);
            if (dataR.getAlgorithm().equals("DFS-Iterative"))
                dfsi.add(dataR);
            if(dataR.getAlgorithm().equals("GREEDY-FIRST"))
                gdf.add(dataR);


            System.out.println(dataR.toString());
        }

        int correct = 0;
        for (DataRecord dataR : dfs) {
            if (dataR.isBestAnswer()) {
                correct++;
            }
        }
        int correcti = 0;
        for (DataRecord dataR : dfsi) {
            if (dataR.isBestAnswer()) {
                correcti++;
            }
        }
        int correctg=0;
        for (DataRecord dataR : gdf) {
            if(dataR.isBestAnswer()){
                correctg++;
            }
        }
        System.out.println("DFS: RATE OF SUCCESS: "+ ((double) correct/dfs.size())*100+"%");
        System.out.println("DFS iterative: RATE OF SUCCESS: "+ ((double) correcti/dfsi.size())*100+"%");
        System.out.println("GREEDY-FIRST: RATE OF SUCCESS: "+ ((double) correctg/dfsi.size())*100+"%");


    }

    private void saveData() {
        List<DataRecord> bfs = new ArrayList<DataRecord>();
        List<DataRecord> dfs = new ArrayList<DataRecord>();
        List<DataRecord> dfsi = new ArrayList<DataRecord>();
        for (DataRecord dataR : data) {
            if (dataR.getAlgorithm().equals("BFS"))
                bfs.add(dataR);
            if (dataR.getAlgorithm().equals("DFS"))
                dfs.add(dataR);
            if (dataR.getAlgorithm().equals("DFS-Iterative"))
                dfsi.add(dataR);
        }
        saveDataToFile(bfs,"data-bfs.csv");
        saveDataToFile(dfs,"data-dfs.csv");
        saveDataToFile(dfsi,"data-dfsi.csv");
    }

    private void saveDataToFile(List<DataRecord> fs, String filename) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (DataRecord dataR : fs) {
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

        graph.floyd();
    }

    private void initGraphEstrella() {

        System.out.println();

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

        graph.addNodeDirecto(head);
        graph.addNodeDirecto(n2);
        graph.addNodeDirecto(n3);
        graph.addNodeDirecto(n4);
        graph.addNodeDirecto(n5);
        graph.addNodeDirecto(n6);
        graph.addNodeDirecto(n7);
        graph.addNodeDirecto(target);
        graph.addNodeDirecto(n9);

    }

    private void estrella() {

        long start, end;
        final String type= "A*";
        System.out.println(type);

        System.out.println(graph.getSize());
        for (int i = 0; i < graph.getSize(); i++) {
            for (int j = 0; j < graph.getSize(); j++) {
                if (i == j) continue;
                NodoEstrela node1 = graph.getNodesEstrella().get(i);
                NodoEstrela node2 = graph.getNodesEstrella().get(j);

                start = System.currentTimeMillis();
                NodoEstrela res = Star.aStar(node1, node2);
                end = System.currentTimeMillis();


                long time = end - start;

                Star.printPath(res, time, node1.getLabel(), node2.getLabel());

                /*
                String label1 = node1.getLabel();
                String label2 = node2.getLabel();

                addData(start, end, type, label1, label2, 0);*/
            }
        }
    }

    private void testDFS() {
        long start, end;
        final String type = "DFS";

        System.out.println(type);

        for (int i = 0; i < graph.getSize(); i++) {
            for (int j = 0; j < graph.getSize(); j++) {
                if (i == j) continue;
                String label1 = graph.getNodes().get(i).getLabel();
                String label2 = graph.getNodes().get(j).getLabel();

                start = System.nanoTime();
                int distance = graph.DFS(label1, label2);
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
                if (i == j) continue;
                String label1 = graph.getNodes().get(i).getLabel();
                String label2 = graph.getNodes().get(j).getLabel();

                start = System.nanoTime();
                int distance = graph.DFS(label1, label2, DEFAULT_DFS_LIMIT);
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
                if (i == j) continue;

                String label1 = graph.getNodes().get(i).getLabel();
                String label2 = graph.getNodes().get(j).getLabel();

                start = System.nanoTime();
                int distance = graph.BFS(label1, label2);
                end = System.nanoTime();
                addData(start, end, type, label1, label2, distance);
            }
        }
    }

    private void addData(long start, long end, String type, String label1, String label2, int distance) {
        this.data.add(new DataRecord(type, (end - start) / 1000, distance, graph.minCostPath(label1, label2), label1, label2));
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
    /*
    // Generamos los transportes
    Transport bicicleta = new Transport("bicicleta", 25.0, 50.0); 
    Transport moto = new Transport("moto", 75.0, 90.0);
    Transport coche = new Transport("coche", 300.0, 100.0);
    /*
    // Tiempos de entrega ---falta la distancia
    double tiempoBicicleta = graph.calculaTiempo(bicicleta.getKg(), bicicleta.getNameTransport(), graph.getDistance(null, null));
    double tiempoMoto = graph.calculaTiempo(moto.getKg(), moto.getNameTransport(), graph.getDistance(null, null));
    double tiempoCoche = graph.calculaTiempo(coche.getKg(), coche.getNameTransport(), graph.getDistance(null, null));*/

 // Generamos los pedidos
    Pedido pedido1 = new Pedido("p01", 5.50, bicicleta, 40.0);
    Pedido pedido2 = new Pedido("p02", 20.0, moto, 20.0);
    Pedido pedido3 = new Pedido("p03", 50.0, coche, 40.0);

 // Generamos las entregas
    Entrega entrega1 = new Entrega("e01", "AVDA BRAGA", pedido1);
    Entrega entrega2 = new Entrega("e02", "RUA DE MINHO", pedido2);
    Entrega entrega3 = new Entrega("e03", "RUA FONTE", pedido2);
    Entrega entrega4 = new Entrega("e04", "AVDA LIBERDADE", pedido3);
}
