import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
public class myTestCases {

	// Driver for running Appium on an Android device
	AndroidDriver driver;

	// To set the configuration for the test environment
	DesiredCapabilities caps = new DesiredCapabilities();

	// For generating random numbers
	Random random = new Random();

	// This method runs ONCE before all tests
	@BeforeTest
	public void mySetup(){
		// Set the platform to Android
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

		// Set the device name
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "calculator");

		// Set the path to the .apk file of the app
		File myapp = new File("src/myapplication/calculator.apk");
		caps.setCapability(MobileCapabilityType.APP, myapp.getAbsolutePath());
	}

	// This method runs BEFORE EACH test method
	@BeforeMethod
	public void thisPartStartsBeforeEachTest() throws MalformedURLException{
		// Launch the app on the specified device using the capabilities
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
	}

	// Multiply two numbers 
	@Test(priority = 1, description = "multiply 2 numbers", groups = "happy", enabled = false)
	public void myTest() throws MalformedURLException{
		driver.findElement(By.id("com.google.android.calculator:id/digit_9")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
	}

	// Click on all number buttons (0 to 9), multiply the result by 1 and check the result
	@Test(priority = 2, enabled = false)
	public void clickOnAllNumbersOnly(){
		// Get all buttons with class name
		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++){
			// Click only number buttons (resource-id contains "digit")
			if (allButtons.get(i).getDomAttribute("resource-id").contains("digit"))
				allButtons.get(i).click();
		}

		driver.findElement(By.id("com.google.android.calculator:id/op_mul")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_1")).click();

		// Get the result and check if it matches expected output
		String results = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		System.out.println(results);

		String expectedResults = "7894561230"; // Expected digits pressed
		Assert.assertEquals(results, expectedResults);
	}

	// Click only on odd number buttons
	@Test(priority = 3, enabled = false)
	public void clickOnOddNumbers(){
		List<WebElement> allButtons = driver.findElements(By.className("android.widget.ImageButton"));

		for (int i = 0; i < allButtons.size(); i++){
			// Check if the button is an odd number and click it
			if (allButtons.get(i).getDomAttribute("resource-id").contains("digit_9")
					|| allButtons.get(i).getDomAttribute("resource-id").contains("digit_7")
					|| allButtons.get(i).getDomAttribute("resource-id").contains("digit_5")
					|| allButtons.get(i).getDomAttribute("resource-id").contains("digit_3")
					|| allButtons.get(i).getDomAttribute("resource-id").contains("digit_1"))

				allButtons.get(i).click();
		}
	}

	// Click on two random digits, add them and verify the result
	@Test(priority = 4)
	public void clickOnTwoRandomNumber(){

		// Generate two random numbers between 0 and 9
		String firstNumber = Integer.toString(random.nextInt(10));
		String secondNumber = Integer.toString(random.nextInt(10));

		System.out.println("First number: " + firstNumber);
		System.out.println("Second number: " + secondNumber);

		// Perform the operation: firstNumber + secondNumber
		driver.findElement(By.id("com.google.android.calculator:id/digit_" + firstNumber)).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_" + secondNumber)).click();

		// Get the preview result
		String result = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		System.out.println("Result: " + result);

		// Verify that the result is correct
		int expected = Integer.parseInt(firstNumber) + Integer.parseInt(secondNumber);
		Assert.assertEquals(Integer.parseInt(result), expected);
	}

	// This runs AFTER all tests are done
	@AfterTest
	public void afterMyTest(){
		
	}
}