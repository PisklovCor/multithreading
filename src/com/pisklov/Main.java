package com.pisklov;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {

        System.out.println("Array");
        arras_v1();
        System.out.println();
        System.out.println("Flows");
        arras_v2();
    }

    public static void arras_v1() {

        float[] arr = new float[size];

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();

        System.out.println("Time of completion: " + (b - a));
    }

    private static void arras_v2() {
        Main mN = new Main();

        float[] arr2 = new float[size];
        float[] arr3 = new float[h];
        float[] arr4 = new float[h];

        for (int i = 0; i < size; i++) {
            arr2[i] = 1;
        }

        long a = System.currentTimeMillis();

        System.arraycopy(arr2, 0, arr3, 0, h);
        System.arraycopy(arr2, h, arr4, 0, h);

        new Thread(() -> mN.method(arr3)).start();
        new Thread(() -> mN.method(arr4)).start();

        System.arraycopy(arr3, 0, arr2, 0, h);
        System.arraycopy(arr3, 0, arr2, h, h);

        long b = System.currentTimeMillis();

        System.out.println("Time of completion: " + (b - a));
    }

    public float[] method(float[] arrM) {

        for (int i = 0; i < h; i++) {
            arrM[i] = (float) (arrM[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        return arrM;
    }

}

