package class11;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * 最小生成树K算法
 */
public class Code04_Kruskal {

	public static class UnionFind {
		
		//key：某一个节点
		//value：key节点往上的节点
		private HashMap<Node, Node> fatherMap;
		
		//key：某一个集合的代表节点
		//value：key所在集合的节点个数
		private HashMap<Node, Integer> sizeMap;
		
		public UnionFind() {
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
		}
		
		public void makeSets(Collection<Node> nodes) {
			fatherMap.clear();
			sizeMap.clear();
			for (Node node : nodes) {
				fatherMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}
		
		private Node findFather(Node n) {
			Stack<Node> path = new Stack<>();
			while (n != fatherMap.get(n)) {
				path.add(n);
				n = fatherMap.get(n);
			}
			while (!path.isEmpty()) {
				fatherMap.put(path.pop(), n); //扁平化
			}
			return n;
		}
		
		public boolean isSameSet(Node a, Node b) {
			return findFather(a) == findFather(b);
		}
		
		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aDai = findFather(a);
			Node bDai = findFather(b);
			if (aDai != bDai) {
				int aSetSize = sizeMap.get(aDai);
				int bSetSize = sizeMap.get(bDai);
				if (aSetSize <= bSetSize) {
					fatherMap.put(aDai, bDai);
					sizeMap.put(bDai, aSetSize + bSetSize);
					sizeMap.remove(aDai);
				} else {
					fatherMap.put(bDai, aDai);
					sizeMap.put(aDai, aSetSize + bSetSize);
					sizeMap.remove(bDai);
				}
			}
		}
	}
	
	/**
	 * 边排序
	 */
	public static class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight; //权值小的边排在左边
		}
	}
	
	public static Set<Edge> kruskalMST(Graph graph) {
		UnionFind unionFind = new UnionFind();
		unionFind.makeSets(graph.nodes.values()); //先把所有的点各自是自己的集合
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
		
		for (Edge edge : graph.edges) {
			priorityQueue.add(edge); //把所有的边加入堆里
		}
		
		Set<Edge> result = new HashSet<>();
		
		while (!priorityQueue.isEmpty()) {
			Edge edge = priorityQueue.poll(); //每一次弹出一条边
			//如果边的左右两端，不是一个集合
			if (!unionFind.isSameSet(edge.from, edge.to)) {
				result.add(edge);
				unionFind.union(edge.from, edge.to); //合并from和to集合
			}
		}
		return result;
	}
}
