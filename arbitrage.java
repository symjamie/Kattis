import java.util.StringTokenizer;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

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


public class arbitrage {
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        StringBuilder sb = new StringBuilder();
        String a, b;
        String[] r;
        int i, n, m;
        double w;
        boolean ans;
        while (true) {
            n = io.getInt();
            if (n == 0) break;
            HashMap<String, Double> dist = new HashMap<String, Double>();
            HashMap<String, HashMap<String, Double>> edge = new HashMap<String, HashMap<String, Double>>();
            for (i = 0; i < n; ++i) dist.put(io.getWord(), Double.MAX_VALUE);
            m = io.getInt();
            for (i = 0; i < m; ++i) {
                a = io.getWord();
                b = io.getWord();
                r = io.getWord().split(":");
                w = -Math.log(Double.parseDouble(r[1]) / Integer.parseInt(r[0]));
                if (!edge.containsKey(a)) edge.put(a, new HashMap<String, Double>());
                edge.get(a).put(b, w);
                if (i == 0) dist.put(a, 0.0);
            }
            // Relax n-1 times (slow).
            for (i = 1; i < n; ++i) {
                for (Map.Entry<String, HashMap<String, Double>> entry : edge.entrySet()) {
                    a = entry.getKey();
                    for (Map.Entry<String, Double> temp : entry.getValue().entrySet()) {
                        b = temp.getKey();
                        if (dist.get(b) > dist.get(a) + temp.getValue()) dist.put(b, dist.get(a) + temp.getValue());
                    }
                }
            }
            // Detect negative cycles in n-th iteration.
            ans = false;
            for (Map.Entry<String, HashMap<String, Double>> entry : edge.entrySet()) {
                a = entry.getKey();
                for (Map.Entry<String, Double> temp : entry.getValue().entrySet()) {
                    b = temp.getKey();
                    if (dist.get(b) > dist.get(a) + temp.getValue()) ans = true;
                }
            }
            if (ans) sb.append("Arbitrage\n");
            else sb.append("Ok\n");
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1).toString());
        io.close();
    }
}