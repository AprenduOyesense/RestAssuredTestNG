package com.anhtester.listeners;

import com.anhtester.globals.ConfigsGlobal;
import com.anhtester.utils.LogUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onStart(ITestContext result) {
        LogUtils.info("\n **********************************");
        //Read Properties - loadAllFiles()
        //Select Database - lưu trữ vào biến toàn cục
        //Connect tới bên thứ 3 cho thuê reports chẳng hạn
    }

    @Override
    public void onFinish(ITestContext result) {
        //Tổng kết lại tình hình chạy test
        //In ra logs cho biết là đã kết thức và chạy được bao nhiêu cái pass/fail
        //Gửi mail đến mail chung để nắm bắt tình hình - Lấy ra các biến toàn cục hoặc file logs, report
        LogUtils.info("Đã gửi mail đến admin@anhtester.com");
        LogUtils.info("Tổng số test cases: " + ConfigsGlobal.TCS_TOTAL);
        LogUtils.info("Số test cases passed: " + ConfigsGlobal.PASSED_TOTAL);
        LogUtils.info("Số test cases failed: " + ConfigsGlobal.FAILED_TOTAL);
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("Đang chạy test case: " + result.getName());
        //Mình sẽ bắt cái tên TCs để ghi logs và ghi vào report (Allure report)
        ConfigsGlobal.TCS_TOTAL++;
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //Cộng 1 đơn vị vào 1 biến toàn cục để nắm bắt số lượng tcs pass
        LogUtils.info("Test case passed: " + result.getName());
        ConfigsGlobal.PASSED_TOTAL++;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //Cộng 1 đơn vị vào 1 biến toàn cục để nắm bắt số lượng tcs fail
        LogUtils.error("Test case failed: " + result.getName());
        LogUtils.error(result.getThrowable());
        ConfigsGlobal.FAILED_TOTAL++;
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }
}
