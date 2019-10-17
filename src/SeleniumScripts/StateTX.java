package SeleniumScripts;

import java.io.File;
import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

public class StateTX {

	public static WebDriver driver;
	ATUTestRecorder recorder;

	public static void main(String[] args) throws IOException, ATUTestRecorderException {
		// TODO Auto-generated method stub

		File file = new File("driver//chromedriver.exe");
		ATUTestRecorder recorder = new ATUTestRecorder("lib", "filename", false);
		recorder.start();
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Launch URL
		driver.get("https://oig.hhsc.state.tx.us/oigportal/EXCLUSIONS.aspx");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

//		WebElement lastName = driver.findElement(By.id("dnn_ctr407_Search_txtLast1"));
//		lastName.clear();
//		lastName.sendKeys("JONES");

		WebElement firstName = driver.findElement(By.id("dnn_ctr423_Search_txtFirst1"));
		firstName.clear();
		firstName.sendKeys("John");

		WebElement submit = driver.findElement(By.id("dnn_ctr423_Search_btnSearch"));
		submit.click();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		// Page 1
		List<WebElement> rowcount = driver.findElements(By.xpath("//*[@id=\"dnn_ctr423_Search_gv_Results\"]/tbody/tr"));

		System.out.println(rowcount.size());
		js.executeScript("window.scrollBy(0,1000)");

		for (int i = 0, j = 2; i <= rowcount.size() && j <= rowcount.size(); i++, j++) {

			try {

				WebElement rowlink = StateTX.driver
						.findElement(By.id("dnn_ctr423_Search_gv_Results_imgBtnViewSSNVerification_" + i + ""));// dnn_ctr407_Search_gv_Results_imgBtnViewSSNVerification_1

				rowlink.click();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

//				String searachKey = "dnn_ctr423_Search_gv_Results_ctl0" + j + "_wmepubsvfy";
//				if (j > 9) {
//
//					searachKey = "dnn_ctr423_Search_gv_Results_ctl" + j + "_wmepubsvfy";
//				}				
				if (j <= 9) {
					WebElement clikonssn = driver
							.findElement(By.id("dnn_ctr423_Search_gv_Results_ctl0" + j + "_wmepubsvfy"));

					clikonssn.click();
					clikonssn.sendKeys("111111111");

					driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				} else {
					WebElement clikonssn = driver
							.findElement(By.id("dnn_ctr423_Search_gv_Results_ctl" + j + "_wmepubsvfy"));

					clikonssn.click();
					clikonssn.sendKeys("111111111");
				}

				WebElement clickonverify = driver
						.findElement(By.id("dnn_ctr423_Search_gv_Results_btnVerifySSN_" + i + ""));
				clickonverify.click();

				WebElement Results = driver
						.findElement(By.id("dnn_ctr423_Search_gv_Results_lblVerifiedResult_" + i + ""));
				System.out.println(Results.getText());
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			} catch (Exception e) {
				System.out.println("Exception Handled");
			}

//				
//				WebElement clickonSecondPage= driver.findElement(By.xpath("//td[2]/a"));
//				clickonSecondPage.click();
//				continue;

//				for (int j = 2; j <= rowcount.size(); j++) {
//					if (driver.findElement(By.xpath("//td[" + j + "]/a")).isDisplayed())
//
//						driver.findElement(By.xpath("//td[" + j + "]/a")).click();
//					System.out.println("Selected Second Page");

		}

		// Page 2

	}
}
