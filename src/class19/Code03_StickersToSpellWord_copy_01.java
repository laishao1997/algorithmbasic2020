package src.class19;

import netscape.security.UserTarget;
import sun.net.idn.StringPrep;

import java.util.HashMap;

// 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word
public class Code03_StickersToSpellWord_copy_01 {

    public static int minStickers1(String[] stickers, String target) {
        int res = process(stickers, target);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // 所有贴纸stickers，每一种贴纸都有无穷张
    // target
    // 最少张数
    private static int process(String[] stickers, String target) {
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first : stickers) {
            String rest = minus(first, target);
            if (rest.length() != target.length()) {
                min = Math.min(min, process(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }


    public static String minus(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] cp = new int[26];
        for (char c : str1) {
            cp[c - 'a']++;
        }
        for (char c : str2) {
            cp[c - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cp.length; i++) {
            if (cp[i] > 0) {
                int count = cp[i];
                for (int j = 0; j < count; j++) {
                    sb.append((char) ('a' + i));
                }
            }
        }
        return sb.toString();
    }

    public static int minStickers2(String[] stickers, String target) {
        int N = stickers.length;
        // 关键优化(用词频表替代贴纸数组)
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        int ans = process2(counts, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // stickers[i] 数组，当初i号贴纸的字符统计 int[][] stickers -> 所有的贴纸
    // 每一种贴纸都有无穷张
    // 返回搞定target的最少张数
    // 最少张数
    public static int process2(int[][] stickers, String t) {
        if (t.length() == 0) {
            return 0;
        }
        // target做出词频统计
        // target  aabbc  2 2 1..
        //                0 1 2..
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // 尝试第一张贴纸是谁
            int[] sticker = stickers[i];
            // 最关键的优化(重要的剪枝!这一步也是贪心!)
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process2(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    public static int minStickers3(String[] stickers, String target) {
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char cha : str) {
                counts[i][cha - 'a']++;
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        int ans = process3(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
        if (dp.containsKey(t)) {
            return dp.get(t);
        }
        char[] target = t.toCharArray();
        int[] tcounts = new int[26];
        for (char c : target) {
            tcounts[c - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // 获取第一张贴纸
            int[] sticker = stickers[i];
            // 优化
            // 如果该贴纸含有target第一个字符才去尝试，减支
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String rest = builder.toString();
                min = Math.min(min, process3(stickers, rest, dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(t, ans);
        return ans;
    }
//	public static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
//		if (dp.containsKey(t)) {
//			return dp.get(t);
//		}
//		char[] target = t.toCharArray();
//		int[] tcounts = new int[26];
//		for (char cha : target) {
//			tcounts[cha - 'a']++;
//		}
//		int N = stickers.length;
//		int min = Integer.MAX_VALUE;
//		for (int i = 0; i < N; i++) {
//			int[] sticker = stickers[i];
//			if (sticker[target[0] - 'a'] > 0) {
//				StringBuilder builder = new StringBuilder();
//				for (int j = 0; j < 26; j++) {
//					if (tcounts[j] > 0) {
//						int nums = tcounts[j] - sticker[j];
//						for (int k = 0; k < nums; k++) {
//							builder.append((char) (j + 'a'));
//						}
//					}
//				}
//				String rest = builder.toString();
//				min = Math.min(min, process3(stickers, rest, dp));
//			}
//		}
//		int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
//		dp.put(t, ans);
//		return ans;
//	}

    public static void main(String[] args) {
        String str1 = "with";
        String str2 = "thehat";
        String minus = minus(str1, str2);
        System.out.println(minus);
    }

}
