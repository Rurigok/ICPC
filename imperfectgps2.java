import java.util.*;
import java.io.*;
public class imperfectgps2 {
	static InputReader in = new InputReader();
	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		double dist1 = 0, dist2 = 0;
		ArrayList<Point> list = new ArrayList<Point>();
		Point prev = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t2 = Integer.parseInt(st.nextToken());
			list.add(new Point(x, y, t2));

			if(i == 0)
				prev = list.get(i);

			if(i > 0){
				Point p1 = list.get(i - 1);
				Point p2 = list.get(i);
				dist1 += Math.hypot(p1.x - p2.x, p1.y - p2.y);

				int start = p1.t;
				int end = p2.t;

				for (int j = start; j <= end; j++) {
					if(j % t != 0) continue;
					double norm = 1.0 *(j - start) / (end - start);
					Point temp = new Point(p1.x + (p2.x - p1.x) * norm, p1.y + (p2.y - p1.y) * norm , j);
					dist2 += Math.hypot(temp.x - prev.x, temp.y - prev.y);
					prev = temp;
				}
			}
		}
		Point last = list.get(list.size() - 1);
		dist2 += Math.hypot(last.x - prev.x, last.y - prev.y);
		System.out.println(100 * (1 - dist2 / dist1));
	}
	static class Point implements Comparable<Point> {
		public double x, y;
		public int t;
		public Point(double i, double j, int k) {
			x = i; y = j; t = k;
		}
		public int compareTo(Point other) {
			return x == other.x ? 1 : -1;
		}
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}
	static class InputReader {
		BufferedReader br;

		public InputReader() {
			try {
				br = new BufferedReader(new FileReader("imperfectgps2.in"));
			} catch(Exception e) {
				br = new BufferedReader(new InputStreamReader(System.in));
			}
		}

		public String readLine() throws Exception {
			return br.readLine();
		}
	}
}