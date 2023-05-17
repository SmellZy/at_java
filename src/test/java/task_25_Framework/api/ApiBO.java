package task_25_Framework.api;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import task_25_Framework.api.model.changeColorInBoard.ChangeColorInBoardRequest;
import task_25_Framework.api.model.changeColorInBoard.ChangeColorInBoardResponse;
import task_25_Framework.api.model.changeColorInBoard.TrelloBoardColor;
import task_25_Framework.api.model.createBoard.CreateBoardRequest;
import task_25_Framework.api.model.createBoard.CreateBoardResponse;
import task_25_Framework.api.model.createBoard.TrelloCreateBoard;
import task_25_Framework.api.model.createBoardForLabel.CreateBoardForLabelRequest;
import task_25_Framework.api.model.createBoardForLabel.CreateBoardForLabelResponse;
import task_25_Framework.api.model.createBoardForLabel.TrelloCreateBoardForLabel;
import task_25_Framework.api.model.createBoardInOrg_Models.CreateBoardInOrgRequest;
import task_25_Framework.api.model.createBoardInOrg_Models.CreateBoardInOrgResponse;
import task_25_Framework.api.model.createBoardInOrg_Models.TrelloBoardInOrg;
import task_25_Framework.api.model.createCard.CreateCardInListRequest;
import task_25_Framework.api.model.createCard.CreateCardInListResponse;
import task_25_Framework.api.model.createCard.TrelloCreateCard;
import task_25_Framework.api.model.createLabelForBoard.CreateLabelForBoardRequest;
import task_25_Framework.api.model.createLabelForBoard.CreateLabelForBoardResponse;
import task_25_Framework.api.model.createLabelForBoard.TrelloCreateLabelForBoard;
import task_25_Framework.api.model.createListInBoard.CreateListInBoardRequest;
import task_25_Framework.api.model.createListInBoard.CreateListInBoardResponse;
import task_25_Framework.api.model.createListInBoard.TrelloCreateList;
import task_25_Framework.api.model.createOrg.CreateOrgRequest;
import task_25_Framework.api.model.createOrg.CreateOrgResponse;
import task_25_Framework.api.model.createOrg.TrelloOrg;
import task_25_Framework.api.model.deleteBoard.DeleteBoardRequest;
import task_25_Framework.api.model.deleteBoardFromOrg.DeleteBoardFromOrgRequest;
import task_25_Framework.api.model.deleteBoardFromOrg.DeleteBoardFromOrgResponse;
import task_25_Framework.api.model.deleteBoardWithLabel.DeleteBoardWithLabelRequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ApiBO {
    private static final String BASE_TRELLO_URL = "https://api.trello.com/";
    private ObjectMapper objectMapper = new ObjectMapper();
    HttpClient httpClient = HttpClient.newHttpClient();


    public CreateOrgResponse createOrg(CreateOrgRequest createOrgRequest) throws IOException, URISyntaxException, InterruptedException {
        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "1/organizations/" +
                        "?displayName=" + createOrgRequest.getDisplayName() + "" +
                        "&key=" + createOrgRequest.getKey() +
                        "&token=" + createOrgRequest.getToken()).toURI())
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();


        HttpResponse createOrgResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());


        CreateOrgResponse res = new CreateOrgResponse();
        res.setStatusCode(createOrgResponse.statusCode());
        System.out.println(createOrgResponse.body());
        TrelloOrg createOrgBody = objectMapper.readValue(createOrgResponse.body()+"",TrelloOrg.class);
        res.setBody(createOrgBody);
        return res;
    }

    public CreateBoardInOrgResponse createBoardInOrg(CreateBoardInOrgRequest createBoardInOrgRequest) throws IOException, InterruptedException, URISyntaxException {

        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "/1/boards/?name="
                        + createBoardInOrgRequest.getBoardName()
                        + "&key=" + createBoardInOrgRequest.getKey()
                        + "&token=" + createBoardInOrgRequest.getToken()
                        + "&idOrganization=" + createBoardInOrgRequest.getOrgId()).toURI())

                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();


        HttpResponse createBoardInOrgResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());


        CreateBoardInOrgResponse res = new CreateBoardInOrgResponse();
        res.setStatusCode(createBoardInOrgResponse.statusCode());
        System.out.println(createBoardInOrgResponse.body());
        TrelloBoardInOrg createBoardInOrgBody = objectMapper.readValue(createBoardInOrgResponse.body()+"", TrelloBoardInOrg.class);
        res.setBody(createBoardInOrgBody);
        return res;
    }

    public ChangeColorInBoardResponse changeColorInBoard(ChangeColorInBoardRequest changeColorInBoardRequest) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "/1/boards/"
                        + changeColorInBoardRequest.getBoardId()
                        + "?key=" + changeColorInBoardRequest.getKey()
                        + "&token=" + changeColorInBoardRequest.getToken()
                        + "&prefs/background=" + changeColorInBoardRequest.getBoardColor()).toURI())
                .PUT(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();


        HttpResponse changeColorInBoardResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());


        ChangeColorInBoardResponse res = new ChangeColorInBoardResponse();
        res.setStatusCode(changeColorInBoardResponse.statusCode());
        System.out.println(changeColorInBoardResponse.body());
        TrelloBoardColor changeColorInBoardBody = objectMapper.readValue(changeColorInBoardResponse.body()+"", TrelloBoardColor.class);
        res.setBody(changeColorInBoardBody);
        return res;
    }


    public void deleteBoardFromOrg(DeleteBoardFromOrgRequest deleteBoardFromOrgRequest) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "/1/boards/"
                        + deleteBoardFromOrgRequest.getBoardId()
                        + "?key=" + deleteBoardFromOrgRequest.getKey()
                        + "&token=" + deleteBoardFromOrgRequest.getToken()).toURI())
                .DELETE().build();

        HttpResponse deleteBoardFromOrgResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());
    }

    // end-to-end 2 ch. ------------------------------------------------------------------------

    public CreateBoardResponse createBoard(CreateBoardRequest createBoardRequest) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "/1/boards/"
                        + "?name=" + createBoardRequest.getBoardName()
                        + "?key=" + createBoardRequest.getKey()
                        + "&token=" + createBoardRequest.getToken()).toURI())
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();

        HttpResponse createBoardResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());


        CreateBoardResponse res = new CreateBoardResponse();
        res.setStatusCode(createBoardResponse.statusCode());
        System.out.println(createBoardResponse.body());
        TrelloCreateBoard createBoardBody = objectMapper.readValue(createBoardResponse.body()+"", TrelloCreateBoard.class);
        res.setBody(createBoardBody);
        return res;
    }

    public CreateListInBoardResponse createList(CreateListInBoardRequest createListInBoardRequest) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "/1/lists"
                        + "?name=" + createListInBoardRequest.getListName()
                        + "&idBoard=" + createListInBoardRequest.getBoardId()
                        + "&key=" + createListInBoardRequest.getKey()
                        + "&token=" + createListInBoardRequest.getToken()).toURI())
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();

        HttpResponse createListResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());

        CreateListInBoardResponse res = new CreateListInBoardResponse();
        res.setStatusCode(createListResponse.statusCode());
        System.out.println(createListResponse.body());
        TrelloCreateList createListBody = objectMapper.readValue(createListResponse.body()+"", TrelloCreateList.class);
        res.setBody(createListBody);
        return res;
    }

    public CreateCardInListResponse createCard(CreateCardInListRequest createCardInListRequest) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "/1/cards"
                        + "?idList=" + createCardInListRequest.getListId()
                        + "&key=" + createCardInListRequest.getKey()
                        + "&token=" + createCardInListRequest.getToken()).toURI())
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();

        HttpResponse createCardResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());

        CreateCardInListResponse res = new CreateCardInListResponse();
        res.setStatusCode(createCardResponse.statusCode());
        System.out.println(createCardResponse.body());
        TrelloCreateCard createCardBody = objectMapper.readValue(createCardResponse.body()+"", TrelloCreateCard.class);
        res.setBody(createCardBody);
        return res;
    }

    public void deleteBoard(DeleteBoardRequest deleteBoardRequest) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "/1/boards/"
                        + deleteBoardRequest.getBoardId()
                        + "?key=" + deleteBoardRequest.getKey()
                        + "&token=" + deleteBoardRequest.getToken()).toURI())
                .DELETE().build();

        HttpResponse deleteBoardResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());
    }

    public CreateBoardForLabelResponse createBoardForLabel(CreateBoardForLabelRequest createBoardForLabelRequest) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "/1/boards/"
                        + "?name=" + createBoardForLabelRequest.getBoardName()
                        + "?key=" + createBoardForLabelRequest.getKey()
                        + "&token=" + createBoardForLabelRequest.getToken()).toURI())
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();

        HttpResponse createBoardForLabelResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());


        CreateBoardForLabelResponse res = new CreateBoardForLabelResponse();
        res.setStatusCode(createBoardForLabelResponse.statusCode());
        System.out.println(createBoardForLabelResponse.body());
        TrelloCreateBoardForLabel createBoardForLabelBody = objectMapper.readValue(createBoardForLabelResponse.body()+"", TrelloCreateBoardForLabel.class);
        res.setBody(createBoardForLabelBody);
        return res;
    }

    public CreateLabelForBoardResponse createLabel(CreateLabelForBoardRequest createLabelForBoardRequest) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "/1/labels"
                        + "?name=" + createLabelForBoardRequest.getLabelName()
                        + "&color=yellow"
                        + "&idBoard=" + createLabelForBoardRequest.getBoardId()
                        + "?key=" + createLabelForBoardRequest.getKey()
                        + "&token=" + createLabelForBoardRequest.getToken()).toURI())
                .POST(HttpRequest.BodyPublishers.ofString("", StandardCharsets.UTF_8)).build();

        HttpResponse createLabelForBoardResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());

        CreateLabelForBoardResponse res = new CreateLabelForBoardResponse();
        res.setStatusCode(createLabelForBoardResponse.statusCode());
        System.out.println(createLabelForBoardResponse.body());
        TrelloCreateLabelForBoard createLabelForBoardBody = objectMapper.readValue(createLabelForBoardResponse.body()+"", TrelloCreateLabelForBoard.class);
        res.setBody(createLabelForBoardBody);
        return res;
    }

    public void deleteBoardWithLabel(DeleteBoardWithLabelRequest deleteBoardWithLabelRequest) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "/1/boards/"
                        + deleteBoardWithLabelRequest.getBoardId()
                        + "?key=" + deleteBoardWithLabelRequest.getKey()
                        + "&token=" + deleteBoardWithLabelRequest.getToken()).toURI())
                .DELETE().build();

        HttpResponse deleteBoardWithLabelResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());
    }
}
