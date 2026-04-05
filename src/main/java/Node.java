/**
 * Represents a node in the Binary Search Tree.
 * Each node stores a Person object and references to its left and right children.
 */
public class Node {
    public Person info;
    public Node left, right;
    public Node() {

    }
    public Node(Person info) {
        this.info = info;
    }
}


