package task_25_Framework.api.model.changeColorInBoard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TrelloBoardColor {
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
    private Object prefs;

    private LabelNames labelNames;
}
