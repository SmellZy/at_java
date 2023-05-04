package task_17.model.createOrg_Model;

import lombok.Data;
import task_17.model.BaseTrelloRequest;

@Data
public class CreateOrgRequest extends BaseTrelloRequest {
    private String displayName;
}
