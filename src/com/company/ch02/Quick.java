package com.company.ch02;

public class Quick extends Example {

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }


    private static int partition(Comparable[] a, int lo, int hi) {
        // 左右扫描指针
        int i = lo, j = hi + 1;
        // 切分元素
        Comparable v = a[lo];
        while (true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
        }
        exch(a, lo, j);
        return j;
    }
}
