package task_16;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import itstep.course_tasks.task_14.AllureListener;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Listeners({AllureListener.class})
public class TrelloApiTest {
    Properties properties = new Properties();
    String trelloKey;
    String trelloToken;
    //General task
    //Make restAssured TC using scenario from Task_15.
    //The same using any another API client.
    @BeforeTest
    void initProperty() throws IOException {
        properties.load(new FileReader(new File("src/main/resources/application.properties")));
        trelloKey= properties.getProperty("trello.key");
        trelloToken = properties.getProperty("trello.token");
    }

    @Test
    void crudTest(){
        //Create organization

        String newBoardName = UUID.randomUUID().toString().substring(2,10).replace("-", "");
        String orgId = given().contentType("application/json")
                .accept("application/json")
                .log().all()
                .when()
                .post("https://api.trello.com/1/organizations" +
                "?displayName="+newBoardName+"&key="+trelloKey+"&token="+trelloToken)
                .then()
                .assertThat().statusCode(200)
                .body("name", equalTo(newBoardName)).extract().jsonPath().getString("id");

        //Create new board in my org
       String boardId = given().contentType("application/json")
                .accept("application/json")
                .log().all()
                .when()
                .post("https://api.trello.com/1/boards/?name="+newBoardName+"&key="+trelloKey+"&token="+trelloToken+"&idOrganization="+orgId)
                .then()
                .assertThat().statusCode(200)
                .body("name", equalTo(newBoardName)).extract().jsonPath().get("id");

       //Change color on card
        String boardColor = "grey";
        given().contentType("application/json")
                .accept("application/json")
                .log().all()
                .when()
                .put("https://api.trello.com/1/boards/"+boardId+"?key="+trelloKey+"&token="+trelloToken+"&prefs/background="+boardColor)
                .then()
                .assertThat().statusCode(200);

        given().contentType("application/json")
                .accept("application/json")
                .log().all()
                .when()
                .delete("https://api.trello.com/1/boards/"+boardId+"?key="+trelloKey+"&token="+trelloToken)
                .then()
                .assertThat().statusCode(200)
                .body("_value", equalTo(null));
    }


    @Test
    void crudAPITest() throws IOException, URISyntaxException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();
        //Create organization
        String newBoardName = UUID.randomUUID().toString().substring(2,10).replace("-", "");

        HttpRequest createOrgRequest;
        createOrgRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL("https://api.trello.com/1/organizations/" +
                        "?displayName="+newBoardName+"" +
                        "&key="+trelloKey+
                        "&token="+trelloToken).toURI())
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();
        HttpResponse<String> response = httpClient.send(createOrgRequest, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body());
            String orgId = jsonNode.get("id").asText();
            System.out.println("ID: " + orgId);


        String requestBodyNewBoard = "?displayName="+newBoardName+"&key="+trelloKey+"&token="+trelloToken;
        HttpRequest createNewBoardRequest;
        createNewBoardRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL("https://api.trello.com/1/boards/?name="+newBoardName+"&key="+trelloKey+"&token="+trelloToken+"&idOrganization="+orgId).toURI())
                .POST(HttpRequest.BodyPublishers.ofString(requestBodyNewBoard)).build();
        HttpResponse<String> boardResponse = httpClient.send(createNewBoardRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNodeBoard = objectMapper.readTree(boardResponse.body());
        String boardId = jsonNodeBoard.get("id").asText();
        System.out.println("ID: " + boardId);


        String boardColor = "grey";
        HttpRequest changeBoardColorRequest;
        String requestChangeBoardColor = "?key="+trelloKey+"&token="+trelloToken+"&prefs/background="+boardColor;
        changeBoardColorRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL("https://api.trello.com/1/boards/"+boardId+"?key="+trelloKey+"&token="+trelloToken+"&prefs/background="+boardColor).toURI())
                .PUT(HttpRequest.BodyPublishers.ofString(requestChangeBoardColor)).build();
        HttpResponse createNewBoardResponse = HttpClient.newBuilder().build().send(changeBoardColorRequest, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(createNewBoardResponse.statusCode(), 200);
        System.out.println(createNewBoardResponse.body());



        HttpRequest deleteBoardFromOrgRequest;
        deleteBoardFromOrgRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL("https://api.trello.com/1/boards/"+boardId+"?key="+trelloKey+"&token="+trelloToken).toURI())
                .DELETE().build();
        HttpResponse deleteBoardFromOrgResponse = HttpClient.newBuilder().build().send(deleteBoardFromOrgRequest, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(deleteBoardFromOrgResponse.statusCode(), 200);
        System.out.println(deleteBoardFromOrgResponse.body());

    }
}
