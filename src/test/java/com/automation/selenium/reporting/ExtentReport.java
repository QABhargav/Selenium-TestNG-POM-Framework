package com.automation.selenium.reporting;

import com.automation.selenium.utils.ConfigLoader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReport {

    private static ExtentReports extent;

    public static ExtentReports getReportObject() {

        if (extent == null) {

            String reportDir = System.getProperty("user.dir") + "/reports";
            File dir = new File(reportDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String path = reportDir + "/index.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);

            reporter.config().setReportName("Student LMS Project Automation Results");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Environment", ConfigLoader.get("env"));
            extent.setSystemInfo("Base URL", ConfigLoader.get("base.url"));
            extent.setSystemInfo("Run Mode", ConfigLoader.get("runMode"));
            extent.setSystemInfo("Browser", ConfigLoader.get("browser"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
//            extent.setSystemInfo("OS Version", System.getProperty("os.version"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("QA Eng", "Bhargav Gohil");
        }

        return extent;
    }
}


//package com.automation.selenium.reporting;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//
//public class ExtentReport {
//
//    public static ExtentReports getReportObject()
//    {
//        String path = System.getProperty("user.dir")+"//reports//index.html";
//        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
//        reporter.config().setReportName("Student LMS Project Automation Results");
//        reporter.config().setDocumentTitle("Test Results");
//
//        ExtentReports extent = new ExtentReports();
//        extent.attachReporter(reporter);
//        extent.setSystemInfo("OS Name", System.getProperty("os.name")); // Add OS Name
//        extent.setSystemInfo("OS Version", System.getProperty("os.version")); // Add OS Version
//        extent.setSystemInfo("Java Version", System.getProperty("java.version")); // Add Java Version
//        extent.setSystemInfo("User Name", System.getProperty("user.name")); // Add User Name
//        extent.setSystemInfo("QA Eng", "Bhargav Gohil");
////        extent.setSystemInfo("Browser",getBrowserName());
////        extent.setSystemInfo("Brows", System.getProperty("browser"));
//
//        return extent;
//    }
//
//    private static String getBrowserName() {
//        String browserName = System.getProperty("browser");
//        if (browserName == null || browserName.isEmpty()) {
//            browserName = "Not specified";
//        }
//        return browserName;
//    }
//
//}
