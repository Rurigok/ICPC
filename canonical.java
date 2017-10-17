import java.util.*;
import java.io.*;
public class canonical {
	static InputReader in = new InputReader();
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine());
		int [] den = new int[st.countTokens()];
		for (int i = 0; i < den.length; i++) {
			den[i] = Integer.parseInt(st.nextToken());
		}
		int [] dp = new int[den[den.length - 1] * 2 + 1];
		boolean flag = true;
		outer: for (int i = 1; i < dp.length; i++) {
			int greedy = 0, sum =  i;
			for (int j = den.length - 1; j >= 0; j--) {
				if(i >= den[j]){
					if(dp[i] == 0)
						dp[i] = dp[i - den[j]] + 1;
					else
						dp[i] = Math.min(dp[i], dp[i - den[j]] + 1); 
				}
				if(den[j] <= sum){
					int times = sum / den[j];
					greedy += times;
					sum -= den[j] * times;
				}
			}
			// System.out.println(dp[i] + " " + greedy);
			if(greedy > dp[i]){
				flag = false;
				break outer;
			}
		}
		System.out.println(flag? "canonical": "non-canonical");

	}
	static class InputReader {
		BufferedReader br;

		public InputReader() {
			try {
				br = new BufferedReader(new FileReader("canonical.in"));
			} catch(Exception e) {
				br = new BufferedReader(new InputStreamReader(System.in));
			}
		}

		public String readLine() throws Exception {
			return br.readLine();
		}
	}
}