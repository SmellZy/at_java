package itstep.course_tasks.task_13;

import org.apache.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class IInvoke implements IInvokedMethodListener {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        IInvokedMethodListener.super.beforeInvocation(method, testResult, context);
       LOGGER.info(method + testResult.getName() + context.getName() + " beforeInvocation");
    }
}
