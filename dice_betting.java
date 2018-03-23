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

public class dice_game {

	static int n, s, k;
	static double[][] pr;

	public static double P(int r, int d) {
		if (r < 0) return 0;
		if (d >= k) {
			pr[r][d] = 1.0;
			return pr[r][d];
		}
		else {
			if (pr[r][d] != -1.0) return pr[r][d];
			double p = (double)d / s;
			double q = (double)(s - d) / s;
			pr[r][d] = p * P(r - 1, d) + q * P(r - 1, d + 1);
			return pr[r][d];
		}
	}

	public static void main(String[] args) throws IOException {
		Kattio io = new Kattio(System.in, System.out);
		n = io.getInt();
		s = io.getInt();
		k = io.getInt();
		pr = new double[n + 1][k + 1];
		for (double[] row: pr) Arrays.fill(row, -1.0);
		io.println(P(n, 0));
        io.close();
	}
}