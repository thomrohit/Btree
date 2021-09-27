class Node {
    String value;
    Node left;
    Node right;
    Node parent;

   public Node(String value, Node parent) {
        this.value = value;
        right = null;
        left = null;
        this.parent = parent;
    }
}