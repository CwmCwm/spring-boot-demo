package com.demo.mybatis;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String[]  val = in.nextLine().split(",");
            int[] x = new int[val.length];
            for (int i = 0; i < val.length; i++) {
                x[i] = Integer.parseInt(val[i]);
            }
            System.out.println(maximumGap(x));
        }

    }

    public static int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        for (int i=0; i<nums.length-2; i++) {
            for (int j=1; j<nums.length-1; j++) {
                if (nums[i] > nums[j]) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        int maximumGap = 0;
        for (int i=0; i<nums.length-2; i++) {
            int tmp = nums[i+1] - nums[i];
            if (tmp > maximumGap) {
                maximumGap = tmp;
            }
        }
        return maximumGap;
    }



}