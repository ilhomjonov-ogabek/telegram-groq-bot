

public class Main {
  public static void main(String[] args) {

    Person odam = new Person("Og'abek", 20, "Erkak", 175, 70);

    System.out.println("1-holat");
    odam.showInfo();

    odam.setAge(25);

    System.out.println("2-holat setter orqali age set qilingandan so'ng:");

    odam.showInfo();


  }
}
