package task_25_Framework.api.model.createLabelForBoard;

import lombok.Data;
import task_25_Framework.api.model.createBoardForLabel.TrelloCreateBoardForLabel;

@Data
public class CreateLabelForBoardResponse {
    private Integer statusCode;
    private TrelloCreateLabelForBoard body;
}
