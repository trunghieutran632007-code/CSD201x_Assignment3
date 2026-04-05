import java.util.Scanner;
/**
 * The main class of the application
 * Provides a command line interface menu to interact with MyBSTree and Graph functionalities
 */
public class Main {
    /**
     * The entry point of the program
     * Initializes the data structures and displays the interactive menu
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        MyBSTree tree = new MyBSTree();
        Graph graph = new Graph();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose one of this options:");
            System.out.println("Person Tree:");
            System.out.println("1. Insert a new Person.");
            System.out.println("2. Inorder Traversal");
            System.out.println("3. Breadth-First Traversal");
            System.out.println("4. Search by Person ID");
            System.out.println("5. Delete by Person ID");
            System.out.println("6. Balancing Binary Search Tree");
            System.out.println("7. DFS-Graph");
            System.out.println("8. Dijkstra");
            System.out.println("Advanced functions:");
            System.out.println("9. PreOrder Traversal");
            System.out.println("10. PostOrder Traversal");
            System.out.println("11. BFS-Graph");
            System.out.println("Exit:");
            System.out.println("0. Exit");
            System.out.print("choice=");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                choice = -1;
            }

            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Input new ID: ");
                    String id = sc.nextLine();
                    System.out.print("Input Name: ");
                    String name = sc.nextLine();
                    System.out.print("Input birthplace: ");
                    String birthPlace = sc.nextLine();
                    System.out.print("Input Birth of Date: ");
                    String dob = sc.nextLine();
                    tree.insert(new Person(id, name, birthPlace, dob));
                    break;
                case 2:
                    System.out.println("Inorder Traversal:");
                    tree.inOrder(tree.root);
                    break;
                case 3:
                    System.out.println("Breadth-First Traversal:");
                    tree.bft();
                    break;
                case 4:
                    System.out.print("Input ID to search: ");
                    String searchId = sc.nextLine();
                    Node res = tree.search(tree.root, searchId);
                    if (res != null) {
                        System.out.println("Found: " + res.info.toString());
                    } else {
                        System.out.println("Not found ID: " + searchId);
                    }
                    break;
                case 5:
                    System.out.print("Input ID to delete: ");
                    String delId = sc.nextLine();
                    tree.delete(delId);
                    break;
                case 6:
                    tree.balance();
                    break;
                case 7:
                    graph.loadData("Matran.txt");
                    graph.DFS(0); // 0 corresponds to city A
                    break;
                case 8:
                    graph.loadData("Matran.txt");
                    graph.displayWeights();
                    graph.dijkstra(0, 4); // 0 to 4 corresponds to shortest path A -> E
                    break;
                case 9:
                    System.out.println("PreOrder Traversal:");
                    tree.preOrder(tree.root);
                    break;
                case 10:
                    System.out.println("PostOrder Traversal:");
                    tree.postOrder(tree.root);
                    break;
                case 11:
                    graph.loadData("Matran.txt");
                    graph.BFS(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }
}