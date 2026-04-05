import java.util.LinkedList;
/**
 * Represents a Queue data structure using a LinkedList.
 * Used for Breadth-First Traversal of the BST.
 */

public class MyQueue {
    LinkedList<Node> t;
    public MyQueue() {
        t = new LinkedList<>();
    }

    public boolean isEmpty() {
        return t.isEmpty();
    }

    public void enqueue(Node node) {
        t.addLast(node);
    }

    public Node dequeue() {
        if (t.isEmpty()) {
            return null;
        }
        return t.removeFirst();
    }
}
