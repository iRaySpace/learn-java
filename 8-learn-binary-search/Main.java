import java.util.Arrays;

class Main {
    void main() {
        final int[] elements = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println(Arrays.toString(elements));

        final int index = binarySearch(elements, 11);
        System.out.println("binarySearch: " + index);

        final int recursiveIndex = recursiveBinarySearch(elements, 11, 0, elements.length - 1);
        System.out.println("recursiveBinarySearch: " + recursiveIndex);
    }

    int binarySearch(int[] array, int target) {
        int start = 0;
        int end = array.length - 1;

        while (start <= end) {
            final int mid = start + (end - start) / 2;
            System.out.println("binarySearch: mid index is " + mid);

            if (array[mid] == target) {
                System.out.printf("binarySearch: found target %d on index %d\n", target, mid);
                return mid;
            }
            
            // Branching out
            if (array[mid] < target) {
                System.out.printf("binarySearch: target %d is greater than the mid element %d\n", target, array[mid]);
                start = mid + 1;
            } else if (array[mid] > target) {
                System.out.printf("binarySearch: target %d is lesser than the mid element %d\n", target, array[mid]);
                end = mid - 1;
            }
        }

        return -1;
    }

    int recursiveBinarySearch(int[] array, int target, int start, int end) {
        final int mid = start + (end - start) / 2;
        if (array[mid] == target) {
            System.out.printf("recursiveBinarySearch: found target %d on index %d\n", target, mid);
            return mid;
        }
        
        if (start < end && array[mid] < target) {
            return recursiveBinarySearch(array, target, mid + 1, end);
        } else if (start < end && array[mid] > target) {
            return recursiveBinarySearch(array, target, start, mid - 1);
        }

        return -1;
    }
}