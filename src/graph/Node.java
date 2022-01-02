package graph;

import java.util.Objects;

public class Node {

    private String label;


    public Node(String rua) {
        this.label = rua;
    }

    public String getLabel() {
        return label;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node vertex = (Node) o;
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
