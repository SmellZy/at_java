package task_25_Framework.api.model.createBoardForLabel;

import lombok.Data;

@Data
public class TrelloCreateBoardForLabel {
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
