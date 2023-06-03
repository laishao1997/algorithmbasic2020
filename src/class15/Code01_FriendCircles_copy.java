package src.class15;

// 本题为leetcode原题
// 测试链接：https://leetcode.com/problems/friend-circles/
// 可以直接通过
public class Code01_FriendCircles_copy {

    public static int findCircleNum(int[][] M) {
        if (M == null) {
            return 0;
        }
        UnionFind unionFind = new UnionFind(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M.length; j++) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets();
    }

    public static class UnionFind {
        //每个节点的父节点；i位置的父节点为parents[i]
        public int[] parents;
        //代表节点的size
        public int[] sizeArr;
        //用作栈
        public int[] help;
        public int size;

        private UnionFind(int N) {
            parents = new int[N];
            sizeArr = new int[N];
            help = new int[N];
            size = N;
            for (int i = 0 ; i < N; i++) {
                parents[i] = i;
                sizeArr[i] = 1;
            }
        }

        public int find(int i) {
            int index = 0;
            while (i != parents[i]) {
                help[index++] = i;
                i = parents[i];
            }
            //扁平化处理
            while (index-- >= 0) {
                parents[help[index--]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int findi = find(i);
            int findj = find(j);
            if (findi != findj) {
                if (sizeArr[i] >= sizeArr[j]) {
                    parents[j] = i;
                    sizeArr[i] += sizeArr[j];
                } else {
                    parents[i] = j;
                    sizeArr[j] += sizeArr[i];
                }
            }
            size--;
		}

		public int sets() {
        	return size;
		}
    }

}
