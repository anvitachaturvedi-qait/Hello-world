package TatocAutomation2.TatocAutomation2;


import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class BasicCourse {
	WebDriver driver;
	
	@BeforeClass
	public void launch()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\anvitachaturvedi\\Desktop\\SeleniumFiles\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		String url="http://10.0.1.86/tatoc";
		driver.get(url);
	}
	
	public void begin() throws InterruptedException
	{
		driver.findElement(By.linkText("Basic Course")).click();
		
		driver.findElement(By.className("greenbox")).click();
		
		driver.switchTo().frame("main");
		String Box1=driver.findElement(By.xpath("//div[text()='Box 1']")).getAttribute("class");
		System.out.println(Box1);
		driver.switchTo().frame("child");
		String Box2= "white";
		System.out.println(Box2);
		while (Box1.equalsIgnoreCase(Box2)==false)
		{
			driver.switchTo().parentFrame();
		    driver.findElement(By.linkText("Repaint Box 2")).click();
		    Thread.sleep(1000);
		    driver.switchTo().frame("child");
		    Box2=driver.findElement(By.xpath("//div[text()='Box 2']")).getAttribute("class");
		}
		driver.switchTo().parentFrame();
		driver.findElement(By.linkText("Proceed")).click();
		
		Actions act=new Actions (driver);
		act.dragAndDrop(driver.findElement(By.id("dragbox")), driver.findElement(By.id("dropbox"))).build().perform();
		driver.findElement(By.linkText("Proceed")).click();
		
		driver.findElement(By.linkText("Launch Popup Window")).click();
		String MainWindow=driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles())
		driver.switchTo().window(winHandle);
		driver.findElement(By.id("name")).sendKeys("Anvita");
		driver.findElement(By.id("submit")).click();
		driver.switchTo().window(MainWindow);
		driver.findElement(By.linkText("Proceed")).click();
		
		driver.findElement(By.linkText("Generate Token")).click();
		String val=driver.findElement(By.id("token")).getText();
		String tokenval= val.substring(7);
		System.out.println(tokenval);
	    Cookie name= new Cookie ("Token", tokenval);
	    driver.manage().addCookie(name);
	    driver.findElement(By.linkText("Proceed")).click();
	    
	    
	}
	

}
