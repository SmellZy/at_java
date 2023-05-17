package task_25_Framework.api.model.createCard;

import lombok.Data;

import java.util.List;

@Data
public class TrelloCreateCard {
    private String id;
    private Badges badges;
    private List<Object> checkItemStates;
    private boolean closed;
    private boolean dueComplete;
    private String dateLastActivity;
    private String desc;
    private DescData descData;
    private String due;
    private String dueReminder;
    private String email;
    private String idBoard;
    private List<Object> idChecklists;
    private String idList;
    private List<Object> idMembers;
    private List<Object> idMembersVoted;
    private int idShort;
    private Object idAttachmentCover;
    private List<Object> labels;
    private List<Object> idLabels;
    private boolean manualCoverAttachment;
    private String name;
    private int pos;
    private String shortLink;
    private String shortUrl;
    private String start;
    private boolean subscribed;
    private String url;
    private Cover cover;
    private boolean isTemplate;
    private Object cardRole;
    private List<Object> attachments;
    private List<Object> stickers;
    private Limits limits;
}
