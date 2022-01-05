package Star;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NodoEstrela implements Comparable<NodoEstrela>{

    private String label;

    public List<Edge> neighbors;

    public NodoEstrela(String rua) {
        this.label = rua;
    }

    public String getLabel() {
        return label;
    }


    // Ids
    private static int idCounter = 0;
    public int id;


    public NodoEstrela padre; // nodo primario


    // Evaluation functions
    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;
    // Hardcoded heuristic
    public double h;


    //Constructor
    public NodoEstrela(String rua, double h){
        this.label = rua;
        this.h = h;
        this.id = idCounter++;
        this.neighbors = new ArrayList<>();
    }


    public static class Edge {
        Edge(int weight, NodoEstrela node){
            this.weight = weight;
            this.node = node;
        }

        public int weight;
        public NodoEstrela node;
    }


    public NodoEstrela getPadre() {
        return padre;
    }
    public void setPadre(NodoEstrela padre) {
        this.padre = padre;
    }

    public void addBranch(int weight, NodoEstrela node){
        Edge newEdge = new Edge(weight, node);
        neighbors.add(newEdge);
    }

    public double calculateHeuristic(NodoEstrela target){
        return this.h;
    }


    @Override
    public int compareTo(NodoEstrela n) {
        return Double.compare(this.f, n.f);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodoEstrela vertex = (NodoEstrela) o;
        return Objects.equals(label, vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    public boolean is(String label) {
        return this.label.equals(label);
    }
}
