package task_17.model.createOrg_Model;

import lombok.Data;
import task_17.model.BaseTrelloRequest;

@Data
public class DeleteRequest extends BaseTrelloRequest {
    private String id;

    public DeleteRequest(String trelloMemberId, String trelloKey, String trelloToken) {
        super(trelloMemberId, trelloKey, trelloToken);
    }
}
