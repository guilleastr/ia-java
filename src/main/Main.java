package main;

import Timer.Timer;
import graph.*;
import printer.PrettyPrinter;
import printer.PrettyPrinterInteger;
import transport.Transport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Timer timer= new Timer();

        timer.run(100);
        //timer.run(100);
        
        


    }

    public static Graph createGraph() {
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
        graph.addEdge(r1, r2,12);
        graph.addEdge(r1, r3,20);
        graph.addEdge(r2, r5,30);
        graph.addEdge(r2, r4,22);
        graph.addEdge(r4,r6,10);
        graph.addEdge(r4, r3,45);
        graph.addEdge(r5, r6,7);
        graph.addEdge(r6, r7,9);
        graph.addEdge(r7, r8,18);
        graph.addEdge(r1, r9,18);
        graph.addEdge(r9,r6,7);

        return graph;
    }
}
