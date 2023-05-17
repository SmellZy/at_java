package task_17;

import com.fasterxml.jackson.databind.ObjectMapper;
import itstep.course_tasks.task_14.AllureListener;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import task_17.model.createBoardInOrg_Models.CreateBoardInOrgRequest;
import task_17.model.createBoardInOrg_Models.CreateBoardInOrgResponse;
import task_17.model.createBoardInOrg_Models.TrelloBoardInOrg;
import task_17.model.createOrg_Model.CreateOrgRequest;
import task_17.model.createOrg_Model.CreateOrgResponse;
import task_17.model.createOrg_Model.DeleteRequest;
import task_17.model.createOrg_Model.TrelloOrg;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.util.Properties;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@Listeners({AllureListener.class})
public class TrelloApiTest17 {
    Properties properties = new Properties();
    String trelloKey;
    String trelloToken;
    String trelloMemberId;
    TrelloBO trelloBO = new TrelloBO();

    @BeforeTest
    void initProperty() throws IOException, URISyntaxException, InterruptedException {
        properties.load(new FileReader(new File("src/main/resources/application.properties")));
        trelloKey= properties.getProperty("trello.key");
        trelloToken = properties.getProperty("trello.token");
        trelloMemberId = properties.getProperty("trello.memberId");
        //clearOldOrgs();
    }

    //Use your scenario from Task_15.
    //Add Request and Response clases for each unique endpoints.
    //Validate Response Object using your own class comparator.

    //https://api.trello.com/1/members/6043af6fe2fd2c24633fe0a8/organizations?key=1b8ea1bc41da47da1d260b2993d6e8f7&token=ATTAb0e40741f63a3d90ada82868865cebd6ac46e8c003201f8d144d225bc229120353D8EF50&fields=id

