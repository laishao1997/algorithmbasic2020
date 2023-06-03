package class08;

import java.util.Arrays;

public class Code03_CountSort {

    // only for 0~200 value
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
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

    public static void MergeSort(int[] arr) {
    	if (arr == null || arr.length < 2) {
    		return;
		}
    	process2(arr, 0, arr.length - 1);
	}

	private static void process2(int[] arr, int L, int R) {
		if (L == R) {
			return;
		}
		int mid = (L + R) / 2;
		process2(arr, L, mid);
		process2(arr, mid + 1, R);
		merge(arr, L, mid, R);
    }

	private static void merge(int[] arr, int L, int mid, int R) {
		int[] help = new int[R - L + 1];
		int indexL = L;
		int indexR = mid + 1;
		int i = 0;
		while (indexL <= mid && indexR <= R) {
			help[i++] = arr[indexL] <= arr[indexR] ? arr[indexL++] : arr[indexR++];
		}
		while (indexL <= mid) {
			help[i++] = arr[indexL++];
		}
		while (indexR <= R) {
			help[i++] = arr[indexR++];
		}
		for (int k = 0; k < help.length; k++) {
			arr[L + k] = help[k];
		}
    }


	public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalsArea = netherLand(arr, L, R);
        process1(arr, L, equalsArea[0] - 1);
        process1(arr, equalsArea[1] + 1, R);
    }

    private static int[] netherLand(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
			return new int[]{L , R};
        }
        int lessL = L - 1;
        int moreR = R;
        int index = L;
        while (index < moreR) {
        	if (arr[index] > arr[R]) {
        		swap(arr, --moreR, index);
			} else if (arr[index] < arr[R]) {
        		swap(arr, index++, ++lessL);
			} else {
        		index++;
			}
		}
        //最后别忘
        swap(arr, moreR, R);
        return new int[] {lessL + 1, moreR};
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 150;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
			MergeSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        countSort(arr);
        printArray(arr);

    }

}
