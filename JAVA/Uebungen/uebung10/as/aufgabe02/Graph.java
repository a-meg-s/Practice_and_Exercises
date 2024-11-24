/*
 * HSLU / ICS/AIML : Modul ADS : Algorithmen & Datenstrukturen
 * Version: Mon Nov 18 14:30:09 CET 2024
 */

package uebung10.as.aufgabe02;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({ "hiding", "rawtypes", "unchecked" })
public class Graph<V extends Comparable<V>, E extends Comparable<E>>
    implements GraphInterface<V, E> {

  private BackRefList<VertexImpl<V>> vertices;
  private BackRefList<EdgeImpl<E>> edges;

  Graph() {
    vertices = new BackRefList<>();
    edges = new BackRefList<>();
  }

  @Override
  public int numVertices() {
    return vertices.size();
  }

  @Override
  public int numEdges() {
    return edges.size();
  }

  @Override
  public Iterable<Vertex<V>> vertices() {
    return (List) vertices.getItems();
  }

  @Override
  public Iterable<Edge<E>> edges() {
    return (List) edges.getItems();
  }

  @Override
  public V replace(Vertex<V> v, V element) throws IllegalArgumentException {
    VertexImpl<V> vi = validate(v);
    return vi.setElement(element);
  }

  @Override
  public E replace(Edge<E> e, E element) throws IllegalArgumentException {
    EdgeImpl<E> ei = validate(e);
    return ei.setElement(element);
  }

  @Override
  public Iterable<Edge<E>> incidentEdges(Vertex<V> v)
      throws IllegalArgumentException {
    VertexImpl<V> vi = validate(v);
    return (List) vi.getIncidentEdges();
  }

  @Override
  public Vertex<V>[] endVertices(Edge<E> e) throws IllegalArgumentException {
    EdgeImpl<E> ei = validate(e);
    return ei.getEndpoints();
  }

  /**
   * @throws IllegalArgumentException if edge is not incident to vertex.
   */
  @Override
  public Vertex<V> opposite(Vertex<V> v, Edge<E> e)
      throws IllegalArgumentException {
    VertexImpl<V> vertexImpl = validate(v);
    EdgeImpl<E> edgeImpl = validate(e);

    Vertex<V>[] endpoints = edgeImpl.getEndpoints();

    if (endpoints[0].equals(vertexImpl)) {
      return endpoints[1];
    } else if (endpoints[1].equals(vertexImpl)) {
      return endpoints[0];
    } else {
      throw new IllegalArgumentException("Vertex is not connected to the edge.");
    }
  }

  @Override
  public boolean areAdjacent(Vertex<V> v, Vertex<V> w)
      throws IllegalArgumentException {
    VertexImpl<V> vi = validate(v);
    VertexImpl<V> wi = validate(w);
    List<EdgeImpl<E>> incV = vi.getIncidentEdges();
    List<EdgeImpl<E>> incW = wi.getIncidentEdges();

    for (EdgeImpl<E> edge : incV) {
      Vertex<V>[] endpoints = edge.getEndpoints(); // Get the two endpoints of the edge
      if ((endpoints[0].equals(vi) && endpoints[1].equals(wi)) ||
          (endpoints[0].equals(wi) && endpoints[1].equals(vi))) {
        return true; // Found an edge connecting v and w
      }
    }

    return false;
  }

  @Override
  public Vertex<V> insertVertex(V element) {
    VertexImpl<V> v = new VertexImpl<>(element);
    Node<VertexImpl<V>> node;
    node = vertices.insert(v);
    v.setBackReference(node);
    return v;
  }

  @Override
  public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E element)
      throws IllegalArgumentException {
    VertexImpl<V> vi = validate(v);
    VertexImpl<V> wi = validate(w);
    EdgeImpl<E> e = new EdgeImpl<>(v, w, element);
    Node<EdgeImpl<E>> node;
    node = edges.insert(e);
    e.setBackReference(node);
    node = vi.incidentList.insert(e);
    e.backReferences[0] = node;
    node = wi.incidentList.insert(e);
    e.backReferences[1] = node;
    return e;
  }

  @Override
  public V removeVertex(Vertex<V> v) throws IllegalArgumentException {
    VertexImpl<V> vi = validate(v);
    List<EdgeImpl<E>> edges = vi.getIncidentEdges();
    for (EdgeImpl<E> e : edges) {
      removeEdge(e);
    }
    vertices.remove((Node<VertexImpl<V>>) vi.getBackReference());
    vi.setBackReference(null); // mark vertex as <defunct>
    return v.getElement();
  }

  @Override
  public E removeEdge(Edge<E> e) throws IllegalArgumentException {
    EdgeImpl<E> ei = validate(e);
    Vertex<V>[] vertices = ei.getEndpoints();
    Node<?>[] backRefs = ei.getBackReferences();
    VertexImpl<V> v0 = (VertexImpl<V>) vertices[0];
    v0.removeIncident(backRefs[0]);
    VertexImpl<V> v1 = (VertexImpl<V>) vertices[1];
    v1.removeIncident(backRefs[1]);
    edges.remove((Node<EdgeImpl<E>>) ei.getBackReference());
    ei.setBackReference(null); // mark edge as <defunct>
    return e.getElement();
  }

  protected VertexImpl<V> validate(Vertex<V> v)
      throws IllegalArgumentException {
    VertexImpl<V> vi = (VertexImpl<V>) v;
    if (!vi.validate(this))
      throw new IllegalArgumentException("Invalid vertex");
    return vi;
  }

  protected EdgeImpl<E> validate(Edge<E> e) throws IllegalArgumentException {
    EdgeImpl<E> ei = (EdgeImpl<E>) e;
    if (!ei.validate(this))
      throw new IllegalArgumentException("Invalid edge");
    return ei;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (VertexImpl<V> v : vertices.getItems()) {
      sb.append(v.getElement() + " -> ");
      for (EdgeImpl<E> e : v.getIncidentEdges()) {
        Vertex<V> w = opposite(v, e);
        sb.append("(" + w + "," + e + ") ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public void print() {
    System.out.println("Graph:");
    System.out.println(toString());
  }

  /**
   * Prints the content of the vertex- and edge-sequences.
   * 
   * listID:NodeNr|Item(BackRef)|prev<>next [: endpoints[2] : backrefs[2]]
   * listID: "v-seq" | "e-seq"
   * NodeNr: `listID`:"head"|`nr`|"tail"
   * Item: Node.item.toString()
   * BackRef: Back-Reference of Item
   * prev: Node.prev
   * next: Node.next
   */
  protected void printDiagnostic() {
    Map<Integer, String> objIdMap = new LinkedHashMap<>();
    fillUp(objIdMap);
    System.out.println("v-seq:");
    vertices.printDiagnostic("    ", objIdMap);
    System.out.println("e-seq:");
    edges.printDiagnostic("    ", objIdMap);
  }

  /**
   * Traverses all lists and puts each object into the map (once!).
   * 
   * @param objIdMap
   *                 Mapping of objects to its String-ID.
   * @throws IllegalStateException
   *                               When an object is inserted more than once into
   *                               the map.
   */
  protected void fillUp(Map<Integer, String> objIdMap)
      throws IllegalStateException {
    String listID = "v-seq";
    Node head = vertices.getHead();
    Node tail = vertices.getTail();
    putInMap(head, listID + ":head", objIdMap);
    int i = 0;
    Node cursor = head.getNext();
    while (cursor != tail) {
      putInMap(cursor, listID + ":" + i, objIdMap);
      putInMap(cursor.getItem(), cursor.getItem().getElement().toString(), objIdMap);
      String incListID = "inc-seq";
      VertexImpl v = (VertexImpl) cursor.getItem();
      Node incHead = v.incidentList.getHead();
      Node incTail = v.incidentList.getTail();
      putInMap(incHead, incListID + ":" + i + "-head", objIdMap);
      int j = 0;
      Node incCursor = incHead.getNext();
      while (incCursor != incTail) {
        putInMap(incCursor, incListID + ":" + i + "-" + j, objIdMap);
        incCursor = incCursor.getNext();
        j++;
      }
      putInMap(incTail, incListID + ":" + i + "-tail", objIdMap);
      cursor = cursor.getNext();
      i++;
    }
    putInMap(tail, listID + ":tail", objIdMap);

    listID = "e-seq";
    head = edges.getHead();
    tail = edges.getTail();
    putInMap(head, listID + ":head", objIdMap);
    i = 0;
    cursor = head.getNext();
    while (cursor != tail) {
      putInMap(cursor, listID + ":" + i, objIdMap);
      cursor = cursor.getNext();
      i++;
    }
    putInMap(tail, listID + ":tail", objIdMap);
  }

  private static void putInMap(Object obj, String objId, Map<Integer, String> objIdMap)
      throws IllegalStateException {
    if (objIdMap.get(obj.hashCode()) == null) {
      objIdMap.put(obj.hashCode(), objId);
    } else {
      throw new IllegalStateException(objId + ": exists already!");
    }
  }

  class VertexImpl<V extends Comparable<V>> extends BackRefNodeItem<V>
      implements Vertex<V> {

    private BackRefList<EdgeImpl<E>> incidentList;

    protected VertexImpl(V element) {
      setElement(element);
      incidentList = new BackRefList<>();
    }

    @Override
    public V getElement() {
      return super.getElement();
    }

    protected List<EdgeImpl<E>> getIncidentEdges() {
      return incidentList.getItems();
    }

    protected void removeIncident(Node<?> backRef) {
      incidentList.remove((Node) backRef);
    }

    protected BackRefList<EdgeImpl<E>> getIncidentList() {
      return incidentList;
    }

    protected boolean validate(GraphInterface<V, E> graph) {
      return (Graph.this == graph) && (getBackReference() != null);
    }

  }

  class EdgeImpl<E extends Comparable<E>> extends BackRefNodeItem<E>
      implements Edge<E> {

    private Vertex<V>[] endpoints;
    private Node<?>[] backReferences;

    protected EdgeImpl(Vertex<V> v, Vertex<V> w, E element) {
      setElement(element);
      endpoints = new Vertex[] { v, w };
      backReferences = new Node[2];
    }

    @Override
    public E getElement() {
      return super.getElement();
    }

    protected Vertex<V>[] getEndpoints() {
      return endpoints;
    }

    protected Node<?>[] getBackReferences() {
      return backReferences;
    }

    protected boolean validate(GraphInterface<V, E> graph) {
      return (Graph.this == graph) && (getBackReference() != null);
    }

  }

}
