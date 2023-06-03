package src.class06;

import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code02_Heap_copy_copy {

    public static class MyMaxHeap {
        private int[] heap;
        private int heapSize;
        private int limit;

        public MyMaxHeap(int value) {
            heap = new int[value];
            this.heapSize = 0;
            this.limit = value;
        }

        public boolean isFull() {
			return limit == heapSize;
        }

        public boolean isEmpty() {
        	return heapSize == 0;
        }

        public void push(int value) {
        	heap[heapSize] = value;
        	heapInsert(heap, heapSize++);
		}

		public int pop() {
        	int ans = heap[0];
        	swap(heap, 0, --heapSize);
        	heapfy(heap, 0, heapSize);
        	return ans;
		}

		public void heapInsert(int[] arr, int index) {
        	//index位置跟父位置比较大小
        	while (arr[index] > arr[(index - 1) / 2]) {
				swap(arr, index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		public void heapfy(int[] arr, int index, int heapSize) {
        	//左孩子
        	int left = index * 2 + 1;
        	while (left < heapSize) { //没超过heapSize,即有左孩子
        		//比较左右孩子谁比较大
        		int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
        		//比较较大的那个孩子与当前节点的值比较谁大
				largest = arr[largest] > arr[index] ? largest : index;
				//说明上一步，index位置的值比较大，不需要再进行交换
        		if (largest == index) {
        			break;
				}
        		swap(arr, largest, index);
        		//index与其子孩子交换之后，调整index来到其子孩子的位置，继续往后调整
        		index = largest;
        		left = index * 2 + 1;
			}
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
