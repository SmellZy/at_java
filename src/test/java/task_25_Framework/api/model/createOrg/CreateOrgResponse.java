package task_25_Framework.api.model.createOrg;

import lombok.Data;

@Data
public class CreateOrgResponse {
    private Integer statusCode;
    private TrelloOrg body;
}

