package xmlmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import objectmapper.Post;
import org.junit.jupiter.api.Test;

public class XmlMapperTest {

  @Test
  void testXmlMapper() throws Exception {
    Post post = new Post();
    post.setId(1);
    post.setUserId(1);
    post.setTitle("sunt aut facere repellat");
    post.setBody("quia et suscipit");
    post.setDate(new Date());

    XmlMapper xmlMapper = new XmlMapper();
     xmlMapper.writeValue(new FileOutputStream("data/xml_output.xml"),post);

  }




 @Test
  void fromXmlToObject() throws Exception {

    XmlMapper xmlMapper = new XmlMapper();
   Post post = xmlMapper.readValue(new FileInputStream("data/xml_output.xml"), Post.class);
   System.out.println(post);

 }

  @Test
  void listToXml() throws Exception {
    Post post = new Post();
    post.setId(1);
    post.setUserId(1);
    post.setTitle("sunt aut facere repellat");
    post.setBody("quia et suscipit");
    post.setDate(new Date());

    XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.writeValue(new FileOutputStream("data/xml_output.xml"), List.of(post,post,post));

  }

}
