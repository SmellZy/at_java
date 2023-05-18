package itstep.task_25_Framework.po;


import itstep.task_25_Framework.bo.ClockBO;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;

import static itstep.task_25_Framework.bo.ClockBO.screen;

public class ClockPO {

    public ClockPO clickAlarmIcon() throws FindFailed {
        Pattern newAlarm = ClockBO.makePattern("alarm_icon").similar(0.8);
        screen.wait(newAlarm, 3000L).click();
        return this;
    }

    public ClockPO createNewAlarm() throws FindFailed {
        Pattern newAlarmButton = ClockBO.makePattern("new_alarm_button").similar(0.8);
        screen.wait(newAlarmButton, 3000L).click();
        return this;
    }

    public ClockPO pressSaveButton() throws FindFailed {
        Pattern saveAlarmButton = ClockBO.makePattern("save_alarm_button").similar(0.8);
        screen.wait(saveAlarmButton, 3000L).click();
        return this;
    }
}
