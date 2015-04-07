import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

public class HighwayOptimizer {
	public static void main(String[] args) throws IOException {
		Graph<String> graph = GraphFactory.buildGraph(args[0]);
		Graph<String> mst = buildMst(graph);
		System.out.print(mst.getTotalWeight());
	}

	private static Graph<String> buildMst(Graph<String> graph) {
		Graph<String> mst = new Graph<String>();
		Queue<Edge<String>> edgesList = new PriorityQueue<Edge<String>>();
		Node<String> choosenNode = graph.getRandomNode();
		edgesList.addAll(choosenNode.getEdges());
		mst.addNode(choosenNode.getNodeElement());
		while(!edgesList.isEmpty() && mst.numberOfNodes() < graph.numberOfNodes()) {
			Edge<String> choosenEdge = edgesList.poll();
			Node<String> addingNode = edgeOk(mst, choosenEdge);
			if(addingNode != null) {
				mst.addNode(addingNode.getNodeElement());
				mst.addEdge(choosenEdge);
				edgesList.addAll(addingNode.getEdges());
			}
		}
		return mst;
	}

	private static Node<String> edgeOk(Graph<String> mst, Edge<String> choosenEdge) {
		boolean node1InGraph = mst.containsNode(choosenEdge.getVertex1().getNodeElement());
		boolean node2InGraph = mst.containsNode(choosenEdge.getVertex2().getNodeElement());
		if(node1InGraph && node2InGraph) {
			return null;
		}
		if(!node1InGraph) {
			return choosenEdge.getVertex1();
		}
		if(!node2InGraph) {
			return choosenEdge.getVertex2();
		}
		return null;
	}

}
