import java.util.LinkedList;

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
