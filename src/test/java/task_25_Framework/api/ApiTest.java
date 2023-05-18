package task_25_Framework.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import task_25_Framework.api.model.changeColorInBoard.ChangeColorInBoardRequest;
import task_25_Framework.api.model.changeColorInBoard.ChangeColorInBoardResponse;
import task_25_Framework.api.model.changeColorInBoard.TrelloBoardColor;
import task_25_Framework.api.model.createBoard.CreateBoardRequest;
import task_25_Framework.api.model.createBoard.CreateBoardResponse;
import task_25_Framework.api.model.createBoard.TrelloCreateBoard;
import task_25_Framework.api.model.createBoardInOrg_Models.CreateBoardInOrgRequest;
import task_25_Framework.api.model.createBoardInOrg_Models.CreateBoardInOrgResponse;
import task_25_Framework.api.model.createBoardInOrg_Models.TrelloBoardInOrg;
import task_25_Framework.api.model.createListInBoard.CreateListInBoardRequest;
import task_25_Framework.api.model.createListInBoard.CreateListInBoardResponse;
import task_25_Framework.api.model.createListInBoard.TrelloCreateList;
import task_25_Framework.api.model.createOrg.CreateOrgRequest;
import task_25_Framework.api.model.createOrg.CreateOrgResponse;
import task_25_Framework.api.model.createOrg.TrelloOrg;
import task_25_Framework.api.model.deleteBoardFromOrg.DeleteBoardFromOrgRequest;
import task_25_Framework.api.model.deleteBoardFromOrg.DeleteBoardFromOrgResponse;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.util.Properties;
import java.util.UUID;

public class ApiTest {
    Properties properties = new Properties();
    String trelloKey;
    String trelloToken;
    String trelloMemberId;
    ApiBO apiBO = new ApiBO();


    @BeforeTest
    void initProperty() throws IOException, URISyntaxException, InterruptedException {
        properties.load(new FileReader(new File("src/main/resources/application.properties")));
        trelloKey= properties.getProperty("trello.key");
        trelloToken = properties.getProperty("trello.token");
        trelloMemberId = properties.getProperty("trello.memberId");
    }

    @Test
    void apiTest() throws IOException, URISyntaxException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        HttpClient httpClient = HttpClient.newHttpClient();

        String newOrgName = UUID.randomUUID().toString().substring(2,10).replace("-", "");
        String newBoardName = UUID.randomUUID().toString().substring(2,10).replace("-", "");
        String newListName = UUID.randomUUID().toString().substring(2,10).replace("-", "");
        //Create organization
        CreateOrgRequest createOrgRequest = new CreateOrgRequest();
        createOrgRequest.setDisplayName(newOrgName);
        createOrgRequest.setKey(trelloKey);
        createOrgRequest.setToken(trelloToken);

        CreateOrgResponse createOrgResponse = apiBO.createOrg(createOrgRequest);
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

        //create board in org
        CreateBoardInOrgRequest createBoardInOrgRequest = new CreateBoardInOrgRequest();
        createBoardInOrgRequest.setBoardName(newBoardName);
        createBoardInOrgRequest.setOrgId(createOrgResponse.getBody().getId());
        createBoardInOrgRequest.setKey(trelloKey);
        createBoardInOrgRequest.setToken(trelloToken);
        CreateBoardInOrgResponse createBoardInOrgResponse = apiBO.createBoardInOrg(createBoardInOrgRequest);
        CreateBoardInOrgResponse expectedCreateBoardInOrgResponse = new CreateBoardInOrgResponse();
        expectedCreateBoardInOrgResponse.setBody(new TrelloBoardInOrg());
        expectedCreateBoardInOrgResponse.getBody().setName(newBoardName);
        expectedCreateBoardInOrgResponse.setStatusCode(200);
        expectedCreateBoardInOrgResponse.getBody().setId(createBoardInOrgResponse.getBody().getId());
        expectedCreateBoardInOrgResponse.getBody().setDesc(createBoardInOrgResponse.getBody().getDesc());
        expectedCreateBoardInOrgResponse.getBody().setIdOrganization(createBoardInOrgResponse.getBody().getIdOrganization());
        expectedCreateBoardInOrgResponse.getBody().setUrl(createBoardInOrgResponse.getBody().getUrl());
        expectedCreateBoardInOrgResponse.getBody().setShortUrl(createBoardInOrgResponse.getBody().getShortUrl());
        expectedCreateBoardInOrgResponse.getBody().setPrefs(createBoardInOrgResponse.getBody().getPrefs());
        expectedCreateBoardInOrgResponse.getBody().setLabelNames(createBoardInOrgResponse.getBody().getLabelNames());
        expectedCreateBoardInOrgResponse.getBody().setLimits(createBoardInOrgResponse.getBody().getLimits());
        Assert.assertEquals(createBoardInOrgResponse,expectedCreateBoardInOrgResponse);


