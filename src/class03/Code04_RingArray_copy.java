package class03;

public class Code04_RingArray_copy {

    public static class MyQueue {
        public int[] arr;
        public int pushi;
        public int popi;
        public int size;
        public int limit;

        public MyQueue(int i) {
            arr = new int[i];
            limit = i;
            pushi = 0;
            popi = 0;
            size = 0;
        }


        public void add(int v) {
            if (size == limit) {
                throw new RuntimeException("队列满了");
            }
            arr[pushi] = v;
            size++;
            pushi = nextIndex(pushi);
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("队列空了");
            }
            int res = arr[popi];
            size--;
            popi = nextIndex(pushi);
            return res;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int nextIndex(int i) {
            return i > limit - 1 ? i + 1 : 0;
        }
    }

}
