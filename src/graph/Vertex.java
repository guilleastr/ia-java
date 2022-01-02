package graph;

import java.util.Objects;

public class Vertex {

    private String rua;

    public Vertex(String rua){
        this.rua = rua;
    }

    public String getRua() {
        return rua;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(rua, vertex.rua);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rua);
    }
}
