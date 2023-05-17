package itstep.course_tasks.task_13;


import org.apache.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;

public class SuiteListener implements ISuiteListener {
    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Override
    public void onStart(ISuite suite){
        LOGGER.info(suite.getName() + "onStart");
    }

    @Override
    public void onFinish(ISuite suite){
        LOGGER.info(suite.getName() + "onFinish");
    }

}
