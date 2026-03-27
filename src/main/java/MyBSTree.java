import java.util.ArrayList;
public class MyBSTree {
    public Node root;

    public MyBSTree () {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(Person person) {
        Node newNode = new Node(person);
        if (isEmpty()) {
            root = newNode;
            return;
        }


    }

}
