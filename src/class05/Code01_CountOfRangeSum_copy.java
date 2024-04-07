package class05;

// 这道题直接在leetcode测评：
// https://leetcode.com/problems/count-of-range-sum/
public class Code01_CountOfRangeSum_copy {

	public static int countRangeSum(int[] nums, int lower, int upper) {
		if (nums == null || nums.length < 1) {
			return 0;
		}
		int[] arr = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			arr[i] += nums[i];
		}
		return process(arr, 0, arr.length - 1, lower, upper);
	}


	public static int process(int[] arr, int l, int r, int lower, int upper) {
		if (l == r) {
			return arr[l] >= lower && arr[l] <= upper ? 1 : 0;
		}
		int mid = l + ((r - l) >> 1);
		return process(arr, l, mid, lower, upper)
				+ process(arr, mid + 1, r, lower, upper)
				+ merge(arr, l, mid, r, lower, upper);
	}

	public static int merge(int[] arr, int L, int M, int R, int lower, int upper) {
		int ans = 0;
		int windL = L;
		int windR = L;
		for (int i = M + 1; i <= R; i++) {
			int max = arr[i] - lower;
			int min = arr[i] - upper;
			while(windR <= M && arr[windR] <= max) {
				windR++;
			}
			while (windL <= M && arr[windL] >= min) {
				windL++;
			}
			ans += windR - windL;
		}
		int[] help = new int[R - L + 1];
		int p1 = L;
		int p2 = M + 1;
		int i = 0;
		while (p1 <= M && p2 <= R) {
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while(p1 <= M) {
			help[i++] = arr[p1++];
		}
		while(p2 <= R) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < arr.length; i++) {
			arr[L + i] = help[i];
		}
		return ans;
	}
}
