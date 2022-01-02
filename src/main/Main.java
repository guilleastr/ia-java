package main;

import graph.*;

import java.beans.VetoableChangeListener;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Graph graph = createGraph(); //creamos el grafo
        List<Vertex> vertices = graph.getAdjVertices("GREEN DISTRIBUTION");
        List<Integer> distancias = graph.getAdjDistanciaVertices("GREEN DISTRIBUTION");
        for (int i=0;i<vertices.size();i++){
            System.out.println(vertices.get(i).getRua() + "  -  " +  distancias.get(i));

        }



    }

    public static Graph createGraph() {
        Graph graph = new Graph();
        String r1 = "AVDA BRAGA";
        String r2 = "AVDA PARIS";
        String r3 = "AVDA LIBERDADE";
        String r4 = "RUA FONTE";
        String r5 = "RUA PORTUGAL";
        String r6 = "AVDA SANTANDER";
        String r7 = "RUA DE MINHO";
        String r8 = "RUA ESPANHA";
        String r9 = "GREEN DISTRIBUTION";
        graph.addVertex(r1);
        graph.addVertex(r2);
        graph.addVertex(r3);
        graph.addVertex(r4);
        graph.addVertex(r5);
        graph.addVertex(r6);
        graph.addVertex(r7);
        graph.addVertex(r8);
        graph.addVertex(r9);
        graph.addEdge(r1, r2,12);
        graph.addEdge(r1, r3,20);
        graph.addEdge(r2, r5,30);
        graph.addEdge(r2, r4,22);
        graph.addEdge(r4, r3,45);
        graph.addEdge(r5, r6,7);
        graph.addEdge(r6, r7,9);
        graph.addEdge(r7, r8,18);
        graph.addEdge(r1, r9,18);
        graph.addEdge(r9,r6,7);

        return graph;
    }
}
