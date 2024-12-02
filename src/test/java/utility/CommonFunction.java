package utility;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonFunction {
	
	
	public static  void dropDown(WebElement element ,String dropDownValue) {
		
		Select sc = new Select(element);
		List<WebElement> allOptions = sc.getOptions();
		
		for (WebElement optionele : allOptions) {
			System.out.println(optionele.getText());
			if (optionele.getText().equals(dropDownValue)) {
				optionele.click();
				break;
			}
		} 
		
	}

}
