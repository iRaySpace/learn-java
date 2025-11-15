import java.util.ArrayDeque;
import java.util.ArrayList;

class Main {

    class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    }

    void main() {
        final Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right = new Node(5);
        
        final ArrayList<Integer> result = iterativePreorderTraversal(root);
        System.out.println(result);
    }

    ArrayList<Integer> iterativePreorderTraversal(Node root) {
        final ArrayDeque<Node> stack = new ArrayDeque<>();
        final ArrayList<Integer> result = new ArrayList<>();

        stack.push(root);
        while (stack.size() > 0) {
            final Node node = stack.pop();
            result.add(node.value);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }

}
