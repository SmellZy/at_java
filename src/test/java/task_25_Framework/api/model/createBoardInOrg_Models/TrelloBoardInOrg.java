package task_25_Framework.api.model.createBoardInOrg_Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TrelloBoardInOrg {
    private String id;
    private String name;
    private String desc;
    private DescData descData;
    private boolean closed;
    private String idOrganization;
    private String idEnterprise;
    private boolean pinned;
    private String url;
    private String shortUrl;
    @JsonIgnore
    private BoardPrefs prefs;
    private LabelNames labelNames;
    private Limits limits;

}
