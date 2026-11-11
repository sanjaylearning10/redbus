package com.redbus.action;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SearchBus {
	
	@Test
	public void launceBrowser() throws InterruptedException
	{
		ChromeOptions chromeoptions=new ChromeOptions();
		
		chromeoptions.addArguments("--start-maximized");
		WebDriver driver=new ChromeDriver(chromeoptions);
		WebDriverWait webDriverWait=new WebDriverWait(driver,Duration.ofSeconds(60));
		//driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
		//driver.manage().window().maximize();
		driver.get("https://www.redbus.in/");
		By sourceLocator=By.xpath("//div[contains(@class,'srcDestWrapper') and @role='button']");
		WebElement source=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(sourceLocator));
		source.click();
		

		Thread.sleep(2000);
		
		enterCity(driver, webDriverWait,"Mumbai");
		enterCity(driver, webDriverWait,"Pune");
		/*
		
		WebElement destinationTextBox=driver.switchTo().activeElement();
		destinationTextBox.sendKeys("Pune");
	
	
		By destinationSuggestionList=By.xpath("//div[contains(@class,'searchSuggestionWrapper')]");
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(destinationSuggestionList));
		
		
		By destinationSearchOptionElement=By.xpath("//div[contains(@class,'searchCategory')]");
		List<WebElement> destinationSearchOption=webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(destinationSearchOptionElement,2));
		System.out.println("destinationSearchOption"+destinationSearchOption.size());
		
		List<WebElement> destinationAutoSuggestions=destinationSearchOption.get(0).findElements(By.xpath(".//div[contains(@class,'listHeader')]"));
		System.out.println("destinationAutoSuggestions::"+destinationAutoSuggestions.size());
		
		
		for(int i=0;i<destinationAutoSuggestions.size();i++)
		{
			System.out.println("destinationAutoSuggestions.get(i).getText()"+destinationAutoSuggestions.get(i).getText());
			if(destinationAutoSuggestions.get(i).getText().equalsIgnoreCase("Pune"))
			{
				
				destinationAutoSuggestions.get(i).click();
				
				break;
			}
		}
		
		*/
		
		
		By searchBusLocator=By.xpath("//button[contains(@class,'primaryButton')]");
		WebElement searchBusElement=webDriverWait.until(ExpectedConditions.elementToBeClickable(searchBusLocator));
		searchBusElement.click();
		
		By primoBusLocator=By.xpath("//div[contains(text(),'Primo Bus') and contains(@class,'label')]");
		WebElement primoBusElement=webDriverWait.until(ExpectedConditions.elementToBeClickable(primoBusLocator));
		primoBusElement.click();
		Thread.sleep(2000);
		
		By highRatedBusLocator=By.xpath("//div[contains(text(),'High Rated Buses') and contains(@class,'label')]");
		WebElement highRatedBusElement=webDriverWait.until(ExpectedConditions.elementToBeClickable(highRatedBusLocator));
		highRatedBusElement.click();
		
		/*
		
		By countOfBusLocator=By.xpath("//span[contains(@class,'subtitle')]");
		WebElement countOfBusElement=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(countOfBusLocator));
		System.out.print("countOfBusElement.getText()"+countOfBusElement.getText());
		*/
		
		By countOfBusLocator=By.xpath("//span[contains(@class,'subtitle')]");
		WebElement countOfBusElement=null;
		if(webDriverWait.until(ExpectedConditions.textToBePresentInElementLocated(countOfBusLocator,"buses"))) {
			 countOfBusElement=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(countOfBusLocator));
		}
		System.out.println("countOfBusElement.getText()"+countOfBusElement.getText());
		
		
		By findMoreResultsLocator=By.xpath("//h4[contains(text(),'Find more results') and contains(@class,'title')]");
		/*
		By findMoreResultsLocator=By.xpath("//h4[contains(text(),'Find more results') and contains(@class,'title')]");
		WebElement findMoreResultsElement=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(findMoreResultsLocator));
		System.out.print("findMoreResultsElement.isDisplayed()"+findMoreResultsElement.isDisplayed());
		*/
		
		
		By rowLocator=By.xpath("//li[contains(@class,'tupleWrapper')]");
		List<WebElement> rowLocatorElements=webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rowLocator));
		
		
		By busTravelNameLocator=By.xpath(".//div[contains(@class,'travelsName')]");
		List<WebElement> busTravelNameElements=webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(busTravelNameLocator));
		
		for(int i=0;i<rowLocatorElements.size();i++) {
		//System.out.println("busTravelNameElements.get(i).getText()"+rowLocatorElements.get(i).findElement(busTravelNameLocator).getText());
		}
		
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		//jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth'})", rowLocatorElements.get(rowLocatorElements.size()-3));
		//Thread.sleep(2000);
		
		List<WebElement> newBusTravelNameElements=webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(busTravelNameLocator,rowLocatorElements.size()-2));
		System.out.println("newBusTravelNameElements"+newBusTravelNameElements.size());
		
		while(true)
		{
			
			List<WebElement> rowLocatorElementsNew=webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(rowLocator));
			List<WebElement> findMoreResultsElementList=driver.findElements(By.xpath("//h4[contains(text(),'Find more results') and contains(@class,'title')]"));
		
			if(!findMoreResultsElementList.isEmpty())
			{
				break;
			}
			jse.executeScript("arguments[0].scrollIntoView({behavior:'smooth'})", rowLocatorElementsNew.get(rowLocatorElementsNew.size()-1));
			
		}
		
		List<WebElement> busTravelNameElementsFinalList=webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(busTravelNameLocator));
		
		for(int i=0;i<busTravelNameElementsFinalList.size();i++)
		{
			System.out.println("Travel Names===> "+busTravelNameElementsFinalList.get(i).getText());
		}
		driver.quit();
	}

	public void enterCity(WebDriver driver, WebDriverWait webDriverWait,String city) {
		WebElement sourceTextBox=driver.switchTo().activeElement();
		sourceTextBox.sendKeys(city);
		
		By suggestionList=By.xpath("//div[contains(@class,'searchSuggestionWrapper')]");
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(suggestionList));
		
		By searchOptionElement=By.xpath("//div[contains(@class,'searchCategory')]");
		//List<WebElement> searchOption=webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchOptionElement));
		List<WebElement> searchOption=webDriverWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchOptionElement,2));
		//System.out.println(searchOption.size());
		
		List<WebElement> autoSuggestions=searchOption.get(0).findElements(By.xpath(".//div[contains(@class,'listHeader')]"));
		//System.out.println("autoSuggestions::"+autoSuggestions.size());
		
		
		for(int i=0;i<autoSuggestions.size();i++)
		{
			//System.out.println("autoSuggestions.get(i).getText()"+autoSuggestions.get(i).getText());
			if(autoSuggestions.get(i).getText().equalsIgnoreCase(city))
			{
				autoSuggestions.get(i).click();
				break;
			}
		}
	}

}
