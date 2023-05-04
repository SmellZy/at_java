package task_17.model.createOrg_Model;

import lombok.Data;

@Data
public class CreateOrgResponse {
    private Integer statusCode;
    private TrelloOrg body;
}
