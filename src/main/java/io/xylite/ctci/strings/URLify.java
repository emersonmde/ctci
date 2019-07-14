package io.xylite.ctci.strings;

/**
 * Write a method to replace all spaces in a string with '%20'. You may
 * assume that the string has sufficient space at the end to hold the
 * additional characters, and that you are given the "true" length of
 * the string. (Note: if implementing in Java, please use a character
 * array so that you can perform this operation in place.)
 */
public class URLify {
    public static void main(String[] args) {
        char[] input = "Mr John Smith    ".toCharArray();
        parameterize(input, 13);
        System.out.println(input);
    }

    public static void parameterize(char[] input, int length) {
        int j = input.length - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (input[i] == ' ') {
                input[j] = '0';
                input[j - 1] = '2';
                input[j - 2] = '%';
                j -= 3;
            } else {
                char temp = input[i];
                input[i] = input[j];
                input[j] = temp;
                j--;
            }
        }
    }
}
