package src.class15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/number-of-islands-ii/
// 所有方法都可以直接通过
// 动态的加入岛
public class Code03_NumberOfIslandsII_copy {

	public static List<Integer> numIslands21(int m, int n, int[][] positions) {
		UnionFind1 unionFind1 = new UnionFind1(m, n);
		List<Integer> ans = new ArrayList<>();
		for (int[] position : positions) {
			ans.add(unionFind1.connect(position[0], position[1]));
		}
		return ans;
	}

	public static class UnionFind1 {
		private int[] parent;
		private int[] size;
		private int[] help;
		private final int row;
		private final int col;
		private int sets;

		public UnionFind1(int m, int n) {
			row = m;
			col = n;
			sets = 0;
			int len = row * col;
			parent = new int[len];
			size = new int[len];
			help = new int[len];
		}

		private int index(int r, int c) {
			return r * col + c;
		}

		private int find(int i) {
			int k = 0;
			while (i != parent[i]) {
				help[k++] = i;
				i = parent[i];
			}
			while (k > 0) {
				parent[help[--k]] = i;
			}
			return i;
		}

		private void union(int r1, int c1, int r2, int c2) {
			if (r1 < 0 || r1 == row || r2 < 0 || r2 == row || c1 < 0 || c1 == col || c2 < 0 || c2 == col) {
				return;
			}
			int i1 = index(r1, c1);
			int i2 = index(r2, c2);
			if (size[i1] == 0 || size[i2] == 0) {
				return;
			}
			int f1 = find(i1);
			int f2 = find(i2);
			if (f1 != f2) {
				if (size[f1] >= size[f2]) {
					parent[f2] = f1;
					size[f1] += size[f2];
				} else {
					parent[f1] = f2;
					size[f2] += size[f1];
				}
				sets--;
			}
		}

		public int connect(int r, int c) {
			int index = index(r, c);
			if (size[index] == 0) {
				parent[index] = index;
				size[index] = 1;
				sets++;
				union(r, c, r - 1, c);
				union(r, c, r + 1, c);
				union(r, c, r, c - 1);
				union(r, c, r, c + 1);
			}
			return sets;
		}

	}

	// 课上讲的如果m*n比较大，会经历很重的初始化，而k比较小，怎么优化的方法
	public static List<Integer> numIslands22(int m, int n, int[][] positions) {
		UnionFind2 uf = new UnionFind2();
		List<Integer> ans = new ArrayList<>();
		for (int[] position : positions) {
			ans.add(uf.connect(position[0], position[1]));
		}
		return ans;
	}

	public static class UnionFind2 {
		private HashMap<String, String> parent;
		private HashMap<String, Integer> size;
		private ArrayList<String> help;
		private int sets;

		public UnionFind2() {
			parent = new HashMap<>();
			size = new HashMap<>();
			help = new ArrayList<>();
			sets = 0;
		}

		private String find(String cur) {
			while (!cur.equals(parent.get(cur))) {
				help.add(cur);
				cur = parent.get(cur);
			}
			for (String str : help) {
				parent.put(str, cur);
			}
			help.clear();
			return cur;
		}

		private void union(String s1, String s2) {
			if (parent.containsKey(s1) && parent.containsKey(s2)) {
				String f1 = find(s1);
				String f2 = find(s2);
				if (!f1.equals(f2)) {
					int size1 = size.get(f1);
					int size2 = size.get(f2);
					String big = size1 >= size2 ? f1 : f2;
					String small = big == f1 ? f2 : f1;
					parent.put(small, big);
					size.put(big, size1 + size2);
					sets--;
				}
			}
		}

		public int connect(int r, int c) {
			String key = String.valueOf(r) + "_" + String.valueOf(c);
			if (!parent.containsKey(key)) {
				parent.put(key, key);
				size.put(key, 1);
				sets++;
				String up = String.valueOf(r - 1) + "_" + String.valueOf(c);
				String down = String.valueOf(r + 1) + "_" + String.valueOf(c);
				String left = String.valueOf(r) + "_" + String.valueOf(c - 1);
				String right = String.valueOf(r) + "_" + String.valueOf(c + 1);
				union(up, key);
				union(down, key);
				union(left, key);
				union(right, key);
			}
			return sets;
		}

	}


	public static List<Integer> numIslands23(int m, int n, int[][] positions) {
		List<Integer> ans = new ArrayList<>();
		int[][] arr = new int[m][n];
		for (int[] position : positions) {
			int sum = 0;
			if (arr[position[0]][position[1]] != 0) {
				continue;
			} else {
				arr[position[0]][position[1]] = 1;
			}
			for (int i =0 ; i < m; i++) {
				for (int j = 0 ; j < n; j++) {
					if (arr[i][j] == 1) {
						if (!(i - 1 >= 0 && arr[i - 1][j] == 2
								|| i + 1 < m && arr[i + 1][j] == 2
								|| j - 1 >= 0 && arr[i][j - 1] == 2
								|| j + 1 < n && arr[i][j + 1] == 2)) {
							sum++;
						}
						infect(arr, i, j);
					}
				}
			}
			ans.add(sum);
		}
		return ans;
	}

	public static void infect(int[][] arr, int i, int j) {
		if (i < 0 || j < 0 || i == arr.length || j == arr[0].length || arr[i][j] == 1) {
			return;
		}
		arr[i][j] = 2;
		infect(arr, i + 1, j);
		infect(arr, i - 1, j);
		infect(arr, i, j - 1);
		infect(arr, i, j + 1);
	}
}
