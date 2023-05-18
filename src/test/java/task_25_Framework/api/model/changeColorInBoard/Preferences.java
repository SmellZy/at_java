package task_25_Framework.api.model.changeColorInBoard;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class Preferences {
    @JsonIgnore
    private String permissionLevel;

    @JsonIgnore
    private boolean hideVotes;

    @JsonIgnore
    private String voting;

    @JsonIgnore
    private String comments;

    @JsonIgnore
    private String invitations;

    @JsonIgnore
    private boolean selfJoin;

    @JsonIgnore
    private boolean cardCovers;

    @JsonIgnore
    private boolean isTemplate;

    @JsonIgnore
    private String cardAging;

    @JsonIgnore
    private boolean calendarFeedEnabled;

    @JsonIgnore
    private List<SwitcherView> switcherViews;

    @JsonIgnore
    private String background;

    @JsonIgnore
    private String backgroundColor;

    @JsonIgnore
    private Object backgroundImage;

    @JsonIgnore
    private Object backgroundImageScaled;

    @JsonIgnore
    private boolean backgroundTile;

    @JsonIgnore
    private String backgroundBrightness;

    @JsonIgnore
    private String backgroundBottomColor;

    @JsonIgnore
    private String backgroundTopColor;

    @JsonIgnore
    private boolean canBePublic;

    @JsonIgnore
    private boolean canBeEnterprise;

    @JsonIgnore
    private boolean canBeOrg;

    @JsonIgnore
    private boolean canBePrivate;

    @JsonIgnore
    private boolean canInvite;
}
