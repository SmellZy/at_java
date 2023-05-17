package task_25_Framework.api.model.createListInBoard;

import lombok.Data;
import task_25_Framework.api.model.BaseTrelloRequest;
import task_25_Framework.api.model.createOrg.TrelloOrg;

@Data
public class CreateListInBoardRequest extends BaseTrelloRequest {
    private String listName;
    private String boardId;
}
