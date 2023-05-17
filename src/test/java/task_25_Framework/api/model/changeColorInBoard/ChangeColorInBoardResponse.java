package task_25_Framework.api.model.changeColorInBoard;

import lombok.Data;
import task_25_Framework.api.model.createBoardInOrg_Models.TrelloBoardInOrg;

@Data
public class ChangeColorInBoardResponse {
    private Integer statusCode;
    private TrelloBoardColor body;
}
