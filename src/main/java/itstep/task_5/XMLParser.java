package itstep.task_5;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class XMLParser {
    public static void main(String[] args) throws IOException {
        //V14
        //General task for all:
        //Serialization-Deserialization:
        //a) Make some complex models using your variant.
        //b) Make it serializable. +
        //c) Read json from “input.json”
        //d) and deserialize it to POJO.
        //e) Then change a few fields and save it to “output.json”.
        //f) Do the same for xml.


        //step 1 init object
        data data = new data();
        data.setTime("10:30 AM");
        data.setAttendees(Arrays.asList("John", "Mary", "David"));
        System.out.println(data);

        //step 2 save to file
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("target/data.json"), data);

        //step 3 read file to object
        data inputXML = xmlMapper.readValue(new File("target/data.json"), data.class);
        System.out.println("inputUserXml: " + inputXML);

        //step 4 change data inUser file
        inputXML.getAttendees().add("Semen");
        xmlMapper.writeValue(new File("target/output.json"), inputXML);
        System.out.println("Change inputUserXml: " + inputXML);
    }
}
