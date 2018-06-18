public class Main {

    static public void main(String args[]) {
        CPP G = new CPP(4); // create a graph of four vertices

        // add the arcs for the example graph
        G.addArc("a", 0, 1, 1).addArc("b", 0, 2, 1).addArc("c", 1, 2, 1)
         .addArc("d", 1, 3, 1).addArc("e", 2, 3, 1).addArc("f", 3, 0, 1);

        G.solve(); // find the CPT
        G.printCPT(0); // print it, starting from vertex 0
        System.out.println("Cost = " + G.cost());
        OpenCPP.test();
        System.out.println("\nTempo de execução: " + OpenCPP.timer.getTime() + "ms");
    }

}
