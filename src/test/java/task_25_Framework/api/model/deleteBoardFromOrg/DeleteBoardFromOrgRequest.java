package task_25_Framework.api.model.deleteBoardFromOrg;

import lombok.Data;
import task_17.model.BaseTrelloRequest;

@Data
public class DeleteBoardFromOrgRequest extends BaseTrelloRequest {
    private String boardId;
}
