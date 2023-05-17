package itstep.course_tasks.task_4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//a)	Create a thread pool with a fixed number of threads using Executors and submit a task to the pool.
//b)	Create your own custom class extended from some another class, and make an object for it. Use getSuperclass() to print out the name of the superclass of the class. Use getInterfaces() to print out the names of all the interfaces implemented by the class.
//c)	Create a generic method that takes an instance of a class that implements a specific interface, and calls a method on that interface using interface and invoke().

public class ThreadPool {
    public static void main(String[] args) {
        // Create a thread pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Submit a task to the pool
        executor.submit(() -> {
            System.out.println("Task executed by thread: " + Thread.currentThread().getName());
        });

        // Shutdown the pool
        executor.shutdown();
    }
}