        //change color in board
        ChangeColorInBoardRequest changeColorInBoardRequest = new ChangeColorInBoardRequest();
        changeColorInBoardRequest.setBoardColor("blue");
        changeColorInBoardRequest.setBoardId(createBoardInOrgResponse.getBody().getId());
        changeColorInBoardRequest.setKey(trelloKey);
        changeColorInBoardRequest.setToken(trelloToken);

        ChangeColorInBoardResponse changeColorInBoardResponse = apiBO.changeColorInBoard(changeColorInBoardRequest);
        ChangeColorInBoardResponse expectedChangeColorInBoardResponse = new ChangeColorInBoardResponse();
        expectedChangeColorInBoardResponse.setBody(new TrelloBoardColor());
        expectedChangeColorInBoardResponse.setStatusCode(200);
        expectedChangeColorInBoardResponse.getBody().setId(changeColorInBoardResponse.getBody().getId());
        expectedChangeColorInBoardResponse.getBody().setName(changeColorInBoardResponse.getBody().getName());
        expectedChangeColorInBoardResponse.getBody().setDesc(changeColorInBoardResponse.getBody().getDesc());
        expectedChangeColorInBoardResponse.getBody().setDescData(changeColorInBoardResponse.getBody().getDescData());
        expectedChangeColorInBoardResponse.getBody().setClosed(changeColorInBoardResponse.getBody().isClosed());
        expectedChangeColorInBoardResponse.getBody().setIdOrganization(changeColorInBoardResponse.getBody().getIdOrganization());
        expectedChangeColorInBoardResponse.getBody().setIdEnterprise(changeColorInBoardResponse.getBody().getIdEnterprise());
        expectedChangeColorInBoardResponse.getBody().setPinned(changeColorInBoardResponse.getBody().isPinned());
        expectedChangeColorInBoardResponse.getBody().setUrl(changeColorInBoardResponse.getBody().getUrl());
        expectedChangeColorInBoardResponse.getBody().setShortUrl(changeColorInBoardResponse.getBody().getShortUrl());
        expectedChangeColorInBoardResponse.getBody().setPrefs("null");
        expectedChangeColorInBoardResponse.getBody().setLabelNames(changeColorInBoardResponse.getBody().getLabelNames());

