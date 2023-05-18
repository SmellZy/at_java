package task_25_Framework.api.model.createListInBoard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class Preferences {
    private String permissionLevel;
    private boolean hideVotes;
    private String voting;
    private String comments;
    private String invitations;
    private boolean selfJoin;
    private boolean cardCovers;
    private boolean isTemplate;
    private String cardAging;
    private boolean calendarFeedEnabled;
    private List<SwitcherView> switcherViews;
    private String background;
    private String backgroundColor;
    private Object backgroundImage;
    private Object backgroundImageScaled;
    private boolean backgroundTile;
    private String backgroundBrightness;
    private String backgroundBottomColor;
    private String backgroundTopColor;
    private boolean canBePublic;
    private boolean canBeEnterprise;
    private boolean canBeOrg;
    private boolean canBePrivate;
    private boolean canInvite;
}
