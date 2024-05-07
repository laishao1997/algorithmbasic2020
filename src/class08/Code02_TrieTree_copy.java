package class08;

import java.util.HashMap;
import java.util.Map;

// 该程序完全正确
public class Code02_TrieTree_copy {

	// 适用于只有英文字母的情况
	public static class Node1 {
		public int pass;
		public int end;
		public Node1[] nexts;

		public Node1() {
			this.pass = 0;
			this.end = 0;
			nexts = new Node1[26];
		}
	}

	public static class Trie1 {
		public Node1 root;

		public Trie1() {
			root = new Node1();
		}

		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] words = word.toCharArray();
			Node1 node = root;
			node.pass++;
			int path = 0;
			for (int i = 0; i < words.length; i++) {
				path = words[i] - 'a';
				if (node.nexts[path] == null) {
					node.nexts[path] = new Node1();
				}
				node = node.nexts[path];
				node.pass++;
			}
			node.end++;
		}

		public void delete(String word) {
			if (search(word) <= 0) {
				return;
			}
			char[] words = word.toCharArray();
			Node1 node = root;
			root.pass--;
			int path = 0;
			for (int i = 0; i < words.length; i++) {
				path =  words[i] - 'a';
				if (--node.nexts[path].pass == 0) {
					node.nexts[path] = null;
					return;
				}
				node = node.nexts[path];
			}
			node.end--;
		}

		// word字符串 出现过几次
		public int search(String word) {
			if (word == null) {
				return 0;
			}
			char[] words = word.toCharArray();
			Node1 node = root;
			int path = 0;
			for (int i = 0; i < words.length; i++) {
				path = words[i] - 'a';
				if (node.nexts[path] == null) {
					return 0;
				}
				node = node.nexts[path];
			}
			return node.end;
		}

		// 以word字符串为前缀的字符出现过几次
		public int prefixNumber(String word) {
			if (word == null) {
				return 0;
			}
			char[] words = word.toCharArray();
			Node1 node = root;
			int path = 0;
			for (int i = 0; i < words.length; i++) {
				path = words[i] - 'a';
				if (node.nexts[path] == null) {
					return 0;
				}
				node = node.nexts[path];
			}
			return node.pass;
		}
	}

	public static class Node2 {
		public int pass;
		public int end;
		public Map<Integer, Node2> nexts;

		public Node2() {
			this.pass = 0;
			this.end = 0;
			this.nexts = new HashMap<>();
		}
	}

	public static class Trie2 {
		private Node2 root;

		public Trie2() {
			root = new Node2();
		}

		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] words = word.toCharArray();
			Node2 cur = root;
			cur.pass++;
			int index = 0;
			for (int i = 0; i < words.length; i++) {
				index = (int) words[i];
				if (!cur.nexts.containsKey(index)) {
					cur.nexts.put(index, new Node2());
				}
				cur = cur.nexts.get(index);
				cur.pass++;
			}
			cur.end++;
		}

		public void delete(String word) {
			if (search(word) != 0) {
				char[] words = word.toCharArray();
				Node2 cur = root;
				cur.pass--;
				int index = 0;
				for (int i = 0; i < words.length; i++) {
					index = (int) words[i];
					if (--cur.nexts.get(index).pass == 0) {
						cur.nexts.remove(index);
						return;
					}
					cur = cur.nexts.get(index);
				}
				cur.end--;
			}
		}

		// word这个单词之前加入过几次
		public int search(String word) {
			if (word == null) {
				return 0;
			}
			char[] words = word.toCharArray();
			Node2 cur = root;
			int index = 0;
			for (int i = 0; i < words.length; i++) {
				index = (int) words[i];
				if (!cur.nexts.containsKey(index)) {
					return 0;
				}
				cur = cur.nexts.get(index);
			}
			return cur.end;
		}

		// 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] pres = pre.toCharArray();
			Node2 cur = root;
			int index = 0;
			for (int i = 0; i < pres.length; i++) {
				index = (int) pres[i];
				if (!cur.nexts.containsKey(index)) {
					return 0;
				}
				cur = cur.nexts.get(index);
			}
			return cur.pass;
		}
	}

	public static class Right {

		private HashMap<String, Integer> box;

		public Right() {
			box = new HashMap<>();
		}

		public void insert(String word) {
			if (!box.containsKey(word)) {
				box.put(word, 1);
			} else {
				box.put(word, box.get(word) + 1);
			}
		}

		public void delete(String word) {
			if (box.containsKey(word)) {
				if (box.get(word) == 1) {
					box.remove(word);
				} else {
					box.put(word, box.get(word) - 1);
				}
			}
		}

		public int search(String word) {
			if (!box.containsKey(word)) {
				return 0;
			} else {
				return box.get(word);
			}
		}

		public int prefixNumber(String pre) {
			int count = 0;
			for (String cur : box.keySet()) {
				if (cur.startsWith(pre)) {
					count += box.get(cur);
				}
			}
			return count;
		}
	}

	// for test
	public static String generateRandomString(int strLen) {
		char[] ans = new char[(int) (Math.random() * strLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			int value = (int) (Math.random() * 6);
			ans[i] = (char) (97 + value);
		}
		return String.valueOf(ans);
	}

	// for test
	public static String[] generateRandomStringArray(int arrLen, int strLen) {
		String[] ans = new String[(int) (Math.random() * arrLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = generateRandomString(strLen);
		}
		return ans;
	}

	public static void main(String[] args) {
		int arrLen = 100;
		int strLen = 20;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			String[] arr = generateRandomStringArray(arrLen, strLen);
			Trie1 trie1 = new Trie1();
			Trie2 trie2 = new Trie2();
			Right right = new Right();
			for (int j = 0; j < arr.length; j++) {
				double decide = Math.random();
				if (decide < 0.25) {
					trie1.insert(arr[j]);
					trie2.insert(arr[j]);
					right.insert(arr[j]);
				} else if (decide < 0.5) {
					trie1.delete(arr[j]);
					trie2.delete(arr[j]);
					right.delete(arr[j]);
				} else if (decide < 0.75) {
					int ans1 = trie1.search(arr[j]);
					int ans2 = trie2.search(arr[j]);
					int ans3 = right.search(arr[j]);
					if (ans1 != ans2 || ans2 != ans3) {
						System.out.println("Oops!");
					}
				} else {
					int ans1 = trie1.prefixNumber(arr[j]);
					int ans2 = trie2.prefixNumber(arr[j]);
					int ans3 = right.prefixNumber(arr[j]);
					if (ans1 != ans2 || ans2 != ans3) {
						System.out.println("Oops!");
					}
				}
			}
		}
		System.out.println("finish!");

	}

}