        //delete board from org
//        DeleteBoardFromOrgRequest deleteBoardFromOrgRequest = new DeleteBoardFromOrgRequest();
//        deleteBoardFromOrgRequest.setBoardId(createBoardInOrgResponse.getBody().getId());
//        deleteBoardFromOrgRequest.setKey(trelloKey);
//        deleteBoardFromOrgRequest.setToken(trelloToken);
//
//        apiBO.deleteBoardFromOrg(deleteBoardFromOrgRequest);
//
//
//        //create board for 2 ch.
//        CreateBoardRequest createBoardRequest = new CreateBoardRequest();
//        createBoardRequest.setBoardName(newBoardName);
//        createBoardRequest.setKey(trelloKey);
//        createBoardRequest.setToken(trelloToken);
//
//        CreateBoardResponse createBoardResponse = apiBO.createBoard(createBoardRequest);
//        CreateBoardResponse expectedCreateBoardResponse = new CreateBoardResponse();
//        expectedCreateBoardResponse.setBody(new TrelloCreateBoard());
//        expectedCreateBoardResponse.getBody().setName(newBoardName);
//        expectedCreateBoardResponse.setStatusCode(200);
//        expectedCreateBoardResponse.getBody().setId(createBoardResponse.getBody().getId());
//        expectedCreateBoardResponse.getBody().setId(createBoardResponse.getBody().getId());
//        expectedCreateBoardResponse.getBody().setName(createBoardResponse.getBody().getName());
//        expectedCreateBoardResponse.getBody().setDesc(createBoardResponse.getBody().getDesc());
//        expectedCreateBoardResponse.getBody().setDescData(createBoardResponse.getBody().getDescData());
//        expectedCreateBoardResponse.getBody().setClosed(createBoardResponse.getBody().isClosed());
//        expectedCreateBoardResponse.getBody().setIdOrganization(createBoardResponse.getBody().getIdOrganization());
//        expectedCreateBoardResponse.getBody().setIdEnterprise(createBoardResponse.getBody().getIdEnterprise());
//        expectedCreateBoardResponse.getBody().setPinned(createBoardResponse.getBody().isPinned());
//        expectedCreateBoardResponse.getBody().setUrl(createBoardResponse.getBody().getUrl());
//        expectedCreateBoardResponse.getBody().setShortUrl(createBoardResponse.getBody().getShortUrl());
//        expectedCreateBoardResponse.getBody().setPrefs(createBoardResponse.getBody().getPrefs());
//        expectedCreateBoardResponse.getBody().setLabelNames(createBoardResponse.getBody().getLabelNames());
//
//        Assert.assertEquals(createBoardResponse, expectedCreateBoardResponse);
//
//        //create list in board 2 ch.
//
//        CreateListInBoardRequest createListInBoardRequest = new CreateListInBoardRequest();
//        createListInBoardRequest.setBoardId(createBoardResponse.getBody().getId());
//        createListInBoardRequest.setListName(newListName);
//        createListInBoardRequest.setKey(trelloKey);
//        createListInBoardRequest.setToken(trelloToken);
//
//        CreateListInBoardResponse createListInBoardResponse = apiBO.createList(createListInBoardRequest);
//        CreateListInBoardResponse expectedCreateListInBoardResponse = new CreateListInBoardResponse();
//        expectedCreateListInBoardResponse.setBody(new TrelloCreateList());
//        expectedCreateListInBoardResponse.setStatusCode(200);
//        expectedCreateListInBoardResponse.getBody().setName(newListName);
//        expectedCreateListInBoardResponse.getBody().setId(createListInBoardResponse.getBody().getId());
//        expectedCreateListInBoardResponse.getBody().setName(createListInBoardResponse.getBody().getName());
//        expectedCreateListInBoardResponse.getBody().setDesc(createListInBoardResponse.getBody().getDesc());
//        expectedCreateListInBoardResponse.getBody().setDescData(createListInBoardResponse.getBody().getDescData());
//        expectedCreateListInBoardResponse.getBody().setClosed(createListInBoardResponse.getBody().isClosed());
//        expectedCreateListInBoardResponse.getBody().setIdOrganization(createListInBoardResponse.getBody().getIdOrganization());
//        expectedCreateListInBoardResponse.getBody().setIdEnterprise(createListInBoardResponse.getBody().getIdEnterprise());
//        expectedCreateListInBoardResponse.getBody().setPinned(createListInBoardResponse.getBody().isPinned());
//        expectedCreateListInBoardResponse.getBody().setUrl(createListInBoardResponse.getBody().getUrl());
//        expectedCreateListInBoardResponse.getBody().setShortUrl(createListInBoardResponse.getBody().getShortUrl());
//        expectedCreateListInBoardResponse.getBody().setPrefs(createListInBoardResponse.getBody().getPrefs());
//        expectedCreateListInBoardResponse.getBody().setLabelNames(createListInBoardResponse.getBody().getLabelNames());
//        expectedCreateListInBoardResponse.getBody().setLimits(createListInBoardResponse.getBody().getLimits());
//
//        Assert.assertEquals(createListInBoardResponse, expectedCreateListInBoardResponse);
    }
}
