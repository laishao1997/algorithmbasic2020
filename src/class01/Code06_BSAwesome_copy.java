package class01;

public class Code06_BSAwesome_copy {

	public static int getIndex(int[] arr) {
		if (arr == null || arr.length < 1) {
			return -1;
		}
		if (arr.length == 1 || arr[0] < arr[1]) {
			return 0;
		}
		int n = arr.length;
		if (arr[n - 1] < arr[n - 2]) {
			return n - 1;
		}
		int L = 1;
		int R = n - 2;
		while (L < R) {
			int mid = L + ((R - L) >> 1);
			if (arr[mid + 1] < arr[mid]) {
				L = mid + 1;
			} else if (arr[mid - 1] < arr[mid]) {
				R = mid - 1;
			} else {
				return mid;
			}
		}
		return L;
	}

}
