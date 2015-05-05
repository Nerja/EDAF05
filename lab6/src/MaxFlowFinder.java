import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class MaxFlowFinder {
	public static void main(String[] args) throws IOException {
		Map<Integer, List<FlowArc>> graph = RailwayFactory.getGraph(args[0]);
		int maxFlow = findMaxFlow(graph, Integer.parseInt(args[1]),
				Integer.parseInt(args[2]));
		System.out.print("MAX_FLOW:" + maxFlow);
	}

	private static int findMaxFlow(Map<Integer, List<FlowArc>> graph,
			int source, int terminal) {
		Map<Integer, List<ResidualArc>> rg = buildResidualGraph(graph);
		List<ResidualArc> stPath = findSTPath(rg, source, terminal);
		while (!stPath.isEmpty()) {
			int b = bottleneck(stPath);
			for (ResidualArc rarc : stPath)
				rarc.apply(b);
			rg = buildResidualGraph(graph);
			stPath = findSTPath(rg, source, terminal);
		}
		return netFlowOut(graph, source);
	}

	private static int netFlowOut(Map<Integer, List<FlowArc>> graph, int node) {
		int netFlowOut = 0;
		for (FlowArc farc : graph.get(node))
			netFlowOut += farc.getFlow();
		return netFlowOut;
	}

	private static int bottleneck(List<ResidualArc> stPath) {
		int min = Integer.MAX_VALUE;
		for (ResidualArc arc : stPath)
			if (arc.getCapacity() < min)
				min = arc.getCapacity();
		return min;
	}

	public static Map<Integer, List<ResidualArc>> buildResidualGraph(
			Map<Integer, List<FlowArc>> graph) {
		Map<Integer, List<ResidualArc>> residual = Util
				.getEmptyResidualGraph(graph.size());
		for (List<FlowArc> arcList : graph.values()) {
			for (FlowArc arc : arcList)
				addResidualArcs(arc, residual);
		}
		return residual;
	}

	private static void addResidualArcs(FlowArc arc,
			Map<Integer, List<ResidualArc>> residual) {
		if (arc.getFlow() > 0) {
			ResidualArc undoArc = new ResidualArc(arc.getTarget(),
					arc.getSource(), arc.getFlow(), arc, false);
			residual.get(arc.getTarget()).add(undoArc);
		}
		if (arc.getCapacity() - arc.getFlow() > 0) {
			ResidualArc forArc = new ResidualArc(arc.getSource(),
					arc.getTarget(), arc.getCapacity() - arc.getFlow(), arc,
					true);
			residual.get(arc.getSource()).add(forArc);
		}
	}

	private static List<ResidualArc> findSTPath(
			Map<Integer, List<ResidualArc>> graph, int source, int terminal) {
		LinkedList<ResidualArc> path = new LinkedList<ResidualArc>();

		Map<Integer, ResidualArc> backtrack = new HashMap<Integer, ResidualArc>();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(source);
		boolean[] discovered = new boolean[graph.size()];
		discovered[source] = true;
		boolean found = false;
		while (!found && !queue.isEmpty()) {
			int checking = queue.poll();
			found = checking == terminal;
			if (!found) {
				for (ResidualArc arc : graph.get(checking)) {
					if (!discovered[arc.getTarget()]) {
						backtrack.put(arc.getTarget(), arc);
						queue.offer(arc.getTarget());
						discovered[arc.getTarget()] = true;
					}
				}
			}
		}
		if (found) {
			int node = terminal;
			while (node != source) {
				path.addFirst(backtrack.get(node));
				node = backtrack.get(node).getSource();
			}
		}
		return path;
	}
}
