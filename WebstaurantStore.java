package objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class WebstaurantStore {
	WebDriver driver;

//	Constructor
	public WebstaurantStore(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

//	WebElements

	@FindBy(id = "searchval")
	WebElement searchInput;
	
	@FindBy(xpath = "(//*[@id=\"ProductBoxContainer\"]/div[4]/form/div/div/input[2])[58]")
	WebElement addToCartButton;
	
	@FindBy(xpath = "//*[@id=\"watnotif-wrapper\"]/div/p/div[2]/div[2]/a[1]")
	WebElement viewCartButton;
	
	@FindBy(css = "button[class='emptyCartButton btn btn-mini btn-ui pull-right']")
	WebElement emptyCartButton;
	
	@FindBy(xpath = "//*[@id=\"td\"]/div[11]/div/div/div/footer/button[1]")
	WebElement EmptyCartConfirmBtn;
	
	@FindBy(xpath = "//*[@id=\"main\"]/div/div[1]/div[1]/div/div[2]/p[1]")
	WebElement validateEmptyCart;
	
	@FindBy(xpath = "//*[@id=\"watnotif-wrapper\"]/div/p/div[2]")
	WebElement AddedToCartPopUpNotification;
	
	@FindBy(css = "a[aria-label ='Homepage, WebstaurantStore']")
	WebElement HomePageLogo;
	
	@FindBy(xpath = "//*[@id=\"gdprBannerMount\"]/div/div/div/div[3]/button")
	WebElement acceptDataPolicy;
	
	
	
	
	
	
	
//	Methods
	
	public void searchStainlessWorkTable(String input) throws InterruptedException {	
		searchInput.sendKeys(input);
		searchInput.submit();
		
		System.out.println("TEST PASSED: Searched for " + input);
		
		Thread.sleep(2000);
	}
	
	public void isProductTitleContainsTable() {
		
//		Find all the Product title
		
		List<WebElement> productTitles = driver.findElements(By.xpath("//*[@id=\"product_listing\"]"));
		
//		Check each product title to ensure it contains the word "table"

		boolean allTitlesContainTable = true;
		
		for(WebElement title : productTitles) {
			String titleText  = title.getText().toLowerCase();
			
			if(!titleText.contains("table")) {
				allTitlesContainTable = false;
				
				System.out.println("Title doesn't contain the word 'table' : " + titleText);
			}

//			Check if all product titles contain the word "Table"
			
			if(allTitlesContainTable) {
				System.out.println("TEST PASSED: All product's titles contain 'table'");
			}else {
				System.out.println("TEST FAILED: Not all product titles contain table.");
			}
		
		}

	}
	
	
	public void addTheLastOfFoundItemsToCart() throws InterruptedException {
		
		addToCartButton.click();
		
		System.out.println("TEST PASSED: The last found item is added to the cart.");
		
		Thread.sleep(5000);
		
		Assert.assertEquals(AddedToCartPopUpNotification.getText().contains("1 item added to your cart"), true);
		
	}
	
	public void clearCart() throws InterruptedException {
		
		viewCartButton.click();
		Thread.sleep(3000);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.webstaurantstore.com/cart/");
		Assert.assertEquals(driver.getTitle().contains("WebstaurantStore Cart"), true);
		Assert.assertEquals(emptyCartButton.isDisplayed(), true);
		emptyCartButton.click();
		Thread.sleep(2000);
		Assert.assertEquals(EmptyCartConfirmBtn.isDisplayed(), true);
		EmptyCartConfirmBtn.click();
		Assert.assertEquals(validateEmptyCart.getText(), "Your cart is empty.");
		System.out.println("TEST PASSED: The cart is Empty now.");
	}
	
	public void acceptPolicy() throws InterruptedException {
		
		acceptDataPolicy.click();
		
		Thread.sleep(2000);
	}
	
	public void goToWebstaurantStore() throws InterruptedException {
		driver.get("https://www.webstaurantstore.com/");
		
		Assert.assertEquals(HomePageLogo.isDisplayed(), true);
		acceptPolicy();
		
		System.out.println("TEST PASSED: User is on the WebstaurantStore's Home page");
	}
	
	

}
