package itstep.task_25_Framework.bo;

import itstep.task_25_Framework.po.ClockPO;
import itstep.task_25_Framework.po.MacPO;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import java.io.File;

public class ClockBO {
    public static Screen screen = new Screen();
    MacPO macPO = new MacPO();
    ClockPO clockPO = new ClockPO();
    public ClockBO() {
        screen.setAutoWaitTimeout(1);
    }

    public ClockBO openClock() throws FindFailed {
        macPO.clicklaunchpadicon();
        macPO.clickClockIcon();

        return this;
    }

    public static Pattern makePattern(String fileName) {
        return new Pattern(new File("src/test/resources/pattern_calc/"+fileName+".png").getAbsolutePath());
    }

    public ClockBO createNewAlarm() throws FindFailed {
        clockPO.clickAlarmIcon().createNewAlarm();

        screen.type("20");
        screen.type(Key.ENTER);
        return this;
    }

}
