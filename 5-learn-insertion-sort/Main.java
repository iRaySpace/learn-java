import java.util.Arrays;


class Main {
    void main() {
        final int[] unsorted = new int[] {23, 1, 10, 5, 2};
        System.out.println(Arrays.toString(unsorted));

        insertionSort(unsorted);
        System.out.println(Arrays.toString(unsorted));
    }

    void insertionSort(int[] data) {
        for (int sortPointer = 1; sortPointer < data.length; sortPointer++) {
            int comparisonElement = data[sortPointer];
            int backwardPointer = sortPointer - 1;

            // Move elements to the right
            while (backwardPointer >= 0 && comparisonElement < data[backwardPointer]) {
                data[backwardPointer + 1] = data[backwardPointer];
                backwardPointer = backwardPointer - 1;
            }

            data[backwardPointer + 1] = comparisonElement;
        }
    }
}