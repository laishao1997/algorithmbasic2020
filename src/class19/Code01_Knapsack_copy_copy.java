package src.class19;

public class Code01_Knapsack_copy_copy {

    // 所有的货，重量和价值，都在w和v数组里
    // 为了方便，其中没有负数
    // bag背包容量，不能超过这个载重
    // 返回：不超重的情况下，能够得到的最大价值
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length == 0 || v.length == 0) {
            return 0;
        }
        return process(w, v, 0, bag);
    }

    // index 0~N
    // rest 负~bag
    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        //不要index位置的物品
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }


    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length == 0 || v.length == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[i + 1][rest];
                int p2 = 0;
                int next = rest - w[i] < 0 ? -1 : dp[i + 1][rest - w[i]];
                if (next != -1) {
                    p2 = v[i] + next;
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }

}
