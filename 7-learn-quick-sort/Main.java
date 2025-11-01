import java.util.Arrays;


class Main {
    void main() throws Exception {
        final int[] data = new int[] { 19, 17, 15, 12, 16, 18, 4, 11, 13 };
        System.out.println("data: " + Arrays.toString(data));

        quickSort(data);
        System.out.println("quickSort: " + Arrays.toString(data));
    }

    void quickSort(int[] data) throws Exception {
        quickSort(data, 0, data.length - 1);
    }

    void quickSort(int[] data, int start, int end) throws InterruptedException {
        final int length = end - start;
        if (length < 1) {
            return;
        }

        int slowPtr = start - 1;
        int fastPtr = start;

        while (fastPtr <= end) {
            final int fastElement = data[fastPtr];
            final int pivotElement = data[end];

            System.out.printf("fastElement: %d, pivotElement: %d\n", fastElement, pivotElement);

            if (fastElement <= pivotElement) {
                slowPtr = slowPtr + 1;
                if (data[slowPtr] > data[fastPtr]) {
                    final int temp = data[slowPtr];
                    data[slowPtr] = data[fastPtr];
                    data[fastPtr] = temp;
                    System.out.printf("Swapped [%d] and [%d]\n", data[slowPtr], data[fastPtr]);
                }
            }

            fastPtr = fastPtr + 1;
            Thread.sleep(1000);
        }

        System.out.println("= Start of Left =");
        quickSort(data, start, slowPtr - 1);
        System.out.println("= End of Left =");

        System.out.println("= Start of Right =");
        quickSort(data, slowPtr + 1, end);
        System.out.println("= End of Right =");
    }
}