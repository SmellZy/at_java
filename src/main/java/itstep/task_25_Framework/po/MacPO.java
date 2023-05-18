package itstep.task_25_Framework.po;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;

import static itstep.task_25_Framework.bo.CalcBO.makePattern;
import static itstep.task_25_Framework.bo.CalcBO.screen;


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

    public MacPO clickNoteIcon() throws FindFailed {Pattern notesIcon = makePattern("notes_icon").similar(0.8);
        screen.wait(notesIcon, 4000L).click();
        return this;
    }

    public MacPO clickClockIcon() throws FindFailed {Pattern clockIcon = makePattern("clock_icon").similar(0.8);
        screen.wait(clockIcon, 3000L).click();
        return this;
    }
}
