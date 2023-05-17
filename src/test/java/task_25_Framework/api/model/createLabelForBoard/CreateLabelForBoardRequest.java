package task_25_Framework.api.model.createLabelForBoard;

import lombok.Data;
import task_25_Framework.api.model.BaseTrelloRequest;

@Data
public class CreateLabelForBoardRequest extends BaseTrelloRequest {
    private String labelName;
    private String boardId;
}
