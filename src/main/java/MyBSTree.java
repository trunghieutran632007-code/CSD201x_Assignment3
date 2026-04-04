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
        if (id.compareTo(p.info.ID) < 0) {
            return search(p.left, id);
        }
        return search(p.right, id);
    }

    public void delete(String id) {
        Node f = null, p = root;
        while (p != null && !p.info.ID.equals(id)) {
            f = p;
            if (id.compareTo(p.info.ID) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }

        }
        if (p == null) {
            System.out.println("Not found ID: " + id);
            return;
        }

        // Delete node with one or no child
        if (p.left == null || p.right == null) {
            Node q = (p.left != null) ? p.left : p.right;
            if (f == null) root = q;
            else if (f.left == p) f.left = q;
            else f.right = q;
        } else {
            // Delete node with two children
            Node rp = p.right;
            Node fRp = p;
            while (rp.left != null) {
                fRp = rp;
                rp = rp.left;
            }
            p.info = rp.info;
            if (fRp.left == rp) fRp.left = rp.right;
            else fRp.right = rp.right;
        }
        System.out.println("Deleted successfully.");
    }

    public void balance() {
        ArrayList<Person> list = new ArrayList<>();
        storeInOrder(root, list);
        MyBSTree newTree = new MyBSTree();
        buildBalancedTree(list, 0, list.size() - 1,  newTree );
        this.root = newTree.root;
        System.out.println("Tree balanced successfully.");

    }

    private void storeInOrder(Node p, ArrayList<Person> list) {
        if (p == null) {
            return;
        }
        storeInOrder(p.left, list);
        list.add(p.info);
        storeInOrder(p.right, list);
    }

    private void buildBalancedTree(ArrayList<Person> list, int start, int end, MyBSTree tree) {
        if (start > end) {
            return;
        }
        int mid = (start + end) / 2;
        tree.insert(list.get(mid));
        buildBalancedTree(list, start, mid - 1, tree);
        buildBalancedTree(list, mid + 1, end, tree);
    }

}
