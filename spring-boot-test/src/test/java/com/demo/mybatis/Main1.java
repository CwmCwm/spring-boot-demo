package com.demo.mybatis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main1 {

    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) throws Exception {
        if (input.length < 2) {
            throw new Exception("int[] input -> error");
        }
        for (int i=0; i<input.length-2; i++) {
            for (int j=1; j<input.length-1; j++) {
                if (input[i] > input[j]) {
                    int tmp = input[i];
                    input[i] = input[j];
                    input[j] = tmp;
                }
            }
        }

        ArrayList<Integer> arrayList = new ArrayList<>(k);
        for (int i=0; i<k; i++) {
            arrayList.add(input[i]);
        }
        return arrayList;
    }



    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String line = in.nextLine();
            int k = Integer.parseInt(in.nextLine());
            int[] input = spliteLine(line);
            ArrayList<Integer> res = GetLeastNumbers_Solution(input, k);
            System.out.println(res);
        }

    }

    public static int[] spliteLine(String line) {
        String[] array = line.split(",");
        int[] inputNumbers = new int[array.length];
        for (int i=0; i<array.length; i++) {
            inputNumbers[i] = Integer.parseInt(array[i]);
        }
        return inputNumbers;
    }



}