package task_17.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseTrelloRequest {
    private String memberId;
    private String key;
    private String token;

    public BaseTrelloRequest(String memberId ,String key, String token) {
        this.key = key;
        this.token = token;
        this.memberId = memberId;
    }
}
