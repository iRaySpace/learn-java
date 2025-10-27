import java.util.Arrays;

class Main {
    void main() {
        final int[] unsorted = new int[] { 8, 5, 7, 1, 9, 3 };
        System.out.println(Arrays.toString(unsorted));

        selectionSort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }

    void selectionSort(int[] data) {
        int unsortedPointer = 0;
        do {
            int minX = unsortedPointer;

            for (int x = unsortedPointer; x < data.length; x++) {
                if (data[x] < data[minX]) {
                    minX = x;
                }
            }

            if (minX != unsortedPointer) {
                int temp = data[unsortedPointer];
                data[unsortedPointer] = data[minX];
                data[minX] = temp;
            }

            unsortedPointer = unsortedPointer + 1;
        } while (unsortedPointer < data.length - 1);
    }
}
