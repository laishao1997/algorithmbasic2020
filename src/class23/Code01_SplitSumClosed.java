package src.class23;

public class Code01_SplitSumClosed {

    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return process(arr, 0, sum / 2);
    }

    // arr[i...]可以自由选择，请返回累加和尽量接近rest，但不能超过rest的情况下，最接近的累加和是多少？
    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return 0;
        }
        int p1 = process(arr, index + 1, rest);
        int p2 = 0;
        if (rest - arr[index] >= 0) {
            p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
        }
        return Math.max(p1, p2);
    }


    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][sum / 2 + 1];
        for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= sum / 2; rest++) {
				int p1 = dp[index + 1][rest];
				int p2 = 0;
				if (rest - arr[index] >= 0) {
					p2 = arr[index] + dp[index + 1][rest - arr[index]];
				}
				dp[index][rest] = Math.max(p1, p2);
			}
        }
        return dp[0][sum / 2];
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
