package task_25_Framework.api.model.createBoardForLabel;

import lombok.Data;
import task_25_Framework.api.model.createBoard.TrelloCreateBoard;

@Data
public class CreateBoardForLabelResponse {
    private Integer statusCode;
    private TrelloCreateBoardForLabel body;
}
