package src.class05;

public class Code02_PartitionAndQuickSort_copy_copy {

    public static void swap(int[] arr, int i, int j) {
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    // arr[L..R]上，以arr[R]位置的数做划分值
    // <= X > X
    // <= X X
    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEquals = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] < arr[R]) {
                swap(arr, ++lessEquals, index);
            }
            index++;
        }
        swap(arr, ++lessEquals, R);
        return lessEquals;
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    // <arr[R] ==arr[R] > arr[R]
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int moreL = L - 1;
        int moreR = R;
        int index = L;
        while (index < moreR) {
            if (arr[index] < arr[R]) {
                swap(arr, ++moreL, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --moreR);
            } else {
                index++;
            }
        }
        swap(arr, R, moreR);
        return new int[]{moreL + 1, moreR};
    }

    // arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
    // <arr[R] ==arr[R] > arr[R]
    public static int[] netherlandsFlag2(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int moreL = L - 1;
        int moreR = R;
        int index = L;
        while (index < moreR) {
            if (arr[index] < arr[R]) {
                swap(arr, ++moreL, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, --moreR, index);
            } else {
                index++;
            }
        }
        swap(arr, R, moreR);
        return new int[]{moreL + 1, moreR};
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int m = partition(arr, L, R);
        process1(arr, L, m - 1);
        process1(arr, m + 1, R);
    }


    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    // arr[L...R] 排有序，快排2.0方式
    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] pev = netherlandsFlag(arr, L, R);
        process1(arr, L, pev[0]);
        process1(arr, pev[1], R);
    }


    public static void compaleQuickSort(int[] arr) {
        if (arr == null || arr.length < 0) {
            return;
        }
        process4(arr, 0, arr.length - 1);
    }

    private static void process4(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) Math.random() * (R - L + 1), R);
        int[] eqaulArea = netherlandsFlag4(arr, L, R);
        process4(arr, L, eqaulArea[0] - 1);
        process4(arr, eqaulArea[1] + 1, R);
    }

    private static int[] netherlandsFlag4(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;//小于区边际
        int more = R;//大于区边际
        int index = L;
        while (index < more) {
            if (arr[index] < arr[R]) {
                swap(arr, ++less, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, --more, index);
            } else {
                index++;
            }
        }
        swap(arr, more, R);
        return new int[]{less + 1, more};
    }


    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[0] + 1, R);
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
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quckiSortf(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }


    public static void quickSortA(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        processSort(arr, 0, arr.length - 1);
    }

    private static void processSort(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        //随机选择划分点
        swap(arr, L + (int) Math.random() * (R - L + 1), R);
        int[] equalArea = netherLands(arr, L, R);
        processSort(arr, L, equalArea[0] - 1);
        processSort(arr, equalArea[1] + 1, R);
    }

    private static int[] netherLands(int[] arr, int L, int R) {
        if (L > R) {
            return new int[] {-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int lessL = L - 1;
        int moreR = R;
        int index = L;
        while (index < moreR) {
            if (arr[index] < arr[R]) {
                swap(arr, ++lessL, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, --moreR, index);
            } else {
                index++;
            }
        }
        swap(arr, moreR, R);
        return new int[]{lessL + 1, moreR};
    }


    public static void swapf(int[] arr, int i, int j) {
        int tem = arr[i];
        arr[i] = arr[j];
        arr[j] = tem;
    }

    public static void quckiSortf(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        processf(arr, 0, arr.length - 1);
    }

    private static void processf(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int pivor = L + (int) (Math.random() * (R - L + 1));
        swapf(arr, pivor, R);
        int[] equalArea = netherlandsf(arr, L, R);
        processf(arr, L, equalArea[0] - 1);
        processf(arr, equalArea[1] + 1, R);
    }

    private static int[] netherlandsf(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int lessE = L - 1;
        int moreE = R;
        int index = L;
        while (index < moreE) {
            if (arr[index] < arr[R]) {
                swapf(arr, index++, ++lessE);
            } else if (arr[index] > arr[R]) {
                swapf(arr, index, --moreE);
            } else {
                index++;
            }
        }
        swapf(arr, moreE, R);
        return new int[]{lessE + 1, moreE};
    }
}
