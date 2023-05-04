package task_17.model.createBoardInOrg_Models;
import lombok.Data;

import java.util.List;

@Data
public class BoardPrefs {
    private String permissionLevel;
    private boolean hideVotes;
    private String voting;
    private String comments;
    private String invitations;
    private boolean selfJoin;
    private boolean cardCovers;
    private boolean isTemplate;// new field
    private String cardAging;
    private boolean calendarFeedEnabled;
    private List<Object> hiddenPluginBoardButtons = null;
    private boolean canBePublic;
    private boolean canBeOrg;
    private boolean canBePrivate;
    private boolean canInvite;
    private Object background;
    private String backgroundColor;
    private Object backgroundImage;
    private Object backgroundImageScaled;
    private boolean backgroundTile;
    private String backgroundBrightness;
    private String backgroundBottomColor;
    private String backgroundTopColor;
    private List<SwitcherViews> switcherViews = null;
}
