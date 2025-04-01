package class11;

/**
 * 边结构的描述
 */
public class Edge {
	public int weight; //权重
	public Node from; //边的起点
	public Node to; //边的终点
	
	public Edge(int weight, Node from, Node to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}
}