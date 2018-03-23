import java.io.*;
import java.util.*;
import java.math.BigInteger;

class node {
	int value;
	int n_nodes;
	node left;
	node right;

	public node(int value) {
		this.value = value;
		n_nodes = 1;
		left = null;
		right = null;
	}
}

public class tree_insertion {
	public static void insert(node root, int value) {
		if (value < root.value) {
			if (root.left == null) {
				node new_node = new node(value);
				root.left = new_node;
			} else insert(root.left, value);
		} else {
			if (root.right == null) {
				node new_node = new node(value);
				root.right = new_node;
			} else insert(root.right, value);
		}
		root.n_nodes++;
	}

	// Referred to https://stackoverflow.com/questions/2201113/combinatoric-n-choose-r-in-java-math.
	public static BigInteger binomial(final int N, final int K) {
	    BigInteger ret = BigInteger.ONE;
	    for (int k = 0; k < K; k++) {
	        ret = ret.multiply(BigInteger.valueOf(N-k)).divide(BigInteger.valueOf(k+1));
	    }
	    return ret;
	}

	public static BigInteger count(node root) {
		if (root == null) return BigInteger.valueOf(1);
		int m, n;
		if (root.left == null) m = 0;
		else m = root.left.n_nodes;
		if (root.right == null) n = 0;
		else n = root.right.n_nodes;
		return(binomial(m+n, n).multiply(count(root.left)).multiply(count(root.right)));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		int n, i, value, power;
		String buffer[];
		node root = null;
		BigInteger result;
		while(!(line = br.readLine()).equals("0")) {
			n = Integer.parseInt(line);
			buffer = br.readLine().split(" ");
			for (i = 0; i < n; i++) {
				value = Integer.parseInt(buffer[i]);
				if (i == 0) root = new node(value);
				else insert(root, value);
			}
			sb.append(count(root));
			sb.append("\n");
		}
		System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
	}
}