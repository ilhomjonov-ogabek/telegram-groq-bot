package objectmapper;


import java.util.Date;

public class Post {

  private Integer id;

  private Integer userId;

  private String title;
  private String body;
  private Date date;

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Post{" +
        "id=" + id +
        ", userId=" + userId +
        ", title='" + title + '\'' +
        ", body='" + body + '\'' +
        ", date=" + date +
        '}';
  }
}
