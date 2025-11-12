import java.util.EmptyStackException;
import java.util.Optional;

public class Main {
    class Node {
        char value;
        Node next;
        Node(char value) {
            this.value = value;
        }
    }

    class NodeStack {

        private Node top;
        
        public void push(char value) {
            if (top == null) {
                top = new Node(value);
            } else {
                final Node temp = top;
                top = new Node(value);
                top.next = temp;
            }
        }

        public char pop() {
            if (top == null) {
                throw new EmptyStackException();
            }

            final Node temp = top;
            top = temp.next;

            return temp.value;
        }

        public char peek() {
            if (top == null) {
                throw new EmptyStackException();
            }
            return top.value;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();

            Node nodeIterator = top;
            while (nodeIterator != null) {
                sb.append(nodeIterator.value + " | ");
                nodeIterator = nodeIterator.next;
            }

            return sb.toString();
        }
    }

    void main() {
        final NodeStack stack = new NodeStack();

        stack.push('1');
        System.out.println(stack);

        stack.push('2');
        System.out.println(stack);

        stack.push('3');
        System.out.println(stack);

        System.out.println("pop(): " + stack.pop());
        System.out.println("peek(): " + stack.peek());
    }
}