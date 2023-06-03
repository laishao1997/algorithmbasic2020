package src.class04;

public class Code01_MergeSort_copy_3 {

    // 递归方法实现
    public static void mergeSort1(int[] arr) {
        if (arr.length < 2 || arr == null) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int l = L;
        int r = M + 1;
        int index = 0;
        while (l <= M && r <= R) {
            help[index++] = arr[l] > arr[r] ? arr[r++] : arr[l++];
        }
        while (l <= M) {
            help[index++] = arr[l++];
        }
        while (r <= R) {
            help[index++] = arr[r++];
        }
        for (index = 0; index < help.length; index++) {
            arr[L + index] = help[index];
        }
    }


    // 非递归方法实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int step = 1;//步长
        while (step < N) {
            int L = 0;//左组第一个数
            while (L < N) {
                //当剩下的数凑不齐左组的时候，将剩下的数交个下一次merge
                if (step >= N - L) {
                    break;
                }
                int mid = L + step - 1;
                int R = mid + Math.min(step, N - mid - 1);
                merge(arr, L, mid, R);
                L = R + 1;
            }
            if (step > N / 2) {//防止数据太大溢出，小数据不要这一句也可以
                break;
            }
            step <<= 1;
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
