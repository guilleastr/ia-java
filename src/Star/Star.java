package Star;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Star {
	public static NodoEstrela aStar(NodoEstrela start, NodoEstrela target){
        PriorityQueue<NodoEstrela> closedList = new PriorityQueue<>();
        PriorityQueue<NodoEstrela> openList = new PriorityQueue<>();

        start.f = start.g + start.calculateHeuristic(target);
        openList.add(start);

        while(!openList.isEmpty()){
        	NodoEstrela n = openList.peek();
            if(n == target){
                return n;
            }

            for(NodoEstrela.Edge edge : n.neighbors){
            	NodoEstrela m = edge.node;
                double totalWeight = n.g + edge.weight;

                if(!openList.contains(m) && !closedList.contains(m)){
                    m.padre = n;
                    m.g = totalWeight;
                    m.f = m.g + m.calculateHeuristic(target);
                    openList.add(m);
                } else {
                    if(totalWeight < m.g){
                        m.padre = n;
                        m.g = totalWeight;
                        m.f = m.g + m.calculateHeuristic(target);

                        if(closedList.contains(m)){
                            closedList.remove(m);
                            openList.add(m);
                        }
                    }
                }
            }

            openList.remove(n);
            closedList.add(n);
        }
        return null;
    }

    public static void printPath(NodoEstrela target){
        NodoEstrela n = target;

        if(n==null)
            return;

        List<Integer> ids = new ArrayList<>();

        while(n.getPadre() != null){
            ids.add(n.id);
            n = n.getPadre();
        }
        ids.add(n.id);
        Collections.reverse(ids);

        for(int id : ids){
            System.out.print(id + " ");
        }
        System.out.println("");
    }
}
