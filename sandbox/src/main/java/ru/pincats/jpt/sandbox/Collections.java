package ru.pincats.jpt.sandbox;

import java.util.Arrays;
import java.util.List;

/**
 * Created by PinCatS on 07.11.2016.
 */
public class Collections {
    public static void main(String[] args) {

        List<String> fruits_list = Arrays.asList("apple", "pineapple", "melon", "peach", "peer");
        for (String fruit : fruits_list) {
            System.out.println(fruit);
        }
    }
}
