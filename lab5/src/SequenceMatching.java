import java.io.IOException;
import java.util.Map;

public class SequenceMatching {
	public static void main(String[] args) throws IOException {
		Map<String, Integer> scoreMatrix = ScoreMatrixFactory
				.getScoreMatrix("files/BLOSUM62.txt");
		Sequence[] sequences = SequenceFactory.getSequences("files/HbB_FASTAs-in.txt");
		for(int i = 0; i < sequences.length; i++) {
			for(int j = i+1; j < sequences.length; j++) {
				int c = match(sequences[i].getSequence(), sequences[j].getSequence(), scoreMatrix);
				System.out.println(sequences[i].getAnimal() + "--" + sequences[j].getAnimal() + ": " + c);
			}
		}
	}

	private static int match(String s1, String s2,
			Map<String, Integer> scoreMatrix) {
		int m = s1.length();
		int n = s2.length();
		int[][] grid = new int[m+1][n+1];
		boolean[][] computed = new boolean[m+1][n+1];
		for(int i = 0; i <= m; i++) {
			grid[i][0] = i * -4;
			computed[i][0] = true;
		}
		for(int j = 0; j <= n; j++) {
			grid[0][j] = j * -4;
			computed[0][j] = true;
		}
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				match(s1, s2, i, j, scoreMatrix, grid, computed);
			}
		}
		return grid[s1.length()][s2.length()];
	}

	private static int match(String s1, String s2, int i, int j,
			Map<String, Integer> scoreMatrix, int[][] memo, boolean[][] computed) {
		if(computed[i][j]) {
			return memo[i][j];
		}
		int costPealBoth = scoreMatrix.get("" + s1.charAt(i-1) + s2.charAt(j-1))
				+ match(s1, s2, i - 1, j - 1, scoreMatrix, memo, computed);
		int costPealS1 = -4 + match(s1, s2, i - 1, j, scoreMatrix, memo, computed);
		int costPealS2 = -4 + match(s1, s2, i, j - 1, scoreMatrix, memo, computed);
		int cost = Math.max(costPealBoth, Math.max(costPealS1, costPealS2));
		memo[i][j] = cost;
		computed[i][j] = true;
		return cost;
	}
}
