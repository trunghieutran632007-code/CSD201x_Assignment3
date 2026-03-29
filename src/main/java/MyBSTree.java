import java.util.ArrayList;
public class MyBSTree {
    public Node root;

    public MyBSTree () {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }


    /**
     * Inserting a person to the tree
     *
     * @param person The person to be inserted
     */
    public void insert(Person person) {
        Node newNode = new Node(person);
        if (isEmpty()) {
            root = newNode;
            return;
        }
        Node f = null;
        Node p = root;
        while (p != null) {
            if (p.info.ID.equals(person.ID)) {
                System.out.println("ID already exists. Cannot insert.");
                return;
            }
            f = p;
            if (person.ID.compareTo(p.info.ID) < 0) {
                p = p.left;
            }
            else  {
                p = p.right;
            }
        }
        if (person.ID.compareTo(f.info.ID) < 0) {
            f.left = newNode;
        }else  {
            f.right = newNode;
        }
    }

    /**
     * Performs an in-order traversal of the binary search tree.
     * This method recursively visits the left subtree, prints the current node,
     * and then visits the right subtree.
     *
     * @param p The starting node for the traversal (usually the root of the tree).
     */
    public void inOrder (Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        System.out.println(p.info);
        inOrder(p.right);
    }

    public void bft() {
        if (isEmpty()) {
            return;
        }
        MyQueue q =  new MyQueue();
        q.enqueue(root);
        while (!q.isEmpty()) {
            Node p = q.dequeue();
            System.out.println(p.info);
            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }
        }
    }

    public Node search(Node p, String id) {
        if (p == null) {
            return null;
        }
        if (p.info.ID.equals(id)) {
            return p;
        }
        if (p.info.ID.compareTo(id) < 0) {
            return search(p.left, id);
        }
        return search(p.right, id);
    }

    public void delete(String id) {

    }

}
