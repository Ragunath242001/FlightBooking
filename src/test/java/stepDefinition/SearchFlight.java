package stepDefinition;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import utility.CommonFunction;

public class SearchFlight {

	public static WebDriver driver;
	String adultsCount;

	@Before
	public void browserSetUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.phptravels.net/flights/");
		driver.manage().window().maximize();

	}

	@After
	public void tearDown() {

	}

	@Given("Launch Site and verify the title")
	public void launch_site_and_verify_the_title() {
		assertEquals(driver.getTitle(), "Search for best Flights");

	}

	@When("Click on {string} and {string}")
	public void click_on_and(String travelmode, String travelType) {

		driver.findElement(By.xpath("//small[.=\"" + travelmode + "\"]")).click();

		WebElement travelmodeDropDown = driver.findElement(By.id("flight_type"));

		CommonFunction.dropDown(travelmodeDropDown, travelType);

	}

	@When("Select  {string} and {string}")
	public void select_and(String from, String to) throws AWTException, InterruptedException {

		driver.findElement(By.xpath("(//span[@class=\"select2-selection select2-selection--single\"])[1]")).click();
		driver.findElement(By.xpath("//input[@class=\"select2-search__field\"]")).click();
		driver.findElement(By.xpath("//input[@class=\"select2-search__field\"]")).sendKeys(from);

		Thread.sleep(500);
		System.out.println(driver.findElement(By.xpath("//ul[@id=\"select2--results\"]/li")).getText());
		driver.findElement(By.xpath("//ul[@id=\"select2--results\"]/li")).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath("(//span[@class=\"select2-selection select2-selection--single\"])[2]")).click();

		WebElement toele = driver.findElement(By.xpath("//input[@class=\"select2-search__field\"]"));
		toele.click();
		toele.sendKeys(to);

		Thread.sleep(1000);
		System.out.println(driver.findElement(By.xpath("//ul[@id=\"select2--results\"]/li")).getText());

		driver.findElement(By.xpath("//ul[@id=\"select2--results\"]/li")).click();
		Thread.sleep(1000);
	}

	@When("Select {string}")
	public void select(String string) throws InterruptedException {

		driver.findElement(By.xpath("//input[@id=\"departure\"]")).click();
		List<WebElement> datele = driver.findElements(By.xpath("//table[@class=\" table-condensed\"]/tbody/tr/td"));

		for (WebElement dateAllEle : datele) {
			System.out.println(dateAllEle.getText());
			if (dateAllEle.getText().equals("14")) {
				dateAllEle.click();
				break;

			}

		}

		Thread.sleep(500);

	}

	@When("Select Travellers {string} & {string} & {string}")
	public void select_travellers(String adults, String child, String Infants) {

		driver.findElement(By.xpath("//a[@class=\"dropdown-toggle dropdown-btn travellers waves-effect\"]")).click();

		adultsCount = driver.findElement(By.xpath("//input[@id=\"fadults\"]")).getAttribute("value");

		if (adults.equals(adultsCount)) {

		} else {
			while (true) {
				adultsCount = driver.findElement(By.xpath("//input[@id=\"fadults\"]")).getAttribute("value");

				Integer adultsInt = Integer.valueOf(adults);
				Integer adultsActInt = Integer.valueOf(adultsCount);

				System.out.println(adultsInt);
				System.out.println(adultsActInt);

				if (adultsInt == adultsActInt) {
					break;
				} else if (adultsInt > adultsActInt) {
					upDown(9);

				} else {
					upDown(8);

				}

			}

		}

	}

	@When("Click on Search Button")
	public void click_on_search_button() throws InterruptedException {

		driver.findElement(By.xpath("//button[@id=\"flights-search\"]")).click();

		Thread.sleep(2000);

	}

	@Then("Verify Flight List should be  Display for above input")
	public void verify_flight_list_should_be_display_for_above_input() {

		String flightcount = driver
				.findElement(By.xpath("//div[@class=\"flex tit-travel-restriction-wrapper\"]/h2/span")).getText();

		String[] split = flightcount.split(" ");
		Integer intflightCount = Integer.valueOf(split[0]);

		System.out.println(intflightCount);

		assertTrue(intflightCount > 0);

	}

	@Then("verify Flight Ticket price List Displayed Low to Hight")
	public void verify_flight_ticket_price_list_displayed_low_to_hight() {

		TreeSet<Integer> obj = new TreeSet<Integer>();
		driver.findElement(By.xpath("//*[@id=\"data\"]/ul/li[1]/button")).click();

		List<WebElement> flightpriseele = driver
				.findElements(By.xpath("//ul[@id=\"flight--list-targets\"]/li/form/div/div/h6/strong"));
		

		for (WebElement alleleprise : flightpriseele) {
			
			String text = alleleprise.getText();
			String[] split = text.split(" ");

			float parseInt = Float.parseFloat(split[2]);
			
			int intconv = Math.round(parseInt);
					
			obj.add(intconv);

		}
		
		String[] split = flightpriseele.get(0).getText().split(" ");
		float parseInt = Float.parseFloat(split[2]);
		
		int intconv = Math.round(parseInt);

		assertEquals(intconv, obj.first());

	}

	@Then("Click on select Flight Button")
	public void click_on_select_flight_button() throws InterruptedException {
		
		driver.findElement(By.xpath("(//button[.=\"Select Flight\"])[1]")).click();
		Thread.sleep(1000);

	}

	@Then("verify Flight Review Page")
	public void verify_flight_review_page() {
		
		String text = driver.findElement(By.xpath("//h6[.=\"Oneway Flight Details\"]")).getText();
		assertEquals(text, "Oneway Flight Details");

	}

	public static void upDown(Integer value) {
		driver.findElement(By.xpath("(//*[local-name()=\"svg\"][@stroke-linecap=\"round\"])[" + value + "]")).click();
	}

}
