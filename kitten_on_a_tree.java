import java.util.*;
import java.io.*;

class kitten_on_a_tree {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int kitten = Integer.parseInt(line);
		int[] tree = new int[101];
		Arrays.fill(tree, -1);
		int parent;
		String[] branch;
		while ((line = br.readLine()) != null) {
			branch = line.split(" ");
			parent = Integer.parseInt(branch[0]);
			if (parent == -1) break;
			for (int i = 1; i < branch.length; i++) {
				tree[Integer.parseInt(branch[i])] = parent;
			}
		}
		parent = kitten;
		while (true) {
			System.out.print(parent);
            parent = tree[parent];
            if (parent != -1) System.out.print(" ");
            else break;
		}
	}
}