import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}


class Node {
    int index, items, dist;
    boolean visited;

    public Node (int i) {
        index = i;
        items = 0;
        dist = 10000;
        visited = false;
    }
}


class NodeComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        if (a.dist > b.dist) return 1;
        else if (a.dist == b.dist) {
            return 0;
        }
        return -1;
    }
}


public class big_truck {
	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio(System.in, System.out);
        int i, n, t, m, a, b, d;
        n = io.getInt();
        Node[] node = new Node[n + 1]; // Travel from node[1] to node[n].
        int[] items = new int[n + 1];
		for (i = 1; i <= n; i++) {
            items[i] = io.getInt();
            node[i] = new Node(i);
        }
        int[][] adj = new int[n + 1][n + 1];
        for (int[] row : adj) Arrays.fill(row, -1);
        for (i = 1; i <= n; i++) adj[i][i] = 0;
        m = io.getInt();
        for (i = 0; i < m; i++) {
            a = io.getInt();
            b = io.getInt();
            d = io.getInt();
            adj[a][b] = d;
            adj[b][a] = d;
        }
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new NodeComparator());
        node[1].dist = 0;
        node[1].items = items[1];
        pq.add(node[1]);
        Node curr;
        while (pq.size() > 0) {
            curr = pq.poll();
            curr.visited = true;
            for (i = 1; i <= n; i++) {
                if (node[i].visited) continue;
                if (adj[curr.index][i] > 0) {
                    if (node[i].dist > curr.dist + adj[curr.index][i]) {
                        node[i].dist = curr.dist + adj[curr.index][i];
                        node[i].items = curr.items + items[i];
                        pq.add(node[i]);
                    } else if (node[i].dist == curr.dist + adj[curr.index][i]) {
                        if (node[i].items < curr.items + items[i]) {
                            node[i].items = curr.items + items[i];
                            pq.add(node[i]);
                        }
                    }
                }
            }
        }
        if (node[n].dist == 10000) io.println("impossible");
        else {
            io.print(node[n].dist);
            io.print(" ");
            io.println(node[n].items);
        }
        io.close();
	}
}