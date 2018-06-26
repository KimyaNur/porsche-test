package com.cybertek;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Porsche {
	WebDriver driver;
	
	@BeforeClass /// runs once for all test
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.porsche.com/usa/modelstart/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
    
	@Test
	public void SetUp() throws InterruptedException {
		driver.findElement(By.xpath("//img[@alt='Porsche - 718']")).click();  //handle
		//driver.findElement(By.xpath("/html/body/div[2]/div[4]/div/div[2]/a[1]/div/div[1]/img")).click();
		driver.findElement(By.xpath("(//span[.='Build & Price'])[1]")).click();    //handle
		//driver.findElement(By.xpath("(//span[.='Build & Price'])[1]")).click();
		
		
		String basePrice = driver.findElement(By.xpath("//div[@class='m-14-model-price'][contains(text(),'From $ 56,900.00*')]")).getText().replaceAll("[ From$,*]", "");   //handle xpath
		//String basePrice = driver.findElement(By.xpath("//*[@id=\"m982120\"]/div[1]/div[2]/div[2]")).getText().replaceAll("[ From$,*]", "");
		double basePriceD = Double.parseDouble(basePrice);
		System.out.println(basePriceD);
		
		//driver.findElement(By.xpath("//*[@id=\"m982120\"]/div[2]/div/a/span")).click();      //Build & Price under 718Cayman click
		driver.findElement(By.xpath("//*[@id='m982120']")).click();       //handle
		
		// Perform the click operation that opens new window
		
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		  driver.switchTo().window(winHandle);
		}		
	
		
		
		//String basePr =driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[1]/div[2]")).getText().replaceAll("[,$]","");
		String basePr =driver.findElement(By.xpath("//section[@id='s_price']//div[@class='ccaPrice'][contains(text(),'$56,900')]")).getText().replaceAll("[,$]","");    //handle
		double basePrD=Double.parseDouble(basePr);
		System.out.println(basePrD);
		Assert.assertEquals(basePriceD,basePrD );
		
		//String s=driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText().replaceAll("[$]", "");
		String s=driver.findElement(By.xpath("//*[@id='s_price']//div[@class='ccaPrice'][contains(text(),'$0')]")).getText().replaceAll("[$]", "");
		double eprice=Double.parseDouble(s);
		Assert.assertEquals(eprice, 0.0);
		
		
		//String st=driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[3]/div[2]")).getText().replaceAll("[$,]", "");
		String st=driver.findElement(By.xpath("(//div[@class='ccaPrice'][contains(text(),'$1,050')])[2]")).getText().replaceAll("[$,]", "");    //handle
		double dprice=Double.parseDouble(st);
		//String tprice=driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText().replaceAll("[$,]", "");
		String tprice=driver.findElement(By.xpath("(//div[@class='ccaPrice'][contains(text(),'$57,950')])[2]")).getText().replaceAll("[$,]", "");
		double totalprice=Double.parseDouble(tprice);
		Assert.assertEquals(totalprice, basePrD+dprice);
		
		
		
		driver.findElement(By.xpath("//span[@style='background-color: rgb(0, 120, 138);']")).click();   //handle color
//		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_FJ5\"]/span")).click();
	
		//String miamiprice=driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText().replaceAll("[$,]", "");
		String miamiprice=driver.findElement(By.xpath("(//div[@class='ccaPrice'][contains(text(),'$2,580')])[2]")).getText().replaceAll("[$,]", "");   //handle
		double miamiPrice=Double.parseDouble(miamiprice);
		System.out.println("miamiPrice : "+miamiPrice);
		//String mblueprice=driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_IAF\"]/div[2]/div[1]/div/div[2]")).getText().replaceAll("[$,]", "");
		String mblueprice=driver.findElement(By.xpath("//div[@class='tt_price tt_cell'][contains(text(),'$2,580')]")).getText().replaceAll("[$,]", "");
		double mbluePrice=Double.parseDouble(mblueprice);
		System.out.println("miamiPrice : "+mbluePrice);
		Assert.assertEquals(miamiPrice, mbluePrice);
		
		
		totalprice=mbluePrice+eprice+totalprice;
		System.out.println("eprice : "+eprice);
		System.out.println("eprice+mbluePrice : "+totalprice);
		//double ttlprice=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText().replaceAll("[$,]", ""));
		double ttlprice=Double.parseDouble(driver.findElement(By.xpath("(//*[@class='ccaPrice'][contains(text(),'$60,530')])[2]")).getText().replaceAll("[$,]", ""));    //handle
		System.out.println(ttlprice);
		Assert.assertEquals(totalprice, ttlprice);
		
		
		//driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_MXRD\"]/span/span")).click();
		//driver.findElement(By.xpath("//*[@id='s_exterieur_x_MXRD']/span/span")).click();
		//driver.findElement(By.className("img-element")).click();
		//driver.findElement(By.xpath("//*[@id='s_exterieur_x_MXRD']/span/span")).click();
		//driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_MXRD\"]/span/span")).click();
		//driver.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']//span[@class='img-element']")).click();
		//driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_MXRD\"]/span/span")).click();
//		driver.manage().window().maximize();
//		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_MXRD\"]/span/span")).click();
//		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_M433\"]/span/span")).click();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
	      jse.executeScript("window.scrollBy(0,500)", "");

	    driver.findElement(By.xpath("(//span[@class='img-element'])[13]")).click();    //handle wheel
		//driver.findElement(By.xpath("//*[@id='s_exterieur_x_MXRD']/span/span")).click();
		
		
		
		//double wprice=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_IRA\"]/div[2]/div[1]/div/div[2]")).getText().replaceAll("[$,]", ""));
	    double wprice=Double.parseDouble(driver.findElement(By.xpath("//*[@class='tt_price tt_cell'][contains(text(),'$3,750')]")).getText().replaceAll("[$,]", ""));     //handle
	    //eprice=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText().replaceAll("[$,]", ""));
	    eprice=Double.parseDouble(driver.findElement(By.xpath("(//*[@class='ccaPrice'][contains(text(),'$6,330')])[2]")).getText().replaceAll("[$,]", ""));     //handle
		System.out.println("miamiPrice+wprice :eprice : "+(miamiPrice+wprice));
		Assert.assertEquals(eprice, miamiPrice+wprice);          

		totalprice=eprice+basePriceD+dprice;
		System.out.println(totalprice);
		System.out.println("totalprice=eprice+basePriceD+dprice: "+totalprice);
		//double ttl = Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText().replaceAll("[$,]", ""));
		double ttl = Double.parseDouble(driver.findElement(By.xpath("(//*[@class='ccaPrice'][contains(text(),'$64,280')])[2]")).getText().replaceAll("[$,]", ""));    //handle
		System.out.println(ttl);
		//Assert.assertEquals(totalprice,Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText().replaceAll("[$,]", "")));  
		Assert.assertEquals(totalprice,Double.parseDouble(driver.findElement(By.xpath("(//*[@class='ccaPrice'][contains(text(),'$64,280')])[2]")).getText().replaceAll("[$,]", "")));    //handle
   
		JavascriptExecutor jseat = (JavascriptExecutor) driver;
	      jseat.executeScript("window.scrollBy(0,800)", "");
		//driver.findElement(By.xpath("//*[@id=\"s_interieur_x_PP06\"]")).click();
		driver.findElement(By.xpath("//span[@id='s_interieur_x_PP06']")).click();    //handle seat
		//double seatprice=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"seats_73\"]/div[2]/div[1]/div[3]/div")).getText().replaceAll("[$,]", ""));
		double seatprice=Double.parseDouble(driver.findElement(By.xpath("(//*[contains(text(),'$2,330')])[1]")).getText().replaceAll("[$,]", ""));    //handle
		
		
		//eprice=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText().replaceAll("[$,]", ""));
		eprice=Double.parseDouble(driver.findElement(By.xpath("(//*[@class='ccaPrice'][contains(text(),'$8,660')])[2]")).getText().replaceAll("[$,]", ""));  //handle
		Assert.assertEquals(eprice, miamiPrice+wprice+seatprice);
		
		
		totalprice=eprice+basePriceD+dprice;
		System.out.println(totalprice);
		//double expected=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText().replaceAll("[$,]", ""));
		double expected=Double.parseDouble(driver.findElement(By.xpath("(//*[@class='ccaPrice'][contains(text(),'$66,610')])[2]")).getText().replaceAll("[$,]", ""));    //handle
		System.out.println(expected);
		Assert.assertEquals(totalprice,expected);
		
		
		
		JavascriptExecutor jcr = (JavascriptExecutor) driver;
	      jcr.executeScript("window.scrollBy(0,800)", "");
		driver.findElement(By.xpath("//div[@id='IIC_subHdl']")).click();
		
		
		
		driver.findElement(By.xpath("//span[@id='vs_table_IIC_x_PEKH_x_c01_PEKH']")).click();
		
		//double carbonprice=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"vs_table_IIC_x_PEKH\"]/div[1]/div[2]/div")).getText().replaceAll("[$,]", ""));
		double carbonprice=Double.parseDouble(driver.findElement(By.xpath("(//*[contains(text(),'$1,540')])[2]")).getText().replaceAll("[$,]", ""));   //handle
		eprice=carbonprice+eprice;
		System.out.println(eprice);
		//double expected2=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText().replaceAll("[$,]", ""));
		double expected2=Double.parseDouble(driver.findElement(By.xpath("(//*[@class='ccaPrice'][contains(text(),'$10,200')])[2]")).getText().replaceAll("[$,]", ""));    //handle
		System.out.println(expected2);
		Assert.assertEquals(eprice,expected2);
		
		
		
		totalprice=eprice+basePriceD+dprice;
		//double expected3=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText().replaceAll("[$,]", ""));
		double expected3=Double.parseDouble(driver.findElement(By.xpath("(//*[@class='ccaPrice'][contains(text(),'$68,150')])[2]")).getText().replaceAll("[$,]", ""));     //handle
		Assert.assertEquals(totalprice,expected3);
		
		
		driver.findElement(By.xpath("//div[@id='IMG_subHdl']")).click();
		driver.findElement(By.xpath("//span[@id='vs_table_IMG_x_M250_x_c11_M250']")).click();
		
		//double speedprice=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M250\"]/div[1]/div[2]/div")).getText().replaceAll("[$,]", ""));
		double speedprice=Double.parseDouble(driver.findElement(By.xpath("(//*[contains(text(),'$3,210')])[1]")).getText().replaceAll("[$,]", ""));  //handle
		
		JavascriptExecutor jccb = (JavascriptExecutor) driver;
	      jccb.executeScript("window.scrollBy(0,500)", "");
		driver.findElement(By.xpath("//span[@id='vs_table_IMG_x_M450_x_c91_M450']")).click();
		//double ccbprice=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"vs_table_IMG_x_M450\"]/div[1]/div[2]/div")).getText().replaceAll("[$,]", ""));
		double ccbprice=Double.parseDouble(driver.findElement(By.xpath("(//*[contains(text(),'$7,410')])[1]")).getText().replaceAll("[$,]", ""));     //handle
		 
		
		//eprice=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText().replaceAll("[$,]", ""));
		eprice=Double.parseDouble(driver.findElement(By.xpath("(//*[@class='ccaPrice'][contains(text(),'$20,820')])[2]")).getText().replaceAll("[$,]", ""));   //handle
		Assert.assertEquals(eprice, miamiPrice+wprice+seatprice+ccbprice+carbonprice+speedprice);
		
		
		totalprice=eprice+basePriceD+dprice;
		//double expected4=Double.parseDouble(driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText().replaceAll("[$,]", ""));
		double expected4=Double.parseDouble(driver.findElement(By.xpath("(//div[contains(text(),'$78,770')])[3]")).getText().replaceAll("[$,]", ""));
		Assert.assertEquals(totalprice,expected4);
		
	}

}
