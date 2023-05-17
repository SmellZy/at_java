package task_25_Framework.api.model.deleteBoard;

import lombok.Data;
import task_25_Framework.api.model.BaseTrelloRequest;

@Data
public class DeleteBoardRequest extends BaseTrelloRequest {
    private String boardId;
}
