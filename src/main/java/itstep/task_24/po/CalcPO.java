package itstep.task_24.po;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;

import static itstep.task_24.bo.CalcBO.makePattern;
import static itstep.task_24.bo.CalcBO.screen;

public class CalcPO {
    public boolean isOpened() throws FindFailed {
        Pattern calcWindow = makePattern("calc_window");
        return screen.wait(calcWindow, 3000L).isValid();
    }

    public CalcPO clickButton(String number) throws FindFailed {
        Pattern digitButton = makePattern(number+"_button");
        screen.find(digitButton).click();
        return this;
    }


    public boolean input1p5pVisible() throws FindFailed {
        Pattern input_5 = makePattern("input_value5");
        return screen.wait(input_5, 3000L).isValid();
    }

    public boolean sum_is_6() throws FindFailed {
        Pattern sum_is_6 = makePattern("sum_of_inputs6");
        return screen.wait(sum_is_6, 3000L).isValid();
    }
}
