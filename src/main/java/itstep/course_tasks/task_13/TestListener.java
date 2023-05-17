package itstep.course_tasks.task_13;


import org.apache.log4j.Logger;
import org.testng.*;

public class TestListener implements ITestListener {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        LOGGER.info(result.getName() + "onTestStart");
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        LOGGER.info(context.getName() + "onStart");
    }
}
