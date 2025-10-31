import java.util.Arrays;

class Main {
    void main() {
        final int[] data = new int[] { 38, 27, 25 };
        System.out.println("data: " + Arrays.toString(data));

        final int[] sortedData = mergeSort(data);
        System.out.println("sortedData: " + Arrays.toString(sortedData));
    }

    // NOTE: need to optimized because maybe in 1 million data; there are too much creation and copies.
    int[] merge(int[] left, int[] right) {
        System.out.println("Sorting " + Arrays.toString(left) + " and " + Arrays.toString(right));

        final int[] sortedData = new int[left.length + right.length];

        int leftPointer = 0;
        int rightPointer = 0;
        int insertPointer = 0;

        while (leftPointer < left.length && rightPointer < right.length) {
            if (left[leftPointer] < right[rightPointer]) {
                sortedData[insertPointer] = left[leftPointer];
                leftPointer = leftPointer + 1;
            } else {
                sortedData[insertPointer] = right[rightPointer];
                rightPointer = rightPointer + 1;
            }
            insertPointer = insertPointer + 1;
        }

        while (leftPointer < left.length) {
            sortedData[insertPointer] = left[leftPointer];
            leftPointer = leftPointer + 1;
            insertPointer = insertPointer + 1;
        }

        while (rightPointer < right.length) {
            sortedData[insertPointer] = right[rightPointer];
            rightPointer = rightPointer + 1;
            insertPointer = insertPointer + 1;
        }

        return sortedData;
    }

    int[] mergeSort(int[] data) {
        if (data.length <= 1) {
            return data;
        }

        System.out.println("Splitting: " + Arrays.toString(data));

        final int mid = data.length / 2;
        final int[] left = Arrays.copyOfRange(data, 0, mid);
        final int[] right = Arrays.copyOfRange(data, mid, data.length);

        return merge(mergeSort(left), mergeSort(right));
    }
}