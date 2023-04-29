package itstep.task_13;

import org.apache.log4j.Logger;

public class IExecutionListener implements org.testng.IExecutionListener {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    @Override
    public void onExecutionStart() {
        org.testng.IExecutionListener.super.onExecutionStart();
        LOGGER.info("");
    }
}
