import java.util.Optional;

class Node {

    int value;
    Optional<Node> left;
    Optional<Node> right;

    Node(int value) {
        this.value = value;
        this.left = Optional.empty();
        this.right = Optional.empty();
    }

}

class Main {
    void main() {
        final Node root = new Node(10);

        insert(root, 4);
        insert(root, 11);

        insert(root, 2);
        insert(root, 15);

        insert(root, 5);
    }

    void insert(Node root, int value) {
        if (root.value > value) {
            if (root.left.isEmpty()) {
                root.left = Optional.of(new Node(value));
            } else {
                insert(root.left.get(), value);
            }
        } else if (root.value < value) {
            if (root.right.isEmpty()) {
                root.right = Optional.of(new Node(value));
            } else {
                insert(root.right.get(), value);
            }
        } else {
            throw new IllegalArgumentException("unable to add value that already exists: " + value);
        }
    }
}