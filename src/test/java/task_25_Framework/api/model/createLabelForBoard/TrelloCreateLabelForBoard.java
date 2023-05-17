package task_25_Framework.api.model.createLabelForBoard;

import lombok.Data;

@Data
public class TrelloCreateLabelForBoard {
    private String id;
    private String idBoard;
    private String name;
    private String color;
    private Limits limits;
}
