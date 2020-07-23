package com.demo.mybatis;

import java.util.*;

public class Main2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println(sort(line));
        }
    }

    private static String sort(String line) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0; i<line.length(); i++) {
            Character character = line.charAt(i);
            if (map.containsKey(character)) {
                int count = map.get(character);
                map.put(character, ++count);
            } else {
                map.put(character, 0);
            }
        }
        Set<Character> keySet = map.keySet();
        for (Character key : keySet) {
            
        }

        return "1";
    }



}