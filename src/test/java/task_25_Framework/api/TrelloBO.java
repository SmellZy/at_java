package task_25_Framework.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import task_17.model.createBoardInOrg_Models.CreateBoardInOrgRequest;
import task_17.model.createBoardInOrg_Models.CreateBoardInOrgResponse;
import task_17.model.createBoardInOrg_Models.TrelloBoardInOrg;
import task_17.model.createOrg_Model.CreateOrgRequest;
import task_17.model.createOrg_Model.CreateOrgResponse;
import task_17.model.createOrg_Model.DeleteRequest;
import task_17.model.createOrg_Model.TrelloOrg;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TrelloBO {
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
    ////https://api.trello.com/1/members/6043af6fe2fd2c24633fe0a8/organizations?key=1b8ea1bc41da47da1d260b2993d6e8f7&token=ATTAb0e40741f63a3d90ada82868865cebd6ac46e8c003201f8d144d225bc229120353D8EF50&fields=id
    public List<String> getAllOrgs(String trelloMemberId, String trelloKey, String trelloToken) throws IOException, URISyntaxException, InterruptedException {


        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "1/members/" +
                        trelloMemberId + "/organizations" +
                        "?key=" + trelloKey +
                        "&token=" + trelloToken + "&fields=id").toURI())
                .GET().build();


        HttpResponse createOrgResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());


        JSONArray jsonArray = new JSONArray(createOrgResponse.body());
        List<String> list = new ArrayList<>();
        for(int i = 0; i<jsonArray.length(); i++){
            list.add(jsonArray.getJSONObject(i).getString("id"));
        }
        return list;
    }

    //https://api.trello.com/1/organizations/{id}?key=APIKey&token=APIToken
    public void deleteOrgsById(DeleteRequest deleteRequest) throws IOException, URISyntaxException, InterruptedException {

        HttpRequest httpRequest;
        httpRequest = HttpRequest.newBuilder()
                .header("accept", "application/json")
                .uri(new URL(BASE_TRELLO_URL + "1/organizations/" +
                        deleteRequest.getId() +
                        "?key=" + deleteRequest.getKey() +
                        "&token=" + deleteRequest.getToken()).toURI())
                .GET().build();


        HttpResponse createOrgResponse = HttpClient.newBuilder().build().send(
                httpRequest,HttpResponse.BodyHandlers.ofString());

    }


    public CreateBoardInOrgResponse createBoardInOrg(CreateBoardInOrgRequest createBoardInOrgRequest) throws IOException, URISyntaxException, InterruptedException {

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
}
