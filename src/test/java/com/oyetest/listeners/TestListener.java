package com.oyetest.listeners;

import com.oyetest.globals.ConfigsGlobal;
import com.oyetest.helpers.PropertiesHelper;
import com.oyetest.utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        LogUtils.info("\n===============================================================\n==================== " 
            + context.getSuite().getName().toUpperCase() 
            + " STARTING ===================\n===============================================================");
        
        // Load all configuration files
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext context) {
        LogUtils.info("\n################################# FINISH ################################");
        LogUtils.info("Summary:");
        LogUtils.info("Total Test Cases: " + ConfigsGlobal.TCS_TOTAL);
        LogUtils.info("Passed: " + ConfigsGlobal.PASSED_TOTAL);
        LogUtils.info("Failed: " + ConfigsGlobal.FAILED_TOTAL);
        LogUtils.info("Email notification sent to admin@oyetest.com.");
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("\n*****************************************************************");
        LogUtils.info("Running test case: " + result.getName());
        ConfigsGlobal.TCS_TOTAL++;

        // Log SubId if present
        logSubIdForTest(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test case " + result.getName() + " PASSED.");
        ConfigsGlobal.PASSED_TOTAL++;

        // Log SubId if present
        logSubIdForTest(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test case " + result.getName() + " FAILED.");
        LogUtils.error(result.getThrowable());
        ConfigsGlobal.FAILED_TOTAL++;

        // Log SubId if present
        logSubIdForTest(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("Test case " + result.getName() + " SKIPPED.");
    }

    // Method to log SubId
    public void logSubIdForTest(ITestResult result) {
        Object subIdObj = result.getTestContext().getAttribute("subid"); // Fetching subid from the test context
        if (subIdObj != null) {
            String subid = subIdObj.toString();
            if (!subid.isEmpty()) {
                LogUtils.info("SubId for test case " + result.getName() + ": " + subid);
                // Add subid to the report
                // ExtentReportManager.addTestDetails("SubId: " + subid); // Uncomment if using Extent Reports
            }
        }
    }
}