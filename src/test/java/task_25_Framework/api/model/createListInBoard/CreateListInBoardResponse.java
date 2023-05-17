package task_25_Framework.api.model.createListInBoard;

import lombok.Data;
import task_25_Framework.api.model.createOrg.TrelloOrg;

@Data
public class CreateListInBoardResponse {
    private Integer statusCode;
    private TrelloCreateList body;
}
