package Timer;

import graph.Graph;
import transport.Transport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Timer {

    private final static int DEFAULT_GRAPH_SIZE = 9;

    private Graph graph;

    private List<DataRecord> data;


    public Timer() {
        data = new ArrayList<DataRecord>();
    }

    public void run() {
        this.initGraph();
        this.testBFS();
        this.testDFS();
        this.printData();

    }


    public void run(int size) {
        initGraph(size);
        this.testBFS();
        this.testDFS();
        this.printData();

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
        for (DataRecord dataR : data) {
            System.out.println(dataR.toString());
        }
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

    private void testDFS() {
        long start, end;
        final String type = "DFS";

        System.out.println(type);

        for (int i = 0; i < graph.getSize(); i++) {
            for (int j = 0; j < graph.getSize(); j++) {
                if(i==j)continue;
                String label1= graph.getNodes().get(i).getLabel();
                String label2= graph.getNodes().get(j).getLabel();

                start = System.currentTimeMillis();
                int distance = graph.DFS(label1,label2);
                end = System.currentTimeMillis();

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

                start = System.currentTimeMillis();
                int distance = graph.BFS(label1,label2);
                end = System.currentTimeMillis();
                addData(start, end, type, label1, label2, distance);
            }
        }
    }

    private void addData(long start, long end, String type, String label1, String label2, int distance) {
        this.data.add(new DataRecord(type, end - start, distance,  graph.minCostPath(label1, label2), label1,label2));
    }
    
    // Generamos los transportes
    Transport bicicleta = new Transport("bicicleta", 25.0, 50.0); 
    Transport moto = new Transport("moto", 75.0, 90.0);
    Transport coche = new Transport("coche", 300.0, 100.0);
    
    // Tiempos de entrega ---falta la distancia
    double tiempoBicicleta = graph.calculaTiempo(bicicleta.getKg(), bicicleta.getNameTransport(), graph.getDistance(null, null));
    double tiempoMoto = graph.calculaTiempo(moto.getKg(), moto.getNameTransport(), graph.getDistance(null, null));
    double tiempoCoche = graph.calculaTiempo(coche.getKg(), coche.getNameTransport(), graph.getDistance(null, null));
}
