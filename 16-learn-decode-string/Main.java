import java.util.ArrayDeque;

class Main {

    void main() {
        final String firstCase = decodeString("3[a]2[bc]");
        System.out.println("firstCase: " + firstCase.equals("aaabcbc"));

        final String secondCase = decodeString("3[a2[c]]");
        System.out.println("secondCase: " + secondCase.equals("accaccacc"));

        final String thirdCase = decodeString("2[abc]3[cd]ef");
        System.out.println("thirdCase: " + thirdCase.equals("abcabccdcdcdef"));

        decodeString("3[a2[c]");
    }

    String decodeString(String s) {
        StringBuilder decodedString = new StringBuilder();

        final ArrayDeque<Integer> stack = new ArrayDeque<>();
        final ArrayDeque<StringBuilder> stackSb = new ArrayDeque<>();

        int idx = 0;
        while (idx < s.length()) {

            // Getting the characters
            while (idx < s.length() && Character.isLowerCase(s.charAt(idx))) {
                decodedString.append(s.charAt(idx));
                idx = idx + 1;
            }

            if (idx < s.length() && Character.isDigit(s.charAt(idx))) {
                final StringBuilder digits = new StringBuilder();
                while (idx < s.length() && Character.isDigit(s.charAt(idx))) {
                    digits.append(s.charAt(idx));
                    idx = idx + 1;
                }

                if (s.charAt(idx) != '[') {
                    throw new IllegalArgumentException(
                            "Expected '[' at position " + idx + " but got '" + s.charAt(idx) + "'");
                }

                final int multiplier = Integer.parseInt(digits.toString());
                stack.push(multiplier);

                // Never push mutable reference
                stackSb.push(new StringBuilder(decodedString));
                decodedString.setLength(0);

                idx = idx + 1;
                continue;
            }

            if (idx < s.length() && s.charAt(idx) == ']') {
                final int multiplier = stack.pop();

                // Pre-allocate memory
                final StringBuilder multiplied = new StringBuilder(decodedString.length() * multiplier);
                for (int c = 0; c < multiplier; c++) {
                    multiplied.append(decodedString);
                }

                decodedString = stackSb.pop();
                decodedString.append(multiplied);
            }

            idx = idx + 1;
        }

        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Unbalanced brackets: missing ']'");
        }

        return decodedString.toString();
    }

}
