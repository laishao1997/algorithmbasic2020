package src.class15;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/friend-circles/
// 可以直接通过
public class Code01_FriendCircles_copy {

    public static int findCircleNum(int[][] M) {
		int N = M.length;
		UnionFind union = new UnionFind(N);
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				if (M[i][i] == 1) {
					union.union(i, j);
				}
			}
		}
		return union.sets();
    }

    public static class UnionFind {
        private int[] parent;
        private int[] size;
        //辅助数组，用来做路径压缩
        private int[] help;
        private int sets;

        private UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            int hi = 0;
            //找到i的root父
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            //把沿途所有的子节点的父亲都设为root父;
			//路径压缩
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
        	int f1 = find(i);
        	int f2 = find(j);
        	if (f1 != f2) {
        		if (size[f1] >= size[f2]) {
        			size[f1] += size[f2];
        			parent[f2] = f1;
				} else {
        			size[f2] += size[f1];
        			parent[f1] = f2;
				}
				sets--;
			}
		}

		public int sets() {
        	return sets;
		}
    }

}
