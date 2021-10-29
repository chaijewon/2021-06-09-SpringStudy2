package com.sist.data;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class MainClass {

	public static void main( String[] args ) { 
		// 크롬 드라이버를 가지고 옴 
		final File driverFile = new File("c:\\node\\chromedriver.exe"); 
		final String driverFilePath = driverFile.getAbsolutePath(); 
		if(!driverFile.exists() && driverFile.isFile()) 
		{ 
			throw new RuntimeException("Not found file. or this is not file. <" + driverFilePath + ">"); 
		} 
		final ChromeDriverService service; 
		service = new ChromeDriverService.Builder() .usingDriverExecutable(driverFile) .usingAnyFreePort() .build(); 
		try { 
		       service.start(); 
		} catch (IOException e)
		{ 
		e.printStackTrace(); 
		} 
		final WebDriver driver = new ChromeDriver(service); 
		final WebDriverWait wait = new WebDriverWait(driver, 10); 
		// 크롤링 시작
		 try { 
		driver.get("https://google.com/ncr"); 
		Thread.sleep(5000); 
		// Let the user actually see something! 
		driver.findElement(By.name("q")).sendKeys("cheese" + Keys.ENTER); 
		final WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3>div")));
		 System.out.println(firstResult.getAttribute("textContent")); 
		} catch (Exception e) { e.printStackTrace(); } finally { 
		// 프로그램이 종료되면 resource 해제 
		driver.quit(); service.stop(); }
		 
	}


		
	 
	}



