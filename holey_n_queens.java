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


class holey_n_queens {
    static int n, count;
    static boolean[][] board;
    static boolean[] a = new boolean[24]; // row
    static boolean[] b = new boolean[24]; // \
    static boolean[] c = new boolean[24]; // /

    static void gen(int col) {
        if (col >= n) {
            ++count;
            return;
        }
        for (int row = 0; row < n; ++row) {
            if (a[row] && b[row + col] && c[row - col + n] && board[row][col]) {
                a[row] = b[row + col] = c[row - col + n] = false;
                gen(col + 1);
                a[row] = b[row + col] = c[row - col + n] = true;
            }
        }
    }

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int i, m, x, y;
        while (true) {
            n = io.getInt();
            if (n == 0) break;
            m = io.getInt();
            count = 0;
            board = new boolean[n][n];
            for (boolean[] row : board) Arrays.fill(row, true);
            Arrays.fill(a, true);
            Arrays.fill(b, true);
            Arrays.fill(c, true);
            for (i = 0; i < m; ++i) {
                x = io.getInt();
                y = io.getInt();
                board[x][y] = false;
            }
            gen(0);
            io.println(count);
        }
        io.close();
    }
}