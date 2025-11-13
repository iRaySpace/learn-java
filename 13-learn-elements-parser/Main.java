import java.util.HashMap;

// NOTE: Limitations is only one element at a time and one count at a time
class Main {

    class AtomCountHelper {

        int idx = 0;

        HashMap<Character, Integer> execute(String formula) {
            final HashMap<Character, Integer> atomCount = new HashMap<>();
            while (idx < formula.length()) {
                final char c = formula.charAt(idx);

                if (c == '(') {
                    idx = idx + 1;

                    final HashMap<Character, Integer> scopedAtomCount = execute(formula);

                    if (idx < formula.length() && Character.isDigit(formula.charAt(idx))) {
                        final int multiplier = formula.charAt(idx) - '0';
                        for (char scopedAtom : scopedAtomCount.keySet()) {
                            scopedAtomCount.put(scopedAtom, scopedAtomCount.get(scopedAtom) * multiplier);
                        }
                        idx = idx + 1;
                    }

                    for (char atom : scopedAtomCount.keySet()) {
                        atomCount.put(atom, atomCount.getOrDefault(atom, 0) + scopedAtomCount.get(atom));
                    }
                } else if (c == ')') {
                    idx = idx + 1;
                    return atomCount;
                } else {
                    final char element = formula.charAt(idx);

                    // Move to the next element and do a count parsing
                    idx = idx + 1;

                    // Count parsing
                    int count = 1;
                    if (idx < formula.length() && Character.isDigit(formula.charAt(idx))) {
                        count = formula.charAt(idx) - '0';
                        idx = idx + 1;
                    }

                    atomCount.put(element, atomCount.getOrDefault(element, 0) + count);
                }
            }

            return atomCount;
        }
    }

    void main() throws InterruptedException {
        final AtomCountHelper helper = new AtomCountHelper();
        System.out.println("C2(C3): " + helper.execute("C2(C3)"));
        // Expected: {C=5}

        helper.idx = 0;  // Reset!
        System.out.println("C(OH)2: " + helper.execute("C(OH)2"));
        // Expected: {C=1, O=2, H=2}
        
        helper.idx = 0;  // Reset!
        System.out.println("H2O: " + helper.execute("H2O"));
        // Expected: {H=2, O=1}
    }

}
