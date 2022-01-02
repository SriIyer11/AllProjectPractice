package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class SignInPage extends BaseClass {
//Sign-In
@FindBy(className="a-link-nav-icon")//(class=), //(xpath=)
static WebElement AmazonLogo;

//after signin reloaded
@FindBy(how=How.ID,using="signInSubmit")
static WebElement SignIn;

//after signin reloaded
@FindBy(how=How.ID,using="ap_password")
static WebElement Password;

//after signin reloaded
@FindBy(how=How.PARTIAL_LINK_TEXT,using="Forgot Password")
static WebElement ForgotPassword;

//after signin reloaded
@FindBy(how=How.PARTIAL_LINK_TEXT,using="Details")
static WebElement Details;

//after signin reloaded
@FindBy(how=How.PARTIAL_LINK_TEXT,using="Change")
static WebElement Change;

//after signin reloaded
@FindBy(how=How.NAME,using="rememberMe")
static WebElement RememberMeCheckbox;

//after signin reloaded
@FindBy(how=How.XPATH,using="//*[contains(text(),'Keep me signed in.')]")
static WebElement KeepmeSignedIn;

@FindBy(css="input[type=email]")//(class=), //(xpath=)
static WebElement EmailorMobilePhoneNumber;

@FindBy(xpath="//span[@id='continue']")//(class=), //(xpath=)
static WebElement Continue;

@FindBy(id="legalTextRow")//(class=), //(xpath=)
static WebElement legalTermsnConditions;

@FindBy(xpath="//*[contains(text(),'Need help?')]/parent::a")//(class=), //(xpath=)
static WebElement NeedHelp;

@FindBy(xpath="//*[contains(text(),'New to Amazon?')]")//(class=), //(xpath=)
static WebElement NewtoAmazon;

@FindBy(id="createAccountSubmit")//(class=), //(xpath=)
static WebElement CreateYourAmazonAccount;

@FindBy(partialLinkText="Conditions of Use")//(class=), //(xpath=)
static WebElement ConditionsofUse;

@FindBy(partialLinkText="Privacy Notice")//(class=), //(xpath=)
static WebElement PrivacyNotice;

@FindBy(partialLinkText="Help")//(class=), //(xpath=)
static WebElement Help;

public static void EnterEMailMobNumber(String input)
{
	EmailorMobilePhoneNumber.sendKeys(input);
}

/*public static SignInPage clickContinue()
{
	Continue.click();
	return new SignInPage(driver);
}*/

public static void clickContinue()
{
	Continue.click();
}

public static void EnterPassword(String input)
{
	Password.sendKeys(input);
}

public static void clickSignInButton()
{
	SignIn.click();
}

public SignInPage()
{
	super();
	PageFactory.initElements(driver, this);
}

public SignInPage(WebDriver driver)
{
	super(driver);
	SignInPage.driver=driver;
	PageFactory.initElements(driver, this);
}
	////div[@id='legalTextRow']/child::a[contains(text(),'Conditions of Use')]

//amazon logo must exists and displayed
//email or phone num
//Continue
//Need Help
//Create Amazon Account
//New to Amazon?
//ConditionsofUse
//PrivacyNotice
//Help
//TermsnConditions
//DisclaimerText	
	
}
