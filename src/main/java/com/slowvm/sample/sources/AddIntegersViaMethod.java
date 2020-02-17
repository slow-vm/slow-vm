package com.slowvm.sample.sources;

public class AddIntegersViaMethod {
    public static void main(String[] args) {
        int i = 1;
        int j = 3;
        int k = add(i, j);
    }

    private static int add(int i, int j) {
        return i + j;
    }
}