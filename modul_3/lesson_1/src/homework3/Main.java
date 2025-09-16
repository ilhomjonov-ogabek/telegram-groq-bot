package homework3;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Scanner scstr = new Scanner(System.in);
    Home home = new Home();

    System.out.print("Qavatlar sonini kiriting:");
    int flour = sc.nextInt();
    home.setFlour(flour);

    System.out.print("Rangini kiriting:");
    String color = scstr.next();
    home.setColor(color);
    System.out.print("Xonalar sonini kiriting:");
    int countRoom = sc.nextInt();
    home.setRoomCount(countRoom);
    System.out.print("Ismni kiriting:");
    String name = scstr.next();
    home.setPersonName(name);


  }

}
