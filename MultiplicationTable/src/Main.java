public class Main {
    public static void main(String[] args) {
        createMultiplicationTable(5);
    }

    public static void createMultiplicationTable(int num) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + " x " + num + " = " + i * num);
        }
    }
}