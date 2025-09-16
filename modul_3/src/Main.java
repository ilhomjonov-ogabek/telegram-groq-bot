import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

   /* try {
      System.out.print("enter number:");
      int num = sc.nextInt();

      System.out.println(num);
    }catch (RuntimeException e){
      System.out.println(e);
    }

    System.out.println("code ishlamoqda");


*/
    int[] array = new int[5];
    String str = "hello world";

    try {
      String substring = str.substring(0,5);
      System.out.println(substring);

      array[5] = 56;


    }catch (ArrayIndexOutOfBoundsException | NullPointerException e ) {
      System.out.println(e);
    }

    System.out.println("code ishlamoqda");


    }
}