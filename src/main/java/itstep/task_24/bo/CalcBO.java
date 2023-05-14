package itstep.task_24.bo;

import itstep.task_24.po.CalcPO;
import itstep.task_24.po.MacPO;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.io.File;

public class CalcBO {

    public static Screen screen = new Screen();
    MacPO macPO = new MacPO();
    CalcPO calcPO = new CalcPO();
    public CalcBO() {
        screen.setAutoWaitTimeout(1);
    }

    public CalcBO openCalcApp() throws FindFailed {
        //step1

        macPO.clicklaunchpadicon()
                .clickSearchInput();

        screen.type("calc");

        macPO.clickCalcIcon();

        Assert.assertTrue(calcPO.isOpened());

        //step

        return this;
    }
    public static Pattern makePattern(String fileName) {
        return new Pattern(new File("src/test/resources/pattern_calc/"+fileName+".png").getAbsolutePath());
    }

    public CalcBO tipe1_p_5_p() throws FindFailed {
        Pattern click_1 = makePattern("num1");
        screen.wait(click_1, 3000L).click();

        Pattern click_plus = makePattern("num_plus");
        screen.wait(click_plus, 3000L).click();

        Pattern check_1 = makePattern("input_value1");
        screen.wait(check_1, 2000L).isValid();

        Pattern click_5 = makePattern("num5");
        screen.wait(click_5, 3000L).click();

        Pattern check_5 = makePattern("input_value5");
        screen.wait(check_5, 2000L).isValid();

        screen.wait(click_plus, 3000L).click();
        return this;
    }

    public void checkSum6() throws FindFailed {
        Assert.assertTrue(calcPO.sum_is_6());
    }
}
