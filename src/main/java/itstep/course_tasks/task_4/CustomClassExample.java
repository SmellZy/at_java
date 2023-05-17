package itstep.course_tasks.task_4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomClassExample extends Object implements Runnable, Comparable<CustomClassExample> {
    @Override
    public void run() {
        // Implementation of the run method
    }

    @Override
    public int compareTo(CustomClassExample o) {
        // Implementation of the compareTo method
        return 0;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        CustomClassExample obj = new CustomClassExample();

        // Print the name of the superclass
        System.out.println("Superclass: " + obj.getClass().getSuperclass().getName());

        // Print the names of all the interfaces implemented by the class
        Class[] interfaces = obj.getClass().getInterfaces();
        System.out.print("Interfaces: ");
        for (Class c : interfaces) {
            System.out.print(c.getName() + " ");
        }

        // Call the callMethod generic method with an object of CustomClassExample
        callMethod(obj);
    }

    public static <T extends Runnable> void callMethod(T obj) throws NoSuchMethodException {
        // Get the method object for the run method
        Method method = obj.getClass().getMethod("run");

        try {
            // Call the run method using invoke
            method.invoke(obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

