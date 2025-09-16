package homework2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    try {

      System.out.print("Nechinchi arrayni chiqaramz kiriting:");
      int num = sc.nextInt();
      if (num < 0 || num > 10) {
        throw new NoFoundNumberException("Noto'g'ri index kiritildi!");
      }
      System.out.println(array[num - 1]);
    } catch (NoFoundNumberException | InputMismatchException e) {
      System.err.println(e);

    }

    System.out.println("Dastur davom etmoqda");
  }

}
