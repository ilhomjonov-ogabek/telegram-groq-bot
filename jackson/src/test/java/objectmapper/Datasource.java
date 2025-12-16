package objectmapper;

public class Datasource {
  private String username;
  private String password;
  private String url;
  private String dataBase;


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDataBase() {
    return dataBase;
  }

  public void setDataBase(String dataBase) {
    this.dataBase = dataBase;
  }

  @Override
  public String toString() {
    return "Datasource{" +
        "username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", url='" + url + '\'' +
        ", dataBase='" + dataBase + '\'' +
        '}';
  }
}
