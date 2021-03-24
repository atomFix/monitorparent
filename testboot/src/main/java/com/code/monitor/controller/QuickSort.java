package com.code.monitor.controller;

import java.util.Arrays;

/**
 * @author codeDorado
 * @version 1.0
 * @date 2021/3/3 21:06
 */
public class QuickSort {

    private static void QuickSort(int[] num, int left, int right) {
        if (left >= right) {
            return;
        }
        int key = num[left];
        int i = left, j = right;
        while (i < j) {
            while (num[j] >= key && i < j) {
                j--;
            }
            while (num[i] <= key && i < j) {
                i++;
            }
            if (i < j) {
                int temp = num[i];
                num[i] = num[j];
                num[j] = temp;
            }
        }
        num[left] = num[i];
        num[i] = key;
        QuickSort(num, left, i - 1);
        QuickSort(num, i + 1, right);
    }

    public static void main(String[] args) {
        int[] num = {3, 45, 78, 64, 52, 11, 64, 55, 99, 11, 18};
        quick(num, 0, num.length - 1);
        System.out.println(Arrays.toString(num));
    }

    static void quick(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int key = nums[left];
        int i = left, j = right;
        while (i < j) {
            while (j > i && nums[j] >= key) {
                j--;
            }
            while (j > i && nums[i] <= key) {
                i++;
            }
            if (i < j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        nums[left] = nums[i];
        nums[i] = key;
        quick(nums, left, i - 1);
        quick(nums, i + 1, right);
    }

}
