package task_25_Framework.api.model.createOrg;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Data
public class TrelloOrg {
    private String id;
    private String name;
    private String displayName;
    private String desc;
    private DescData descData;
    private String url;
    private String website;
    private String teamType;
    private String logoHash;
    private String logoUrl;
    private List<String> products;
    private List<String> powerUps;
    private String idMemberCreator;
    private Map<String, Objects> limits;
}
