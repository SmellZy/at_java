package task_25_Framework.api.model.changeColorInBoard;

import lombok.Data;
import task_25_Framework.api.model.BaseTrelloRequest;

@Data
public class ChangeColorInBoardRequest extends BaseTrelloRequest {
    private String boardId;
    private String boardColor;
}
