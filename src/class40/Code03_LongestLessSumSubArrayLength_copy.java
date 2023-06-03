package src.class40;

public class Code03_LongestLessSumSubArrayLength_copy {

	//思路：就是利用窗口，一次一次的往后扩，直到 > k
	//因为两个辅助数组，minSums代表以某个开头往后的最小和，minSumsEnd，对应的某个开头最小和能到什么位置
	//这样当窗口扩的时候，如果一直 < k就一直往后扩，那么index就能随着一直增长，直到最长
	//如果到某个位置 > k了，说明之前的长度，就是以当前位置开头的最长，那么就要把当前位置往后推一个，继续加后面的最小和，看看是否能满足 <= k直到找到最大长度
    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
		int[] minSums = new int[arr.length];
        int[] minSumsEnd = new int[arr.length];
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSumsEnd[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
        	if (minSums[i + 1] <= 0) {
        		minSums[i] = arr[i] + minSums[i + 1];
        		minSumsEnd[i] = minSumsEnd[i + 1];
			} else {
        		minSums[i] = arr[i];
				minSumsEnd[i] = i;
			}
		}
        // 扩不进来的开头位置，即可理解为窗口的右边界后一个位置
        int end = 0;
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
        	while (end < arr.length && sum + minSums[end] <= k) {
        		sum += minSums[end];
        		end = minSumsEnd[end] + 1;
			}
        	ans = Math.max(ans, end - i);
        	if (end > i) { //窗口还存在，窗口内还有数
        		sum -= arr[i];
			} else {
        		end = end + 1;
			}
		}
        return ans;
    }

    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        for (int i = 0; i < 10000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }

}
