import java.util.Scanner;

class Sphere {

  public int x;
  public int y;
  public int z;
  public int r;
  public double v;

  public Sphere(int r, int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.r = r;
  }

  public double volume() {
    v=(4.0/3)*Math.PI * Math.pow(r, 3);
  }

  public double getDiscIntegral(double a, double b) {
    if (a < z - r && b < z - r || a > z + r && b > z + r) {
      return 0;
    }
    a = Math.max(a, z - r);
    b = Math.min(b, z + r);
    a -= z;
    b -= z;
    return Math.PI * (r * r * b - b * b * b / 3) - Math.PI * (r * r * a - a * a * a / 3);
  }
}

public class Cheese {

  public static double numholes;
  public static double numslices;
  public static double b;
  public static double c;
  public static double d;
  public static ArrayList<Sphere> spheres = new ArrayList<>();

  public static void main(String[] args) {

    Scanner in = new Scanner(System.in);

    numholes=in.nextDouble();
    numslices=in.nextDouble();

    for (int i=0; i<numholes; i++){
      spheres.add(new Sphere(in.nextDouble(),in.nextDouble(),in.nextDouble(),in.nextDouble()));
    }
    double totalHoleVolume=0;
    for(Sphere sphere : spheres) {
      totalHoleVolume+=sphere.volume();
    }
    double totalVolume=(100000.0*100000.0*100000.0)-totalHoleVolume;
    double sliceVolume=totalVolume/numslices;
    int desiredZ=100000/numslices;


  }

  public double sliceVolume(double start, double end) {
    double total = 100000 * 100000 * (end - start);
    for (Sphere sphere : spheres) {
      if (sphere.z + sphere.r <= start || sphere.z - sphere.r >= end) {
        continue;
      }
      if (sphere.z - sphere.r >= start && sphere.z + sphere.r <= end) {
        total -= sphere.volume();
      } else if (sphere.z - sphere.r < start) {
        total -= sphere.getDiscIntegral(start, sphere.z + sphere.r);
      } else if (sphere.z + sphere.r > end) {
        total -= sphere.getDiscIntegral(sphere.z - sphere.r, end);
      }
    }
    return total;
  }

}
