
class Main {

    class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }
        if (node.left == null) {
            node.left = insert(node.left, value);
            return node.left;
        } else if (node.right == null) {
            node.right = insert(node.right, value);
            return node.right;
        } else {
            // pass
            return null;
        }
    }

    void preorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.value);
        preorderTraversal(node.left);
        preorderTraversal(node.right);        
    }

    void inorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        System.out.println(node.value);
        inorderTraversal(node.right);
    }

    void main() {
        final Node root = insert(null, 1);
        
        final Node left = insert(root, 2);
        insert(left, 4);
        insert(left, 5);

        final Node right = insert(root, 3);
        insert(right, 6);
        insert(right, 7);

        // preorderTraversal(root);
        inorderTraversal(root);
    }

}
