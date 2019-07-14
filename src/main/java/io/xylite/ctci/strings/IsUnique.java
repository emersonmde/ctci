package io.xylite.ctci.strings;

import java.util.HashSet;

/**
 * Implement an algorithm to determine if a string hash
 * all unique characters
 */
public class IsUnique {
    public static boolean allCharactersUnique1(String input) {
        HashSet<Character> charSet = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            if (charSet.contains(input.charAt(i))) {
                return false;
            }
            charSet.add(input.charAt(i));
        }
        return true;
    }

    /**
     * What if you cannot use additional data structures?
     * @return
     */
    public static boolean allCharactersUnique2(String input) {
        Character[] charArray = new Character[input.length()];
        for (int i = 0; i < input.length(); i++) {
            charArray[i] = input.charAt(i);
        }
        quickSort(charArray);
        for (int i = 0, j = 1; j < charArray.length; i++, j++) {
            if (charArray[i].equals(charArray[j])) {
                return false;
            }
        }

        return true;
    }

    private static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] array, int start , int end) {
        if (start >= end) {
            return;
        }

        int pivot = sortOnPivot(array, start, end);
        quickSort(array, start, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    private static <T extends Comparable<T>> int sortOnPivot(T[] array, int start, int end) {
        T pivot = array[end];

        // i tracks the end of the smaller elements
        int i = start - 1;

        // j tracks the current element
        for (int j = start; j < end; j++) {
            // skip if its larger then the pivot
            if (array[j].compareTo(pivot) > 0) {
                continue;
            }

            // Found a smaller element, so update the end of the list
            i++;

            // No need to swap if the current if its in the smaller element list
            if (j <= i) {
                continue;
            }

            // Swap current element into the smaller element list
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        // Swap pivot to just after last smaller element
        T temp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = temp;

        return i + 1;
    }
}
