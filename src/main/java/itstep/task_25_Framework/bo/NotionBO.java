package itstep.task_25_Framework.bo;

import itstep.task_25_Framework.po.CalcPO;
import itstep.task_25_Framework.po.MacPO;
import itstep.task_25_Framework.po.NotePO;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import java.io.File;

public class NotionBO {
    public static Screen screen = new Screen();
    MacPO macPO = new MacPO();
    NotePO notePO = new NotePO();
    public NotionBO() {
        screen.setAutoWaitTimeout(1);
    }

    public NotionBO openNotions() throws FindFailed {
        macPO.clicklaunchpadicon().clickSearchInput();

        screen.type("note");

        macPO.clickNoteIcon();

        Assert.assertTrue(notePO.isOpened());

        return this;
    }

    public static Pattern makePattern(String fileName) {
        return new Pattern(new File("src/test/resources/pattern_calc/"+fileName+".png").getAbsolutePath());
    }

    public NotionBO createNewNote() throws FindFailed {
        Pattern newNote = makePattern("new_note").similar(0.8);
        screen.wait(newNote, 3000L).click();
        return this;
    }

    public void checkNewNote() throws FindFailed {
        Assert.assertTrue(notePO.checkNote());
    }
}
