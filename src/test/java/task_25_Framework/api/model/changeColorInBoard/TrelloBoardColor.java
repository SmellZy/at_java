package task_25_Framework.api.model.changeColorInBoard;

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
    private Preferences prefs;
    private LabelNames labelNames;
}
