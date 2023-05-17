package task_25_Framework.api;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.UUID;

public class SimpleApiTest {
    Properties properties = new Properties();
    String trelloKey;
    String trelloToken;

    @BeforeTest
    void initProperty() throws IOException {
        properties.load(new FileReader(new File("src/main/resources/application.properties")));
        trelloKey= properties.getProperty("trello.key");
        trelloToken = properties.getProperty("trello.token");
    }

    @Test
    void trelloCreateOrgAndChangeDetailsTest() throws IOException, InterruptedException, URISyntaxException {
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

    @Test
    void trelloCreateBoardAndListAndCreateCardIn() throws IOException, InterruptedException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();
        String newBoardName = UUID.randomUUID().toString().substring(2,10).replace("-", "");
        String listName = UUID.randomUUID().toString().substring(0, 10);

        //create board
        HttpRequest createNewBoardRequest;
        createNewBoardRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL("https://api.trello.com/1/boards/?name="+newBoardName+"&key="+trelloKey+"&token="+trelloToken).toURI())
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();
        HttpResponse<String> boardResponse = httpClient.send(createNewBoardRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNodeBoard = mapper.readTree(boardResponse.body());
        String boardId = jsonNodeBoard.get("id").asText();
        Assert.assertEquals(boardResponse.statusCode(), 200);


        //create list in board
        HttpRequest createListRequest;
        createListRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL("https://api.trello.com/1/lists" +
                        "?name="+listName+"" +
                        "&idBoard="+boardId +
                        "&key="+trelloKey+
                        "&token="+trelloToken).toURI())
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();
        HttpResponse<String> listResponse = httpClient.send(createListRequest, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(listResponse.body());
        String listId = jsonNode.get("id").asText();
        Assert.assertEquals(listResponse.statusCode(), 200);

        //create card in list
        HttpRequest createCardRequest;
        createCardRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL("https://api.trello.com/1/cards" +
                        "?idList="+listId +
                        "&key="+trelloKey+
                        "&token="+trelloToken).toURI())
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();
        HttpResponse<String> cardResponse = httpClient.send(createCardRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonCardNode = objectMapper.readTree(cardResponse.body());
        Assert.assertEquals(cardResponse.statusCode(), 200);

        //delete board
        HttpRequest deleteBoardRequest;
        deleteBoardRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL("https://api.trello.com/1/boards/"+boardId+"?key="+trelloKey+"&token="+trelloToken).toURI())
                .DELETE().build();
        HttpResponse<String> deleteResponse = httpClient.send(deleteBoardRequest, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals(deleteResponse.statusCode(), 200);
    }

    @Test
    void trelloCreateBoardAddLabelDeleteBoard() throws IOException, InterruptedException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();
        String newBoardName = UUID.randomUUID().toString().substring(2,10).replace("-", "");
        String labelName = UUID.randomUUID().toString().substring(0, 10);

        //create board
        HttpRequest createNewBoardRequest;
        createNewBoardRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL("https://api.trello.com/1/boards/?name="+newBoardName+"&key="+trelloKey+"&token="+trelloToken).toURI())
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();
        HttpResponse<String> boardResponse = httpClient.send(createNewBoardRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNodeBoard = mapper.readTree(boardResponse.body());
        String boardId = jsonNodeBoard.get("id").asText();
        Assert.assertEquals(boardResponse.statusCode(), 200);


        //create label for board
        HttpRequest createLabelRequest;
        createLabelRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL("https://api.trello.com/1/labels?name="+labelName+ "&color=yellow" + "&idBoard=" + boardId +"&key="+trelloKey+"&token="+trelloToken).toURI())
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();
        HttpResponse<String> labelResponse = httpClient.send(createLabelRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode jsonNodeLabel = mapper.readTree(labelResponse.body());
        String labelId = jsonNodeBoard.get("id").asText();
        Assert.assertEquals(labelResponse.statusCode(), 200);


        //delete board
        HttpRequest deleteBoardRequest;
        deleteBoardRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL("https://api.trello.com/1/boards/"+boardId+"?key="+trelloKey+"&token="+trelloToken).toURI())
                .DELETE().build();
        HttpResponse<String> deleteResponse = httpClient.send(deleteBoardRequest, HttpResponse.BodyHandlers.ofString());
        Assert.assertEquals(deleteResponse.statusCode(), 200);
    }
}
