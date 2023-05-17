package task_25_Framework.api.model.createCard;

import lombok.Data;
import task_25_Framework.api.model.createListInBoard.TrelloCreateList;

@Data
public class CreateCardInListResponse {
    private Integer statusCode;
    private TrelloCreateCard body;
}
