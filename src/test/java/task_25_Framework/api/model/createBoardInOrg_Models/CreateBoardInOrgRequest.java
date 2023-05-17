package task_25_Framework.api.model.createBoardInOrg_Models;

import lombok.Data;
import task_17.model.BaseTrelloRequest;

@Data
public class CreateBoardInOrgRequest extends BaseTrelloRequest {
    private String boardName;
    private String orgId;
}
