package main;

import delivery.Entrega;
import delivery.Pedido;
import graph.Graph;

import java.util.ArrayList;
import java.util.List;


public class Main {



/*
    // Tiempos de entrega ---falta la distancia
    double tiempoBicicleta = graphDistances.calculaTiempo(bicicleta.getKg(), bicicleta.getNameTransport(), graphDistances.getDistance(null, null));
    double tiempoMoto = graphDistances.calculaTiempo(moto.getKg(), moto.getNameTransport(), graphDistances.getDistance(null, null));
    double tiempoCoche = graphDistances.calculaTiempo(coche.getKg(), coche.getNameTransport(), graphDistances.getDistance(null, null));*/

    // Generamos los pedidos
    static Pedido pedido1 = new Pedido("p01", 3.50, 1, 40.0);
    static Pedido pedido2 = new Pedido("p02", 30.0, 3, 20.0);
    static Pedido pedido3 = new Pedido("p03", 1.0, 10, 40.0);
    static Pedido pedido4 = new Pedido("p04", 30.0, 1, 40.0);

    // Generamos las entregas
    static Entrega entrega1 = new Entrega("e01", "AVDA BRAGA", pedido1);
    static Entrega entrega2 = new Entrega("e02", "RUA DE MINHO", pedido2);
    static Entrega entrega3 = new Entrega("e03", "RUA FONTE", pedido3);
    static Entrega entrega4 = new Entrega("e04", "AVDA LIBERDADE", pedido4);

    public static void main(String[] args) {
        Graph graphDistances = createGraphDistances();
        Graph graphTime =createGraphTimes();

        
        System.out.println(graphDistances.calculateVehicle(entrega1));
        System.out.println(graphDistances.calculateVehicle(entrega2));
        System.out.println(graphDistances.calculateVehicle(entrega3));
        System.out.println(graphDistances.calculateVehicle(entrega4));

        List<Entrega> entregas= new ArrayList<>();
        entregas.add(entrega1);
        entregas.add(entrega2);

        System.out.println(graphDistances.sendMultipleEstefeta(new ArrayList<>(entregas)));

        System.out.println(graphDistances.calculateVehicle(entregas));


    }

    public static Graph createGraphDistances() {
        Graph graph = new Graph(9);
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

        return graph;
    }


    public static Graph createGraphTimes() {
        Graph graph = new Graph(9);
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
        graph.addEdge(r1, r2, 10);
        graph.addEdge(r1, r3, 12);
        graph.addEdge(r2, r5, 24);
        graph.addEdge(r2, r4, 15);
        graph.addEdge(r4, r6, 14);
        graph.addEdge(r4, r3, 22);
        graph.addEdge(r5, r6, 6);
        graph.addEdge(r6, r7, 18);
        graph.addEdge(r7, r8, 15);
        graph.addEdge(r1, r9, 12);
        graph.addEdge(r9, r6, 10);

        return graph;
    }
}
