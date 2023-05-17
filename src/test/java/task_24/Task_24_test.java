package task_24;

import itstep.course_tasks.task_14.AllureListener;
import itstep.course_tasks.task_24.bo.CalcBO;
import org.sikuli.script.FindFailed;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureListener.class})
public class Task_24_test {



    @Test
    void calcTest() throws FindFailed {
        CalcBO calcBO= new CalcBO();

        calcBO.openCalcApp()
                .tipe1_p_5_p()
                .checkSum6();

    }
}
