package class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 */
public class Code01_UnionFind {

	// Node类是把value包了一层
	public static class Node<V> {
		V value;
		
		public Node(V v) {
			value = v;
		}
	}
	
	public static class UnionSet<V> {
		// 对应表，拿V去查它的node
		public HashMap<V, Node<V>> nodes;
		// 一个节点它的父亲节点是谁，作为一组记录
		public HashMap<Node<V>, Node<V>> parents;
		// 只有当一个点，它是某一个集合代表点的情况下，才有记录
		public HashMap<Node<V>, Integer> sizeMap;
		
		// 初始化
		public UnionSet(List<V> values) {
			for (V value : values) {
				Node<V> node = new Node<>(value);
				nodes.put(value, node); //存入对应表
				parents.put(node, node); //初始，每个节点的父节点是自己
				sizeMap.put(node, 1); //每一个点一开始都是代表点，所以都有1个点
			}
		}
		
		/**
		 * 从点cur开始，一直往上找，找到不能再往上的代表点，返回
		 * @param cur
		 * @return
		 */
		public Node<V> findFather(Node<V> cur) {
			Stack<Node<V>> path = new Stack<>();
			while (cur != parents.get(cur)) {
				path.push(cur);
				cur = parents.get(cur);
			}
			// cur头节点
			while (!path.isEmpty()) {
				parents.put(path.pop(), cur); //优化，路径压缩，把途中所有节点都指向最终的节点
			}
			return cur;
		}
		
		/**
		 * 返回a点和b点的代表点是否一样
		 * @param a
		 * @param b
		 * @return
		 */
		public boolean isSameSet(V a, V b) {
			if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
				return false;
			}
			return findFather(nodes.get(a)) == findFather(nodes.get(b));
		}
		
		/**
		 * 将a背后的集合，和b背后的集合合并在一起
		 * @param a
		 * @param b
		 */
		public void union(V a, V b) {
			if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
				return;
			}
			Node<V> aHead = findFather(nodes.get(a)); //找到a的代表点
			Node<V> bHead = findFather(nodes.get(b)); //找到b的代表点
			if (aHead != bHead) {
				int aSetSize = sizeMap.get(aHead); //找到a的集合大小
				int bSetSize = sizeMap.get(bHead); //找到b的集合大小
				if (aSetSize >= bSetSize) {
					parents.put(bHead, aHead); //小集合的头节点，直接把父亲节点改成aHead
					sizeMap.put(aHead, aSetSize + bSetSize); //修改aHead的集合大小
					sizeMap.remove(bHead); //移除bHead
				} else {
					parents.put(aHead, bHead);
					sizeMap.put(bHead, aSetSize + bSetSize);
					sizeMap.remove(aHead);
				}
			}
		}
	}
}
