package task_25_Framework.api.model.createBoard;

import lombok.Data;


@Data
public class CreateBoardResponse {
    private Integer statusCode;
    private TrelloCreateBoard body;
}
