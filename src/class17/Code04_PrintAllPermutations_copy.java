package src.class17;

import java.util.ArrayList;
import java.util.List;

public class Code04_PrintAllPermutations_copy {

	public static List<String> permutation1(String s) {
		if (s == null || s.length() < 1) {
			return new ArrayList<>();
		}
		char[] strs = s.toCharArray();
		List<String> ans = new ArrayList<>();
		ArrayList<Character> characters = new ArrayList<>();
		for (char c : strs) {
			characters.add(c);
		}
		String path = "";
		f(characters, path, ans);
		return ans;
	}

	private static void f(ArrayList<Character> strs, String path, List<String> ans) {
		if (strs.isEmpty()) {
			ans.add(path);
		} else {
			int N = strs.size();
			for (int i = 0; i < N; i++) {
				char c = strs.get(i);
				strs.remove(i);
				f(strs, path + String.valueOf(c), ans);
				strs.add(i, c);
			}
		}
	}


	public static List<String> permutation2(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return ans;
		}
		char[] str = s.toCharArray();
		g1(str, 0, ans);
		return ans;
	}

	public static void g1(char[] str, int index, List<String> ans) {
		if (index == str.length) {
			ans.add(String.valueOf(str));
		} else {
			for (int i = index; i < str.length; i++) {
				swap(str, index, i);
				g1(str, index + 1, ans);
				swap(str, index, i);
			}
		}
	}

	public static List<String> permutation3(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return ans;
		}
		char[] str = s.toCharArray();
		g2(str, 0, ans);
		return ans;
	}

	public static void g2(char[] str, int index, List<String> ans) {
		if (index == str.length) {
			ans.add(String.valueOf(str));
		} else {
			boolean[] visited = new boolean[256];
			for (int i = index; i < str.length; i++) {
				if (!visited[str[i]]) {
					visited[str[i]] = true;
					swap(str, index, i);
					g2(str, index + 1, ans);
					swap(str, index, i);
				}
			}
		}
	}

	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

	public static void main(String[] args) {
		String s = "acc";
		List<String> ans1 = permutation1(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans2 = permutation2(s);
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans3 = permutation3(s);
		for (String str : ans3) {
			System.out.println(str);
		}

	}

}
