package task_25_Framework.api.model.createCard;

import lombok.Data;
import task_25_Framework.api.model.BaseTrelloRequest;

@Data
public class CreateCardInListRequest extends BaseTrelloRequest {
    private String listId;
}
