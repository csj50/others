package class05;

import java.util.HashMap;

/**
 * 用两种方式实现前缀树
 */
public class Code02_TrieTree {

	//用数组实现
	public static class Node1 {
		public int pass;
		public int end;
		public Node1[] nexts;
		
		public Node1() {
			pass = 0;
			end = 0;
			//nexts[i] == null，i方向的路不存在
			//nexts[i] != null，i方向的路存在
			nexts = new Node1[26]; //26个字母？每个节点都有26条路
		}
	}
	
	//第一种前缀树
	public static class Trie1 {
		//根节点
		private Node1 root;
		
		private Trie1() {
			root = new Node1(); //新建出头结点
		}
		
		public void insert(String word) {
			if (word == null) {
				return;
			}
			
			char[] chs = word.toCharArray();
			Node1 node = root; //从头结点出发
			node.pass++;
			int index = 0;
			for (int i = 0; i < chs.length; i++) { //从左往右遍历字符
				index = chs[i] - 'a'; //由字符，对应成走向哪条路
				if (node.nexts[index] == null) {
					node.nexts[index] = new Node1(); //新建一个节点
				}
				node = node.nexts[index]; //node指向这个新建的节点
				node.pass++;
			}
			node.end++;
		}
		
		public void delete (String word) {
			if (search(word) != 0) {
				char[] chs = word.toCharArray();
				Node1 node = root;
				node.pass--;
				int index = 0;
				for (int i = 0; i < chs.length; i++) {
					index = chs[i] - 'a';
					if (--node.nexts[index].pass == 0) { //P值变成0，表示它下面的子树可以不要了
						node.nexts[index] = null;
						return;
					}
					node = node.nexts[index];
				}
				node.end--;
			}
		}
		
		//查询word这个单词加入过几次
		public int search(String word) {
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			Node1 node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.end;
		}
		
		//所有加入的字符串中，有几个是以pre这个字符串作为前缀的
		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			Node1 node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = chs[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.pass;
		}
		
	}
	
	//用哈希表实现
	public static class Node2 {
		public int pass;
		public int end;
		//通过哈希表的形式
		public HashMap<Integer, Node2> nexts;
		
		public Node2() {
			pass = 0;
			end = 0;
			nexts = new HashMap<>();
		}
	}
	
	//第二种前缀树
	public static class Trie2 {
		//根节点
		private Node2 root;
		
		public Trie2() {
			root = new Node2();
		}
		
		public void insert(String word) {
			if (word == null) {
				return;
			}
			char[] chs = word.toCharArray();
			Node2 node = root;
			node.pass++;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = (int) chs[i];
				if (!node.nexts.containsKey(index)) {
					node.nexts.put(index, new Node2());
				}
				node = node.nexts.get(index);
				node.pass++;
			}
			node.end++;
		}
		
		public void delete(String word) {
			if (search(word) != 0) {
				char[] chs = word.toCharArray();
				Node2 node = root;
				node.pass--;
				int index = 0;
				for (int i = 0; i < chs.length; i++) {
					index = (int) chs[i];
					if (--node.nexts.get(index).pass == 0) {
						node.nexts.remove(index);
						return;
					}
					node = node.nexts.get(index);
				}
				node.end--;
			}
		}
		
		//查询word这个单词加入过几次
		public int search(String word) {
			if (word == null) {
				return 0;
			}
			char[] chs = word.toCharArray();
			Node2 node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = (int) chs[i];
				if (!node.nexts.containsKey(index)) {
					return 0;
				}
				node = node.nexts.get(index);
			}
			return node.end;
		}
		
		//所有加入的字符串中，有几个是以pre这个字符串作为前缀的
		public int prefixNumber(String pre) {
			if (pre == null) {
				return 0;
			}
			char[] chs = pre.toCharArray();
			Node2 node = root;
			int index = 0;
			for (int i = 0; i < chs.length; i++) {
				index = (int) chs[i];
				if (!node.nexts.containsKey(index)) {
					return 0;
				}
				node = node.nexts.get(index);
			}
			return node.pass;
		}
	}
	
	/**
	 * 绝对正确的对数器（纯暴力的方法）
	 */
	public static class Right {
		
		private HashMap<String, Integer> box;
		
		private Right() {
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
				} else if (decide < 0.7) {
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
