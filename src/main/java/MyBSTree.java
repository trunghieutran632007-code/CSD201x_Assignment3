import java.util.ArrayList;
/**
 * Represents a Binary Search Tree (BST) storing Person records.
 * Supports insert, search, delete, traversal and balancing operations.
 */
public class MyBSTree {
    public Node root;
    /**
     * Initializes an empty Binary Search Tree.
     * Sets the root of the tree to null.
     */
    public MyBSTree () {
        root = null;
    }
    /**
     * Checks whether the Binary Search Tree is empty.
     *
     * @return true if the tree has no nodes, false otherwise.
     */
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

    /**
     * Performs a Breadth-First Traversal (BFT) on the tree.
     * This method uses a queue to traverse the tree level by level,
     * starting from the root node, and prints the information of each node.
     * If the tree is empty, the method returns immediately.
     */
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

    /**
     * Recursively searches for a node with the specified ID in the binary search tree.
     * * @param p  The starting node (usually the root) for the search.
     * @param id The target ID to search for.
     * @return The node containing the specified ID, or null if the node is not found.
     */
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

    /**
     * Deletes a node from the BST by the given ID.
     * Handles three cases:
     * - Node has no children: simply remove it.
     * - Node has one child: replace it with its child.
     * - Node has two children: replace with in-order successor.
     *
     * @param id The ID of the Person to be deleted.
     */
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

    /**
     * Balances the BST by collecting all nodes via in-order traversal
     * into a sorted list, then rebuilding the tree from the middle element
     * of each subarray to ensure balance.
     */
    public void balance() {
        ArrayList<Person> list = new ArrayList<>();
        storeInOrder(root, list);
        MyBSTree newTree = new MyBSTree();
        buildBalancedTree(list, 0, list.size() - 1,  newTree );
        this.root = newTree.root;
        System.out.println("Tree balanced successfully.");

    }

    /**
     * Stores all nodes in sorted order into a list using in-order traversal.
     *
     * @param p    The current node.
     * @param list The list to store Person objects.
     */
    private void storeInOrder(Node p, ArrayList<Person> list) {
        if (p == null) {
            return;
        }
        storeInOrder(p.left, list);
        list.add(p.info);
        storeInOrder(p.right, list);
    }

    /**
     * Recursively builds a balanced BST from a sorted list.
     *
     * @param list  The sorted list of Person objects.
     * @param start The start index.
     * @param end   The end index.
     * @param tree  The tree to insert into.
     */
    private void buildBalancedTree(ArrayList<Person> list, int start, int end, MyBSTree tree) {
        if (start > end) {
            return;
        }
        int mid = (start + end) / 2;
        tree.insert(list.get(mid));
        buildBalancedTree(list, start, mid - 1, tree);
        buildBalancedTree(list, mid + 1, end, tree);
    }

    /**
     * Performs a pre-order traversal of the binary search tree.
     * Visits the current node first, then recursively the left and right subtrees.
     *
     * @param p The starting node for the traversal (usually the root).
     */
    public void preOrder(Node p) {
        if (p == null) return;
        System.out.println(p.info);
        preOrder(p.left);
        preOrder(p.right);
    }

    /**
     * Performs a post-order traversal of the binary search tree.
     * Recursively visits the left and right subtrees first, then the current node.
     *
     * @param p The starting node for the traversal (usually the root).
     */
    public void postOrder(Node p) {
        if (p == null) return;
        postOrder(p.left);
        postOrder(p.right);
        System.out.println(p.info);
    }

}
