package src.class40;

import java.util.HashMap;
import java.util.Map;

public class Code02_LongestSumSubArrayLength_copy {

	//以i为结尾的所有子数组，达到的和
	public static int maxLength(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		//K: 前缀和的值
		//V: 最早出现该前缀和的位置
		HashMap<Integer, Integer> cache = new HashMap<>();
		cache.put(0, -1);
		int sum = 0;
		int len = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (cache.containsKey(sum - k)) {
				len = Math.max(len, i - cache.get(sum - k));
			}
			if(!cache.containsKey(sum)) {
				cache.put(sum, i);
			}
		}
		return len;
	}

	// for test
	public static int right(int[] arr, int K) {
		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				if (valid(arr, i, j, K)) {
					max = Math.max(max, j - i + 1);
				}
			}
		}
		return max;
	}

	// for test
	public static boolean valid(int[] arr, int L, int R, int K) {
		int sum = 0;
		for (int i = L; i <= R; i++) {
			sum += arr[i];
		}
		return sum == K;
	}

	// for test
	public static int[] generateRandomArray(int size, int value) {
		int[] ans = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
		}
		return ans;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int len = 50;
		int value = 100;
		int testTime = 500000;

		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(len, value);
			int K = (int) (Math.random() * value) - (int) (Math.random() * value);
			int ans1 = maxLength(arr, K);
			int ans2 = right(arr, K);
			if (ans1 != ans2) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println("K : " + K);
				System.out.println(ans1);
				System.out.println(ans2);
				break;
			}
		}
		System.out.println("test end");

	}

}
