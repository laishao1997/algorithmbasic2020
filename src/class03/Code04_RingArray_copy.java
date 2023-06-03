package src.class03;

public class Code04_RingArray_copy {

	public static class MyQueue {
		private int[] arr;
		private int pushi;
		private int popi;
		private int size;
		private int limit;

		public MyQueue(int limit) {
			arr = new int[limit];
			pushi = 0;
			popi = 0;
			size = 0;
			this.limit = limit;
		}

		public void push(int value) {
			if (limit == size) {
				System.out.println("满了，不能放了");
				return;
			}
			size++;
			arr[pushi] = value;
			pushi = nextIndex(pushi);
		}

		public int pop() {
			if (size == 0) {
				System.out.println("空的，没有数");
				return -1;
			}
			size--;
			int ans = arr[popi];
			popi = nextIndex(popi);
			return ans;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		// 如果现在的下标是i，返回下一个位置
		private int nextIndex(int i) {
			return i < limit - 1 ? i + 1 : 0;
		}

	}

}
