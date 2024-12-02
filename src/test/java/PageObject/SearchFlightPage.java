package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchFlightPage {
	
	
	public WebDriver driver ; 
	
	public SearchFlightPage(WebDriver driver) {
		
		this.driver=driver;
		
	}
	
	@FindBy(xpath = "//span[@class=\"select2-selection select2-selection--single\"])[1]")
	WebElement from;
	
	@FindBy(xpath = "//input[@class=\"select2-search__field\"]")
	WebElement frominput;
	
	
	public void from() {
		
		from.click();
		frominput.click();
		frominput.sendKeys("chennai");
		
	}
	
	
	

}
