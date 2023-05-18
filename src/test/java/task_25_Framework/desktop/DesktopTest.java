package task_25_Framework.desktop;

import itstep.task_25_Framework.bo.CalcBO;
import itstep.task_25_Framework.bo.ClockBO;
import itstep.task_25_Framework.bo.NotionBO;
import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

public class DesktopTest {

    @Test
    void calcTest() throws FindFailed {
        CalcBO calcBO = new CalcBO();

        calcBO.openCalcApp()
                .tipe1_p_5_p()
                .checkSum6();
    }

    @Test
    void notionTest() throws FindFailed {
        NotionBO notionBO = new NotionBO();

        notionBO.openNotions().createNewNote().checkNewNote();

    }

    @Test
    void clockTest() throws FindFailed {
        ClockBO clockBO = new ClockBO();

        clockBO.openClock().createNewAlarm();
    }
}
