/**
 * Class for finding and printing the optimal Chinese Postman tour of multidigraphs.
 * For more details, read http://www.uclic.ucl.ac.uk/harold/cpp.
 *
 * @author Harold Thimbleby, 2001, 2, 3.
 * Adaptado para experimentos computacionais.
 *
 */

import util.Timer;
import java.util.Vector;

public class OpenCPP {
    Vector arcs = new Vector();
    static Timer timer = new Timer(1);
    int N;

    OpenCPP(int vertices) {
        N = vertices;
    }

    public OpenCPP addArc(String lab, int u, int v, float cost) {
        if (cost < 0) throw new Error("Graph has negative costs");
        arcs.addElement(new Arc(lab, u, v, cost));
        return this;
    }

    float printCPT(int startVertex) {
        CPP bestGraph = null, g;
        float bestCost = 0, cost;
        int i = 0;
        do {
            g = new CPP(N + 1);
            for (int j = 0; j < arcs.size(); j++) {
                Arc it = (Arc) arcs.elementAt(j);
                g.addArc(it.lab, it.u, it.v, it.cost);
            }
            cost = g.basicCost;
            g.findUnbalanced(); // initialise g.neg on original graph
            g.addArc("'virtual start'", N, startVertex, cost);
            g.addArc("'virtual end'",
                    // graph is Eulerian if neg.length=0
                    g.neg.length == 0 ? startVertex : g.neg[i], N, cost);
            g.solve();
            if (bestGraph == null || bestCost > g.cost()) {
                bestCost = g.cost();
                bestGraph = g;
            }
        } while (++i < g.neg.length);
        System.out.println("Open CPT from " + startVertex + " (ignore virtual arcs)");
        bestGraph.printCPT(N);
        return cost + bestGraph.phi();
    }

    static void test() {
        timer.start();
        OpenCPP G = new OpenCPP(4); // create a graph of four vertices
        // add the arcs for the example graph
        G.addArc("a", 0, 1, 1).addArc("b", 0, 2, 1).addArc("c", 1, 2, 1)
                .addArc("d", 1, 3, 1).addArc("e", 2, 3, 1).addArc("f", 3, 0, 1);
        int besti = 0, worsti = 0;
        float bestCost = 0, worstCost = 0;
        for (int i = 0; i < 4; i++) {
            System.out.println("\nSolve from " + i);
            float c = G.printCPT(i);
            System.out.println("Cost = " + c + "\n");
            if (i == 0 || c < bestCost) {
                bestCost = c;
                besti = i;
            }
            if(i == 0 || c > worstCost){
                worstCost = c;
                worsti = i;
            }
        }

        System.out.println("\n* FASTEST WAY *");
        G.printCPT(besti);
        System.out.println("Cost = " + bestCost);

        System.out.println("\n* SLOWEST WAY *");
        G.printCPT(worsti);
        System.out.println("Cost = " + worstCost);
        timer.finish();
    }

}