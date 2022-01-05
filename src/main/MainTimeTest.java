package main;

import Timer.Timer;
import delivery.Entrega;
import graph.Graph;

import java.util.ArrayList;
import java.util.List;

public class MainTimeTest {


    public static void main(String[] args) {
        Timer t= new Timer();

        //Test default graph
        t.run();

        //Test random graph of N size
        t.run(70);




    }
}
