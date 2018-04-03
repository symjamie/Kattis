import java.util.*;
import java.io.*;

class boggle {
	static int[] score;
	static String[] word;
	static String[] board;
	static boolean[][] visited;

	public static boolean findPath(String word, int i, int row, int col) {
		visited[row][col] = true;
		//System.out.println(row + ", " + col + " " + board[row].charAt(col));
		if (i == word.length()) return true;

		// Create a list of next possible steps' rows and columns.
        List<Integer> nextRows = new ArrayList<Integer>();
        List<Integer> nextCols = new ArrayList<Integer>();
        nextRows.add(row);
        nextCols.add(col);
        if (row - 1 >= 0) nextRows.add(row - 1);
        if (col - 1 >= 0) nextCols.add(col - 1);
        if (row + 1 < 4) nextRows.add(row + 1);
        if (col + 1 < 4) nextCols.add(col + 1);
        // Find the next step recursively.
        for (Integer r : nextRows) {
            for (Integer c : nextCols) {
            	try {
	                if (!visited[r][c] && board[r].charAt(c) == word.charAt(i)) {
	                    if (findPath(word, i + 1, r, c)) return true;
	                }
	            } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }
            }
        }

        // Backtrack if fail.
        visited[row][col] = false;
        //System.out.println("Backtrack");
        return false;
	}


	public static boolean found(String word) {
		int i, j;
		for (i = 0; i < 4; ++i) {
			for (j = 0; j < 4; ++j) {
				if (word.charAt(0) == board[i].charAt(j)) {
					if (findPath(word, 1, i, j)) return true;
				}
			}
		}
		return false;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		score = new int[9];
		score[3] = score[4] = 1;
		score[5] = 2;
		score[6] = 3;
		score[7] = 5;
		score[8] = 11;

		int n = Integer.parseInt(br.readLine()), i, j, sc, len, num;
		word = new String[n];
		for (i = 0; i < n; ++i) word[i] = br.readLine();
		br.readLine();

		int m = Integer.parseInt(br.readLine());
		String longest;
		for (i = 0; i < m; ++i) {
			board = new String[4];
			board[0] = br.readLine();
			board[1] = br.readLine();
			board[2] = br.readLine();
			board[3] = br.readLine();
			br.readLine();

			sc = 0;
			longest = "";
			num = 0;

			for (j = 0; j < n; ++j) {
				//System.out.println("\n"+word[j]);
				visited = new boolean[4][4];
				if (found(word[j])) {
					//System.out.println("found");
					len = word[j].length();
					sc += score[len];
					if (len > longest.length()) longest = word[j];
					else if (len == longest.length()) {
						if (word[j].compareTo(longest) < 0) longest = word[j];
					}
					++num;
				}
			}

			System.out.print(sc);
			System.out.print(" " + longest + " ");
			System.out.println(num);
		}
	}
}