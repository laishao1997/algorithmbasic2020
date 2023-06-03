package src.class14;

import java.util.PriorityQueue;

public class Code02_LessMoneySplitGold_copy {

	// 纯暴力！
	public static int lessMoney1(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		return process(arr, 0);
	}

	// 等待合并的数都在arr里，pre之前的合并行为产生了多少总代价
	// arr中只剩一个数字的时候，停止合并，返回最小的总代价
	public static int process(int[] arr, int sum) {
		if (arr.length == 1) {
			return sum;
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				min = Math.min(min, process(copyAndMergeTwo(arr, i, j), sum + arr[i] + arr[j]));
			}
		}
		return min;
	}

	public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
		int[] ans = new int[arr.length - 1];
		int ansi = 0;
		for (int arri = 0; arri < arr.length; arri++) {
			if (arri != i && arri != j) {
				ans[ansi++] = arr[arri];
			}
		}
		ans[ansi] = arr[i] + arr[j];
		return ans;
	}

	public static int lessMoney2(int[] arr) {
		if (arr == null || arr.length < 1) {
			return 0;
		}
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		//全部加入小根堆
		for (int i : arr) {
			queue.add(i);
		}
		int ans = 0;
		while (queue.size() > 1) {
			int sum = queue.poll() + queue.poll();
			ans += sum;
			queue.add(sum);
		}
		return ans;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	public static void main(String[] args) {
		int testTime = 100000;
		int maxSize = 6;
		int maxValue = 1000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			if (lessMoney1(arr) != lessMoney2(arr)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
