package itstep.task_3;
import java.util.LinkedList;
import java.util.Scanner;

public class Arrays {
    //V14.
    //Make LinkedList from the result array and performs the following operations: a) Add an element to the beginning of the list; b) Add an element to the end of the list; c) Remove the first element from the list; d) Remove the last element from the list; e) Print the elements of the list in reverse order;
    //Make up the situation for NumberFormatException. Catch it and display the explanation for your custom case.

    public static void main(String[] args) {
        ArraySum();
        LinkedListTask();
        ExceptionTask();
    }

    private static void ArraySum() {
        System.out.println("1. Array task: " + "\n");
        //Array Sum: Write a Java program that takes an array of integers and calculates their sum. Your program should prompt the user to enter the array size and the elements of the array, and then output the sum of the array.

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();

        int[] nums = new int[size];

        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            nums[i] = scanner.nextInt();
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        System.out.println("The sum of the array is: " + sum + "\n");
    }

    private static void LinkedListTask() {
        System.out.println("2. LinkedListTask: " + "\n");
        int[] arr = {1, 2, 3, 4, 5};
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        System.out.println("Our List" + list);

        // Add an element to the beginning of the list
        list.addFirst(0);
        System.out.println("Add an element to the beginning of the list " + list);

        // Add an element to the end of the list
        list.addLast(6);
        System.out.println("Add an element to the end of the list " + list);

        // Remove the first element from the list
        list.removeFirst();
        System.out.println("Remove the first element from the list " + list);

        // Remove the last element from the list
        list.removeLast();
        System.out.println("Remove the last element from the list " + list);

        // Print the elements of the list in reverse order
        System.out.print("Print the elements of the list in reverse order: ");
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + " ");
        }
    }

    private static void ExceptionTask() {
        System.out.println("\n" + "\n" + "3. NumberFormatExceptionTask" + "\n");
        String str = "IT Step University";
        try {
            int num = Integer.parseInt(str);
            System.out.println(num);
        } catch (NumberFormatException e) {
            System.out.println("The string \"" + str + "\" is not a valid integer.");
        }
    }
}
