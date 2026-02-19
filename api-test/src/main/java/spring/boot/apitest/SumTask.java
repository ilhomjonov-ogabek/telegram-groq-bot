package spring.boot.apitest;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class SumTask extends RecursiveTask<Long> {

  private final int[] arr;
  private final int start, end;
  private static final int THRESHOLD = 10_000;

  public SumTask(int[] arr, int start, int end) {
    this.arr = arr;
    this.start = start;
    this.end = end;
  }

  @Override
  protected Long compute() {
    int length = end - start;
    if (length <= THRESHOLD) {
      long sum = 0;
      for (int i = start; i < end; i++)
        sum += arr[i];
      return sum;
    }

    int mid = start + length / 2;
    SumTask left = new SumTask(arr, start, mid);
    SumTask right = new SumTask(arr, mid, end);

    left.fork();              // chap bo‘lakni parallelga tashla
    long rightRes = right.compute(); // o‘ngni o‘zing hisobla
    long leftRes = left.join();      // chap natijani kutib ol

    return leftRes + rightRes;
  }
}