    private void clearOldOrgs() throws IOException, URISyntaxException, InterruptedException {
        trelloBO.getAllOrgs(trelloMemberId,trelloKey,trelloToken).forEach(id->{
            DeleteRequest deleteRequest = new DeleteRequest(trelloMemberId,trelloKey,trelloToken);
            deleteRequest.setId(id);

            try {
                trelloBO.deleteOrgsById(deleteRequest);
            }catch (IOException | InterruptedException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });

    }


    @Test
    void crudAPITest() throws IOException, URISyntaxException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();
        //Create organization
        String newOrgName = UUID.randomUUID().toString().substring(2,10).replace("-", "");
        String newBoardName = UUID.randomUUID().toString().substring(2,10).replace("-", "");

        CreateOrgRequest createOrgRequest = new CreateOrgRequest();
        createOrgRequest.setDisplayName(newOrgName);
        createOrgRequest.setKey(trelloKey);
        createOrgRequest.setToken(trelloToken);




        CreateOrgResponse createOrgResponse = trelloBO.createOrg(createOrgRequest);

        CreateOrgResponse expectedCreateOrgResponse = new CreateOrgResponse();
        expectedCreateOrgResponse.setBody(new TrelloOrg());
        expectedCreateOrgResponse.getBody().setName(newOrgName);
        expectedCreateOrgResponse.setStatusCode(200);
        expectedCreateOrgResponse.getBody().setId(createOrgResponse.getBody().getId());
        expectedCreateOrgResponse.getBody().setDisplayName(createOrgResponse.getBody().getDisplayName());
        expectedCreateOrgResponse.getBody().setDesc(createOrgResponse.getBody().getDesc());
        expectedCreateOrgResponse.getBody().setDescData(createOrgResponse.getBody().getDescData());
        expectedCreateOrgResponse.getBody().setUrl(createOrgResponse.getBody().getUrl());
        expectedCreateOrgResponse.getBody().setIdMemberCreator(createOrgResponse.getBody().getIdMemberCreator());
        expectedCreateOrgResponse.getBody().setProducts(createOrgResponse.getBody().getProducts());
        expectedCreateOrgResponse.getBody().setPowerUps(createOrgResponse.getBody().getPowerUps());
        expectedCreateOrgResponse.getBody().setLimits(createOrgResponse.getBody().getLimits());
        Assert.assertEquals(createOrgResponse, expectedCreateOrgResponse);


        CreateBoardInOrgRequest createBoardInOrgRequest = new CreateBoardInOrgRequest();
        createBoardInOrgRequest.setBoardName(newBoardName);
        createBoardInOrgRequest.setOrgId(createOrgResponse.getBody().getId());
        createBoardInOrgRequest.setKey(trelloKey);
        createBoardInOrgRequest.setToken(trelloToken);

        CreateBoardInOrgResponse createBoardInOrgResponse = trelloBO.createBoardInOrg(createBoardInOrgRequest);

        CreateBoardInOrgResponse expectedCreateBoardInOrgResponse = new CreateBoardInOrgResponse();
        expectedCreateBoardInOrgResponse.setBody(new TrelloBoardInOrg());
        expectedCreateBoardInOrgResponse.getBody().setName(newBoardName);
        expectedCreateBoardInOrgResponse.setStatusCode(200);
        expectedCreateBoardInOrgResponse.getBody().setId(createBoardInOrgResponse.getBody().getId());
        expectedCreateBoardInOrgResponse.getBody().setId(createBoardInOrgResponse.getBody().getId());
        expectedCreateBoardInOrgResponse.getBody().setDesc(createBoardInOrgResponse.getBody().getDesc());
        expectedCreateBoardInOrgResponse.getBody().setIdOrganization(createBoardInOrgResponse.getBody().getIdOrganization());
        expectedCreateBoardInOrgResponse.getBody().setUrl(createBoardInOrgResponse.getBody().getUrl());
        expectedCreateBoardInOrgResponse.getBody().setShortUrl(createBoardInOrgResponse.getBody().getShortUrl());
        expectedCreateBoardInOrgResponse.getBody().setPrefs(createBoardInOrgResponse.getBody().getPrefs());
        expectedCreateBoardInOrgResponse.getBody().setLabelNames(createBoardInOrgResponse.getBody().getLabelNames());
        expectedCreateBoardInOrgResponse.getBody().setLimits(createBoardInOrgResponse.getBody().getLimits());




        Assert.assertEquals(createBoardInOrgResponse,expectedCreateBoardInOrgResponse);

//Create new board in organization

//        String requestBodyNewBoard = "?displayName="+newOrgName+"&key="+trelloKey+"&token="+trelloToken;
//        HttpRequest createNewBoardRequest;
//        createNewBoardRequest = HttpRequest.newBuilder()
//                .header("accept", "application/json")
//                .uri(new URL("https://api.trello.com/1/boards/?name="+newOrgName+"&key="+trelloKey+"&token="+trelloToken+"&idOrganization="+orgId).toURI())
//                .POST(HttpRequest.BodyPublishers.ofString(requestBodyNewBoard)).build();
//        HttpResponse<String> boardResponse = httpClient.send(createNewBoardRequest, HttpResponse.BodyHandlers.ofString());
//        JsonNode jsonNodeBoard = objectMapper.readTree(boardResponse.body());
//        String boardId = jsonNodeBoard.get("id").asText();
//        System.out.println("ID: " + boardId);

        //Change background color in board

//        String boardColor = "grey";
//        HttpRequest changeBoardColorRequest;
//        String requestChangeBoardColor = "?key="+trelloKey+"&token="+trelloToken+"&prefs/background="+boardColor;
//        changeBoardColorRequest = HttpRequest.newBuilder()
//                .header("accept", "application/json")
//                .uri(new URL("https://api.trello.com/1/boards/"+boardId+"?key="+trelloKey+"&token="+trelloToken+"&prefs/background="+boardColor).toURI())
//                .PUT(HttpRequest.BodyPublishers.ofString(requestChangeBoardColor)).build();
//        HttpResponse createNewBoardResponse = HttpClient.newBuilder().build().send(changeBoardColorRequest, HttpResponse.BodyHandlers.ofString());
//
//        Assert.assertEquals(createNewBoardResponse.statusCode(), 200);
//        System.out.println(createNewBoardResponse.body());

//Delete board from organization

//        HttpRequest deleteBoardFromOrgRequest;
//        deleteBoardFromOrgRequest = HttpRequest.newBuilder()
//                .header("accept", "application/json")
//                .uri(new URL("https://api.trello.com/1/boards/"+boardId+"?key="+trelloKey+"&token="+trelloToken).toURI())
//                .DELETE().build();
//        HttpResponse deleteBoardFromOrgResponse = HttpClient.newBuilder().build().send(deleteBoardFromOrgRequest, HttpResponse.BodyHandlers.ofString());
//
//        Assert.assertEquals(deleteBoardFromOrgResponse.statusCode(), 200);
//        System.out.println(deleteBoardFromOrgResponse.body());

    }
}
