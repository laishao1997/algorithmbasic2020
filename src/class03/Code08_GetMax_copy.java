package src.class03;

public class Code08_GetMax_copy {

    // 求arr中的最大值
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }


    // arr[L..R]范围上求最大值  L ... R   N
    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = (L + R) >> 1;
        return Math.max(process(arr, L, mid), process(arr, mid + 1, R));
    }
}
