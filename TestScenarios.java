package Testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import objects.WebstaurantStore;


public class TestScenarios {
	WebDriver driver;
	
	
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(45));
		
		
		driver.get("https://www.webstaurantstore.com/");
		WebElement acceptDataPolicy;
		acceptDataPolicy = driver.findElement(By.xpath("//*[@id=\"gdprBannerMount\"]/div/div/div/div[3]/button"));
		acceptDataPolicy.click();
		
		Thread.sleep(5000);
		
		
	}
	
	
	@Test
	public void myTest() throws InterruptedException {
		WebstaurantStore page = new WebstaurantStore(driver);
		page.searchStainlessWorkTable("stainless work table");
		page.isProductTitleContainsTable();
		page.addTheLastOfFoundItemsToCart();
		page.clearCart();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
