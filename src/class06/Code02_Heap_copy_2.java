package src.class06;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code02_Heap_copy_2 {

    public static class MyMaxHeap {
        private int[] heap;
        private int limit;
        private int size;

        public MyMaxHeap(int num) {
            heap = new int[num];
            limit = num;
            size = 0;
        }

        public boolean isFull() {
            return limit == size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void add(int value) {
            if (isFull()) {
                System.out.println("满了");
                return;
            }
            heap[size] = value;
            heapInser(value, size++);
        }

        private void heapInser(int value, int index) {
            while (heap[(index - 1) / 2] < heap[value]) {
                swap(heap, index, (index - 1) / 2 );
                index = (index - 1) / 2;
            }
        }

        public int poll() {
            int ans = heap[0];
            swap(heap, 0, --size);
            heapify(heap, 0, size);
            return ans;
        }

        private void heapify(int[] arr, int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int biger = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
                biger = arr[index] > arr[biger] ? index : biger;
                if (biger == index) {
                    return;
                }
                swap(arr, index, biger);
                index = biger;
                left = index * 2 + 1;
            }
        }

        public int peek() {
            return heap[0];
        }

		public void swap(int[] arr, int i, int j) {
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
                    my.add(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.poll() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.add(curValue);
                        test.push(curValue);
                    } else {
                        if (my.poll() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }

}
