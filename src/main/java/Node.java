/**
 * Represents a node in the Binary Search Tree.
 * Each node stores a Person object and references to its left and right children.
 */
public class Node {
    public Person info;
    public Node left, right;

    /**
     * Default constructor.
     */
    public Node() {

    }

    /**
     * Constructor with parameters. Creates a node containing the given person,
     * with no children (left and right are null by default).
     *
     * @param info The {@link Person} object to store in this node
     */
    public Node(Person info) {
        this.info = info;
    }
}


