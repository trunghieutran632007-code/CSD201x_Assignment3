import java.util.LinkedList;

/**
 * Represents a Queue data structure using a LinkedList.
 * Used for Breadth-First Traversal of the BST.
 */
public class MyQueue {
    LinkedList<Node> t;

    /**
     * Initializes an empty queue using a LinkedList.
     */
    public MyQueue() {
        t = new LinkedList<>();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return t.isEmpty();
    }

    /**
     * Adds a node to the end of the queue.
     *
     * @param node The node to be added to the queue.
     */
    public void enqueue(Node node) {
        t.addLast(node);
    }

    /**
     * Removes and returns the node at the front of the queue.
     *
     * @return The node at the front of the queue, or null if the queue is empty.
     */
    public Node dequeue() {
        if (t.isEmpty()) {
            return null;
        }
        return t.removeFirst();
    }
}
