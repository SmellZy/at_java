package task_25_Framework.api.model.deleteBoardWithLabel;

import lombok.Data;
import task_25_Framework.api.model.BaseTrelloRequest;

@Data
public class DeleteBoardWithLabelRequest extends BaseTrelloRequest {
    private String boardId;
}
