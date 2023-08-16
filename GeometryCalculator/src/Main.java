public class Main {
    public static void main(String[] args) {
        int x = 5, y = 10, z = 5;

        System.out.println(calculateVolume(x, y, z));
        System.out.println(calculateSurface(x, y, z));
        System.out.println(calculateSpaceDiagonal(x, y, z));
    }

    public static int calculateVolume(int x, int y, int z) {
        return x * y * z;
    }

    public static int calculateSurface(int x, int y, int z) {
        return x + y + z;
    }

    public static double calculateSpaceDiagonal(double x, double y, double z) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }
}