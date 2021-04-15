package com.sample.test.demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sample.test.demo.TestBase;

public class DemoTest extends TestBase {

	Select selectPizza;
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

	String topping1_xpath = "//div[@id='pizza1']//select[@class='toppings1']";
	String selectToppings2_xpath = "//div[@id='pizza1']//select[@class='toppings2']";
	String selectPizza_id = "pizza1Pizza";

	@Test
	public void demoTest() {
		System.out.println("HELLO WORLD");

	}

	//This test case validate happy path with successful pizza order process
	@Test
	public void sucessfulOrder() {

		choosePizza(selectPizza_id, 1);
		getToppings(topping1_xpath, "Mushrooms");
		getToppings(selectToppings2_xpath, "Classic Pepperoni");
		enterQuantity("2");
		getName();
		getEmail();
		getPhone();
		placeOrder();
		selectCashPayment();
		orderScucess();

	}
	
	
	//This test case validate unhappy path with and error of the pizza order process
	@Test
	public void orderWithError() {
		choosePizza(selectPizza_id, 2);
		getToppings(topping1_xpath, "Mushrooms");
		getToppings(selectToppings2_xpath, "Classic Pepperoni");
		enterQuantity("-2");

		getName();
		getEmail();
		getPhone();
		placeOrder();
		selectCashPayment();
		orderScucess();

	}

	//reusable method to choose a pizza
	public void choosePizza(String id, int index) {

		selectPizza = new Select(driver.findElement(By.id(id)));
		selectPizza.selectByIndex(index);

	}

	//reusable method to select a topping
	public void getToppings(String xpath, String vText) {
		selectToppings1 = new Select(driver.findElement(By.xpath(xpath)));

		selectToppings1.selectByVisibleText(vText);

	}

	//method that accepts quantity
	public void enterQuantity(String quantity) {
		pizzaQuantity = driver.findElement(By.id("pizza1Qty"));
		pizzaQuantity.sendKeys(quantity);
		pizzaQuantity.sendKeys(Keys.ENTER);
	}

	public void getName() {
		name = driver.findElement(By.id("name"));
		name.sendKeys("Ummul Mukta");

	}

	public void getEmail() {
		email = driver.findElement(By.id("email"));
		email.sendKeys("ummulmukta@gmail.com");

	}

	public void getPhone() {
		phone = driver.findElement(By.id("phone"));
		phone.sendKeys("646-515-0529");

	}

	public void placeOrder() {
		placeOrderButton = driver.findElement(By.id("placeOrder"));

		placeOrderButton.click();
	}

	public void selectCashPayment() {
		radioCash = driver.findElement(By.id("cashpayment"));

		radioCash.click();
	}

	public void orderScucess() {
		orderSuccessExpectedMessage = "Thank you for your order! TOTAL: 13.5 Small 6 Slices - no toppings";

		actualOrderSuccessMessage = driver.findElement(By.xpath("//div[@id='dialog']/p")).getText();
		System.out.println(actualOrderSuccessMessage);

		Assert.assertEquals(actualOrderSuccessMessage, orderSuccessExpectedMessage);

	}

	
}
