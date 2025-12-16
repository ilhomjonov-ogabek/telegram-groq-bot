package objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class ObjectMapperTest {

  @Test
  public void simpleCodeTest() throws JsonProcessingException {
    String postJSON = """
    {
        "id": 1,
        "userId": 1,
        "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        "body": "quia et suscipit"
    }""";

    ObjectMapper mapper = new ObjectMapper();
    Post post = mapper.readValue(postJSON, Post.class);
    System.out.println(post);

    String string = mapper.writeValueAsString(post);

    System.out.println(string);

  }

  @Test
  public void objectFromStringReader() throws IOException {

    String postJSON = """
    {
        "id": 1,
        "userId": 1,
        "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        "body": "quia et suscipit"
    }""";

    StringReader reader = new StringReader(postJSON);
    ObjectMapper mapper = new ObjectMapper();
    Post post = mapper.readValue(reader, Post.class);
    System.out.println(post.getTitle());

  }

 @Test
  public void objectFromFileReader() throws IOException {
    File file = new File("data/file.txt");

    ObjectMapper mapper = new ObjectMapper();
    Post post = mapper.readValue(file, Post.class);
    System.out.println(post);

  }

  @Test
  public void objectFromURL() throws IOException {
    int postID = new Random().nextInt(2,15);
    URL url = new URL("https://jsonplaceholder.typicode.com/posts/"+postID );
    ObjectMapper mapper = new ObjectMapper();
    Post post = mapper.readValue(url, Post.class);
    System.out.println(post);

  }

@Test
  public void objectFromInputStream () throws IOException {
    InputStream inputStream = new FileInputStream("data/file.txt");
    ObjectMapper mapper = new ObjectMapper();
    Post post = mapper.readValue(inputStream, Post.class);
    System.out.println(post);

  }

  @Test
  void yamlFormat() throws Exception {
    YAMLFactory factory = new YAMLFactory();
    ObjectMapper mapper = new ObjectMapper(factory);
    Post post = new Post();
    post.setId(1);
    post.setUserId(1);
    post.setTitle("sunt aut facere repellat");
    post.setBody("quia et suscipit");
    post.setDate(new Date());
    mapper.setDateFormat(new SimpleDateFormat());
   /* String string = mapper.writeValueAsString(post);
    System.out.println(string);*/
    Settings settings = mapper.readValue(new File("data/settings.yaml"), Settings.class);
    System.out.println(settings);


  }



}
