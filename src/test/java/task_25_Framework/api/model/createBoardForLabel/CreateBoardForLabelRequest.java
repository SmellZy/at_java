package task_25_Framework.api.model.createBoardForLabel;

import lombok.Data;
import task_25_Framework.api.model.BaseTrelloRequest;

@Data
public class CreateBoardForLabelRequest extends BaseTrelloRequest {
    private String boardName;
}
