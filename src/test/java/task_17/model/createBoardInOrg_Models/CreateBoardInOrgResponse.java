package task_17.model.createBoardInOrg_Models;

import lombok.Data;

@Data

public class CreateBoardInOrgResponse {
    private Integer statusCode;
    private TrelloBoardInOrg body;
}
