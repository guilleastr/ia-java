package graph;

import java.util.*;

public class Graph {
    private Map<Vertex, List<Vertex>> adjVertices;
    private Map<Vertex, List<Integer>> adjDistanciaVertices;

    // standard constructor, getters, setters
    public Graph(){
        this.adjVertices=new HashMap<Vertex, List<Vertex>>();
        this.adjDistanciaVertices=new HashMap<Vertex, List<Integer>>();
    }

    public void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
        adjDistanciaVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }


    public void addEdge(String label1, String label2, int distancia) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjVertices.get(v1).add(v2);
        adjDistanciaVertices.get(v1).add(distancia);
        adjVertices.get(v2).add(v1);
        adjDistanciaVertices.get(v2).add(distancia);
    }

    //METODO QUE RETORNA LOS NOMBRES DE LOS VERTICES ADYACENTES
    public List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    //METODO QUE RETORNA LAS DISTANCIAS ENTRE LOS VERTICES ADYACENTES
    public List<Integer> getAdjDistanciaVertices(String label) {
        return adjDistanciaVertices.get(new Vertex(label));
    }


}
