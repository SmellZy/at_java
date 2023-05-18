package task_25_Framework.api.model.createListInBoard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TrelloCreateList {
    private String id;
    private String name;
    private String desc;
    private Object descData;
    private boolean closed;
    private String idOrganization;
    private Object idEnterprise;
    private boolean pinned;
    private String url;
    private String shortUrl;
    @JsonIgnore
    private Preferences prefs;
    private LabelNames labelNames;
    private Object limits;
}
