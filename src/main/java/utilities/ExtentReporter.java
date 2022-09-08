package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	static ExtentReports extentReport;
	
	public static ExtentReports getExtentReport() {
		
		String extentPath=System.getProperty("user.dir")+"\\reports\\extentReport.html";
		
		ExtentSparkReporter reporter= new ExtentSparkReporter(extentPath);
		reporter.config().setReportName("Automation lab Login Test");
		reporter.config().setDocumentTitle("Test Report");
		
		extentReport= new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("Operating system", "Windows 11");
		extentReport.setSystemInfo("Tested By", "Surendra");
		
		return extentReport;
	}

}
