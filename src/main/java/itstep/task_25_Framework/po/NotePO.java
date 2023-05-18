package itstep.task_25_Framework.po;

import itstep.task_25_Framework.bo.NotionBO;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;

public class NotePO {
    public boolean isOpened() throws FindFailed {
        Pattern noteWindow = NotionBO.makePattern("note_window").similar(0.8);
        return NotionBO.screen.wait(noteWindow, 3000L).isValid();
    }

    public NotePO clickButton(String newNote) throws FindFailed {
        Pattern noteButton = NotionBO.makePattern(newNote);
        NotionBO.screen.find(noteButton).click();
        return this;
    }



    public boolean checkNote() throws FindFailed {
        Pattern new_note = NotionBO.makePattern("check_new_note").similar(0.8);
        return NotionBO.screen.wait(new_note, 3000L).isValid();
    }
}
