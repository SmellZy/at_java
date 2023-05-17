package task_25_Framework.api.model.createBoardInOrg_Models;

import lombok.Data;

@Data

public class CreateBoardInOrgResponse {
    private Integer statusCode;
    private TrelloBoardInOrg body;
}
