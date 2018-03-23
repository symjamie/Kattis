import java.util.*;
import java.io.*;

public class kastenlauf {

	public static void union (int[] parent, int n, int a, int b) {
		int i, min, max;
		if (parent[a] == parent[b]) return;
		if (parent[a] < parent[b]) {
			min = parent[a];
			max = parent[b];
		} else {
			min = parent[b];
			max = parent[a];
		}
		for (i = 0; i < n; i++) {
			if (parent[i] == max) parent[i] = min;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        int i, j, k, store, n;
        int[] x, y, parent;
        String[] temp;
        for (i = 0; i < test; i++) {
        	store = Integer.parseInt(br.readLine());
        	n = store + 2;
        	x = new int[n];
        	y = new int[n];
        	for (j = 0; j < n; j++) {
        		temp = br.readLine().split(" ");
        		x[j] = Integer.parseInt(temp[0]);
        		y[j] = Integer.parseInt(temp[1]);
        	}
        	parent = new int[n];
        	for (j = 0; j < n; j++) parent[j] = j;
        	for (j = 1; j < n; j++) {
        		for (k = 0; k < j; k++) {
        			if ((Math.abs(x[j] - x[k]) + Math.abs(y[j] - y[k])) <= 1000) union(parent, n, j, k);
        		}
        	}
        	if (parent[n-1] == 0) System.out.println("happy");
        	else System.out.println("sad");
        }
	}

}