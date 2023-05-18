package itstep.task_25_Framework.po;

import itstep.task_25_Framework.bo.CalcBO;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;

public class CalcPO {
    public boolean isOpened() throws FindFailed {
        Pattern calcWindow = CalcBO.makePattern("calc_window");
        return CalcBO.screen.wait(calcWindow, 3000L).isValid();
    }

    public CalcPO clickButton(String number) throws FindFailed {
        Pattern digitButton = CalcBO.makePattern(number+"_button");
        CalcBO.screen.find(digitButton).click();
        return this;
    }

    public boolean input1p5pVisible() throws FindFailed {
        Pattern input_5 = CalcBO.makePattern("input_value5");
        return CalcBO.screen.wait(input_5, 3000L).isValid();
    }

    public boolean sum_is_6() throws FindFailed {
        Pattern sum_is_6 = CalcBO.makePattern("sum_of_inputs6");
        return CalcBO.screen.wait(sum_is_6, 3000L).isValid();
    }
}
