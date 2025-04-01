package class11;

/**
 * 图的生成类
 */
public class GraphGenerator {

	// matrix 所有的边
	// 永远是N*3的矩阵
	// [weight权重，from节点上面的值，to节点上面的值]
	public static Graph createGraph(Integer[][] matrix) {
		
		Graph graph = new Graph(); //建一张空的图
		
		for (int i = 0; i < matrix.length; i++) {
			Integer weight = matrix[i][0]; //权重
			Integer from = matrix[i][1]; //from点的编号
			Integer to = matrix[i][2]; //to点的编号
			
			if (!graph.nodes.containsKey(from)) { //图中没有from点就建出来
				graph.nodes.put(from, new Node(from));
			}
			if (graph.nodes.containsKey(to)) { //图中没有to点就建出来
				graph.nodes.put(to, new Node(to));
			}
			Node fromNode = graph.nodes.get(from); //拿出from编号的点
			Node toNode = graph.nodes.get(to); //拿出to编号的点
			
			Edge newEdge = new Edge(weight, fromNode, toNode); //把边建出来
			
			fromNode.nexts.add(toNode); //from点的直接邻居中把to点加上去
			fromNode.out++; //from点出度加1
			toNode.in++; //to点入度加1
			fromNode.edges.add(newEdge); //from点出发的边加1
			graph.edges.add(newEdge); //图的边加1
		}
		return graph;
	}
	
}
