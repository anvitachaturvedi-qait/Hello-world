package TatocAutomation2.TatocAutomation2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class AdvancedCourse {
	WebDriver driver;
	@BeforeClass
	public void launch ()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\anvitachaturvedi\\Desktop\\SeleniumFiles\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		String url="http://10.0.1.86/tatoc";
		driver.get(url);
	}
	
	@Test
	public void advanced () throws ClassNotFoundException
	{
		driver.findElement(By.linkText("Advanced Course")).click();
		Actions act=new Actions (driver);
		act.moveToElement(driver.findElement(By.className("menutitle"))).perform();
		driver.findElement(By.xpath("//span[@onclick='gonext();']")).click();
		
		String sym=driver.findElement(By.id("symboldisplay")).getText();
		System.out.println("Symbol="+sym);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://10.0.1.86:3306/tatoc", "tatocuser", "tatoc01");
			
			Statement st=con.createStatement();
			String query = "select id FROM identity WHERE symbol='" + sym + "';";
			System.out.println(query);
			ResultSet rs1=st.executeQuery(query);
			int idno=0;
			while(rs1.next())
			{
				idno=rs1.getInt(1);
				System.out.println("id number="+idno);
			}
			
			String query2="select name, passkey FROM credentials WHERE id='" + idno + "';";
			System.out.println(query2);
			ResultSet rs2=st.executeQuery(query2);
			String Name1="";
			String passkey1="";
			while(rs2.next())
			{
				Name1=rs2.getString("name");
				passkey1=rs2.getString("passkey");
				System.out.println("Name1=" + Name1);
				System.out.println("passkey1=" + passkey1);
			}
			driver.findElement(By.id("name")).sendKeys(Name1);
			driver.findElement(By.id("passkey")).sendKeys(passkey1);
			driver.findElement(By.id("submit")).click();
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
