package itstep.task_2;

public class Primitives {
    public static void main(String[] args) {
        // Declare and initialize an int variable
        int num = 42;

        // Use Integer wrapper class to convert int to String
        String str = Integer.toString(num);
        System.out.println("String representation of num: " + str);

        // Use Integer wrapper class to convert String to int
        int num2 = Integer.parseInt(str);
        System.out.println("Value of num2: " + num2);
    }
}
