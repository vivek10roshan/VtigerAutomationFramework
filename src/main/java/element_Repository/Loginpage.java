package element_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	//Constructor
	public Loginpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getUsernameTextfield() {
		return usernameTextfield;
	}

	public void setUsernameTextfield(WebElement usernameTextfield) {
		this.usernameTextfield = usernameTextfield;
	}

	public WebElement getPasswordTextfield() {
		return passwordTextfield;
	}

	public void setPasswordTextfield(WebElement passwordTextfield) {
		this.passwordTextfield = passwordTextfield;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}

	public void setLoginButton(WebElement loginButton) {
		this.loginButton = loginButton;
	}

	@FindBy(name = "user_name")
	private WebElement usernameTextfield;
	
	@FindAll({@FindBy(name = "user_password"), @FindBy(xpath = "//input[@type = 'password']")})
	private WebElement passwordTextfield;
	
	@FindBy(id = "submitButton")
	private WebElement loginButton;
	

}
