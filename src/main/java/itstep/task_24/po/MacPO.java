package itstep.task_24.po;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;

import javax.crypto.Mac;

import static itstep.task_24.bo.CalcBO.makePattern;
import static itstep.task_24.bo.CalcBO.screen;

public class MacPO {

    public MacPO clicklaunchpadicon() throws FindFailed {
        Pattern launchpadicon = makePattern("launchpad");
        screen.find(launchpadicon).click();
        return this;
    }

    public MacPO clickSearchInput() throws FindFailed {
        Pattern searchInput = makePattern("search_input");
        screen.wait(searchInput, 3000L).click();
        return this;
    }

    public MacPO clickCalcIcon() throws FindFailed {Pattern calcIcon = makePattern("calc_icon");
        screen.wait(calcIcon, 3000L).click();
        return this;
    }
}
