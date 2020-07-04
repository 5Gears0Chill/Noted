package com.fivegearszerochill.noted.util.general;

public class StringHelper {

    public static String getFirstNWords(String s, int words) {
        String[] arr = s.split("\\s+");
        //Splits words & assign to the arr[]  ex : arr[0] -> Copying ,arr[1] -> first

        StringBuilder nWords = new StringBuilder();

        // concatenating number of words that you required
        for (int i = 0; i < words; i++) {
            nWords.append(" ").append(arr[i]);
        }

        return nWords.toString();
    }
}
