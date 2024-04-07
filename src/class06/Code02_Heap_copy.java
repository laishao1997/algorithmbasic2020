package class06;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code02_Heap_copy {

	public static class MyMaxHeap {
		private int[] heap;
		private final int limit;
		private int heapSize;

		public MyMaxHeap(int limit) {
			heap = new int[limit];
			this.limit = limit;
			heapSize = 0;
		}

		public boolean isEmpty() {
			return heapSize == 0;
		}

		public boolean isFull() {
			return heapSize == limit;
		}

		public void push(int v) {
			if (isFull()) {
				throw new RuntimeException("满了");
			}
			heap[heapSize] = v;
			heapInsert(heap, heapSize++);
		}

		// 大根堆
		public int pop() {
			if (isEmpty()) {
				throw new RuntimeException("空了");
			}
			int res = heap[0];
			swap(heap, 0, --heapSize);
			heapify(heap, 0, heapSize);
			return res;
		}

		// 自上而下调整
		public void heapInsert(int[] heap, int index) {
			while (index > 0 && heap[index] > heap[(index - 1) / 2]) {
				swap(heap, index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		// 自下而上调整
		public void heapify(int[] heap, int index, int size) {
			int left = index * 2 + 1;
			while (left < size) {
				int right = left + 1;
				int large = right < size && heap[right] > heap[left] ? right : left;
				large = heap[index] > heap[large] ? index : large;
				if (large == index) {
					break;
				}
				swap(heap, index, large);
				index = large;
				left = index * 2 + 1;
			}
		}

		private void swap(int[] arr, int i, int j) {
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}

	}

	public static class RightMaxHeap {
		private int[] arr;
		private final int limit;
		private int size;

		public RightMaxHeap(int limit) {
			arr = new int[limit];
			this.limit = limit;
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public boolean isFull() {
			return size == limit;
		}

		public void push(int value) {
			if (size == limit) {
				throw new RuntimeException("heap is full");
			}
			arr[size++] = value;
		}

		public int pop() {
			int maxIndex = 0;
			for (int i = 1; i < size; i++) {
				if (arr[i] > arr[maxIndex]) {
					maxIndex = i;
				}
			}
			int ans = arr[maxIndex];
			arr[maxIndex] = arr[--size];
			return ans;
		}

	}

	public static class MyComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}

	}

	public static void main(String[] args) {

		// 小根堆
		PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
		heap.add(5);
		heap.add(5);
		heap.add(5);
		heap.add(3);
		// 5 , 3
		System.out.println(heap.peek());
		heap.add(7);
		heap.add(0);
		heap.add(7);
		heap.add(0);
		heap.add(7);
		heap.add(0);
		System.out.println(heap.peek());
		while (!heap.isEmpty()) {
			System.out.println(heap.poll());
		}

		int value = 1000;
		int limit = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			int curLimit = (int) (Math.random() * limit) + 1;
			MyMaxHeap my = new MyMaxHeap(curLimit);
			RightMaxHeap test = new RightMaxHeap(curLimit);
			int curOpTimes = (int) (Math.random() * limit);
			for (int j = 0; j < curOpTimes; j++) {
				if (my.isEmpty() != test.isEmpty()) {
					System.out.println("Oops!");
				}
				if (my.isFull() != test.isFull()) {
					System.out.println("Oops!");
				}
				if (my.isEmpty()) {
					int curValue = (int) (Math.random() * value);
					my.push(curValue);
					test.push(curValue);
				} else if (my.isFull()) {
					if (my.pop() != test.pop()) {
						System.out.println("Oops!");
					}
				} else {
					if (Math.random() < 0.5) {
						int curValue = (int) (Math.random() * value);
						my.push(curValue);
						test.push(curValue);
					} else {
						if (my.pop() != test.pop()) {
							System.out.println("Oops!");
						}
					}
				}
			}
		}
		System.out.println("finish!");

	}

}
