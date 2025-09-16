package homework1;

public class Main {

  public static void main(String[] args) {

    ProgrammingLanguage programmingLanguage = new ProgrammingLanguage(null , "Og'abek",null);

    try{
      System.out.println(programmingLanguage.getName().substring(0,5));
      System.out.println(programmingLanguage.getReleaseDate().substring(0,5));
      System.out.println(programmingLanguage.getOwner().substring(0,5));
    }catch(NullPointerException e){
      System.out.println(e);
    }

    System.out.println("Dastur davom etmoqda...");


  }
}