import java.io.*;
import java.util.*;

class pick_up_sticks {

	public static boolean test = false;
	public static HashMap<Integer, HashSet<Integer>> dag = new HashMap<Integer, HashSet<Integer>>();
	public static int[] visited; // 1: temporarily visited; 2: permanently visited.
	public static Stack<Integer> result = new Stack<Integer>();

	public static void dfs(int u) {
		if (test) System.out.println("Visiting " + u);
		if (visited[u] == 2) {
			if (test) System.out.println("Visited");
			return;
		}
		if (visited[u] == 1) {
			System.out.println("IMPOSSIBLE");
			System.exit(0);
		}
		visited[u] = 1;
		if (dag.containsKey(u)) {
			for (int v : dag.get(u)) dfs(v);
		}
		visited[u] = 2;
		result.push(u);
		if (test) System.out.println("Pushing " + u);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String temp[] = line.split(" ");
		int num_vert = Integer.parseInt(temp[0]);
		visited = new int[num_vert + 1];
		int u, v;
		while ((line = br.readLine()) != null) {
			temp = line.split(" ");
			u = Integer.parseInt(temp[0]);
			v = Integer.parseInt(temp[1]);
			if (!dag.containsKey(u)) dag.put(u, new HashSet<Integer>());
			dag.get(u).add(v);
		}
		if (test) System.out.println(dag);
		for (u = 1; u < num_vert+1; u++) {
			if (visited[u] == 0) dfs(u);
		}
		StringBuilder sb = new StringBuilder();
		while (!result.isEmpty()) {
			sb.append(result.pop().toString());
			sb.append("\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		System.out.println(sb.toString());
	}

}