/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Mon Nov 18 14:30:09 CET 2024
 */

package uebung10.as.aufgabe02;


public class GraphTest {

  @SuppressWarnings("unused")
  public static void main(String[] args) {
    Graph<String, String> graph = new Graph<>();
    Vertex<String> u = graph.insertVertex("U");
    Vertex<String> v = graph.insertVertex("V");
    Vertex<String> w = graph.insertVertex("W");
    Edge<String> a = graph.insertEdge(u, v, "a");
    Edge<String> b = graph.insertEdge(v, w, "b");
    graph.print();
    
    if (graph.opposite(u, a) != v) {
      System.err.println("ERROR: v is not opposite of u!");
      System.exit(11);
    }
    if (!graph.areAdjacent(v, w)) {
      System.err.println("ERROR: v is not adjacent of w!");
      System.exit(22);
    }
  }
}

/* Session-Log:

Graph:
U -> (V,a) 
V -> (U,a) (W,b) 
W -> (V,b) 

*/
