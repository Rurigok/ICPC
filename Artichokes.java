import java.util.Scanner;

public class Artichokes {

  public static double p;
  public static double a;
  public static double b;
  public static double c;
  public static double d;

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    p = in.nextDouble();
    a = in.nextDouble();
    b = in.nextDouble();
    c = in.nextDouble();
    d = in.nextDouble();
    int n = in.nextInt();

    double max = -9999999;
    double maxDecline = 0;
    for (int i = 1; i <= n; i++) {

      double cur = price(i);

      if (cur > max) {
        max = cur;
      } else {
        maxDecline = maxDecline < max - cur ? max - cur : maxDecline;
      }

    }

    System.out.println(maxDecline);

  }

  public static double price(int k) {

    return p * (Math.sin(a * k + b) + Math.cos(c * k + d) + 2);

  }

}
