package task_25_Framework.api.model.createOrg;

import lombok.Data;
import task_17.model.BaseTrelloRequest;

@Data
public class CreateOrgRequest extends BaseTrelloRequest {
    private String displayName;
}
