package ch27;
// A simple example that uses RecursiveTask<V>.
import java.util.concurrent.*;

// A RecursiveTask that computes the summation of an array of doubles.
class Sum1 extends RecursiveTask<Double> {

  // The sequential threshold value.
  final int seqThresHold = 500;

  // Array to be accessed.
  double[] data;

  // Deterines what part of data to process.
  int start, end;

  Sum1(double[] vals, int s, int e ) {
    data = vals;
    start = s;
    end = e;
  }

  // Find the summation of an array of doubles.
  protected Double compute() {
    double sum = 0;

    // If number of elements is below the sequential threshold,
    // then process sequentially.
    if((end - start) < seqThresHold) {
      // Sum the elements.
      for(int i = start; i < end; i++) sum += data[i];
    }
    else {
      // Otherwise, continue to break the data into smaller peices.

      // Find the midpoint.
      int middle = (start + end) / 2;

      // Invoke new tasks, using the subdivided data.
      Sum1 subTaskA = new Sum1(data, start, middle);
      Sum1 subTaskB = new Sum1(data, middle, end);

      // Start each subtask by forking.
      subTaskA.fork();
      subTaskB.fork();

      // Wait for the subtasks to return, and aggregate the results.
      sum = subTaskA.join() + subTaskB.join();
    }
      // Return the final sum.
      return sum;
  }
}

// Demonstrate parallel execution.
class RecurTaskDemo {
  public static void main(String args[]) {
    // Create a task pool.
    ForkJoinPool fjp = new ForkJoinPool();

    double[] nums = new double[5000];

    // Initialize nums with values that alternate between
    // positive and negative.
    for(int i=0; i < nums.length; i++)
      nums[i] = (double) (((i%2) == 0) ? i : -i) ;

    Sum1 task = new Sum1(nums, 0, nums.length);

    // Start the ForkJoinTasks.  Notice that in this case,
    // invoke() returns a result.
    double summation = fjp.invoke(task);

    System.out.println("Summation " + summation);
  }
}