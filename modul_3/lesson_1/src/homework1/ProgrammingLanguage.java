package homework1;

public class ProgrammingLanguage {

  private String name;
  private String owner;
  private String releaseDate;

  public ProgrammingLanguage(String name, String owner, String releaseDate) {
    this.name = name;
    this.owner = owner;
    this.releaseDate = releaseDate;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "homework1.ProgrammingLanguage{" +
        "name='" + name + '\'' +
        ", owner='" + owner + '\'' +
        ", releaseDate='" + releaseDate + '\'' +
        '}';
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }
}
