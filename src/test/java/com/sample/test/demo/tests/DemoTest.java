package com.sample.test.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sample.test.demo.TestBase;

public class DemoTest extends TestBase {

	Select select;
	Select selectToppings1;
	Select selectToppings2;
	WebElement pizzaQuantity;
	WebElement name;
	WebElement email;
	WebElement phone;
	WebElement placeOrderButton;
	WebElement radioCash;
	String orderSuccessExpectedMessage;
	String actualOrderSuccessMessage;

	@Test
	public void demoTest() {
		System.out.println("HELLO WORLD");

	}

	@Test
	public void sucessfulOrder() throws InterruptedException {

		choosePizza();
		getToppings1();
		getToppings2();
		enterQuantity();
		getName();
		getEmail();
		getPhone();
		placeOrder();
		selectCashPayment();
		orderScucess();

	}

	@Test
	public void orderWithError() throws InterruptedException {
		choosePizza();
		getToppings1();
		getToppings2();
		invalidQuantity();
		getName();
		getEmail();
		getPhone();
		placeOrder();
		selectCashPayment();
		orderScucess();

	}

	public void getToppings1() throws InterruptedException {
		selectToppings1 = new Select(driver.findElement(By.xpath("//div[@id='pizza1']//select[@class='toppings1']")));
		selectToppings1.selectByVisibleText("Mushrooms");
		Thread.sleep(1000);
	}

	public void getToppings2() throws InterruptedException {
		selectToppings2 = new Select(driver.findElement(By.xpath("//div[@id='pizza1']//select[@class='toppings2']")));
		selectToppings2.selectByVisibleText("Classic Pepperoni");
		Thread.sleep(1000);
	}

	public void enterQuantity() {
		pizzaQuantity = driver.findElement(By.id("pizza1Qty"));
		pizzaQuantity.sendKeys("2");
		pizzaQuantity.sendKeys(Keys.ENTER);
	}

	public void getName() throws InterruptedException {
		name = driver.findElement(By.id("name"));
		name.sendKeys("Ummul Mukta");
		Thread.sleep(1000);
	}

	public void getEmail() throws InterruptedException {
		email = driver.findElement(By.id("email"));
		email.sendKeys("ummulmukta@gmail.com");
		Thread.sleep(1000);
	}

	public void getPhone() throws InterruptedException {
		phone = driver.findElement(By.id("phone"));
		phone.sendKeys("646-515-0529");
		Thread.sleep(1000);
	}

	public void placeOrder() throws InterruptedException {
		placeOrderButton = driver.findElement(By.id("placeOrder"));
		Thread.sleep(1000);
		placeOrderButton.click();
	}

	public void selectCashPayment() throws InterruptedException {
		radioCash = driver.findElement(By.id("cashpayment"));
		Thread.sleep(1000);
		radioCash.click();
	}

	public void orderScucess() throws InterruptedException {
		orderSuccessExpectedMessage = "Thank you for your order! TOTAL: 13.5 Small 6 Slices - no toppings";

		actualOrderSuccessMessage = driver.findElement(By.xpath("//div[@id='dialog']/p")).getText();
		System.out.println(actualOrderSuccessMessage);
		Thread.sleep(1000);
		Assert.assertEquals(actualOrderSuccessMessage, orderSuccessExpectedMessage);

	}

	public void choosePizza() throws InterruptedException {
		select = new Select(driver.findElement(By.id("pizza1Pizza")));
		select.selectByIndex(1);
		Thread.sleep(1000);
	}

	public void invalidQuantity() {
		pizzaQuantity = driver.findElement(By.id("pizza1Qty"));
		pizzaQuantity.sendKeys("-2");
		pizzaQuantity.sendKeys(Keys.ENTER);
	}

}
