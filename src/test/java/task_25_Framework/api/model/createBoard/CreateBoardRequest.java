package task_25_Framework.api.model.createBoard;

import lombok.Data;
import task_25_Framework.api.model.BaseTrelloRequest;

@Data
public class CreateBoardRequest extends BaseTrelloRequest {
    String boardName;
}
