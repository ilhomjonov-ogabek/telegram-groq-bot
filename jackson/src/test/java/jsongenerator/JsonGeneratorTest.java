package jsongenerator;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import java.io.File;
import org.junit.jupiter.api.Test;

public class JsonGeneratorTest {

@Test
  void sampleCodeTest() throws Exception {
  JsonFactory factory = new JsonFactory();
  JsonGenerator generator = factory.createGenerator(new File("data/output.json"), JsonEncoding.UTF8);
  generator.writeStartObject();
  generator.writeStringField("first_name","Ogabek");
  generator.writeStringField("last_name","Ilhomjonov");
  generator.writeNumberField("age",19);

  generator.writeArrayFieldStart("programmingLanguage");
  generator.writeString("Java");
  generator.writeString("Scala");
  generator.writeString("Kotlin");
  generator.writeString("Groovy");
  generator.writeString("Go");
  generator.writeEndArray();


  generator.writeEndObject();
  generator.flush();

}

}
