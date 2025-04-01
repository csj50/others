package class11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 图的拓扑排序
 */
public class Code03_TopologySort {

	// 返回排序之后node结果
	public static List<Node> sortedTopoloy(Graph graph) {
		
		// key：某一个node
		// value：剩余的入度
		HashMap<Node, Integer> inMap = new HashMap<>();
		// 剩余入度为0的点，才能进这个队列
		Queue<Node> zeroInQueue = new LinkedList<>();
		
		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in); //记录所有节点
			if (node.in == 0) {
				zeroInQueue.add(node); //初始情况下，入度为0的节点
			}
		}
		
		// 拓扑排序的结果，依次加入result
		List<Node> result = new ArrayList<>();
		
		while (!zeroInQueue.isEmpty()) {
			Node cur = zeroInQueue.poll();
			result.add(cur); //进入zeroInQueue的点，弹出放到结果里去
			
			for (Node next : cur.nexts) {
				inMap.put(next, inMap.get(next) - 1); //cur节点消掉了，它的邻居入度要减1
				if (inMap.get(next) == 0) {
					zeroInQueue.add(next); //如果减1后，邻居节点入度也为0，也放入zeroInQueue
				}
			}
		}
		return result;
	}
}
