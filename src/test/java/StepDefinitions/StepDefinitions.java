package StepDefinitions;

import Functions.SeleniumFunctions;
import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class StepDefinitions{
    WebDriver driver;
    SeleniumFunctions functions = new SeleniumFunctions();


    /******** Log Attribute ********/
    Logger log = Logger.getLogger(StepDefinitions.class);



    public StepDefinitions() {
        driver = Hooks.driver;
    }

    /******** Scenario Attributes ********/
    Scenario scenario = null;
    public void scenario (Scenario scenario) {
        this.scenario = scenario;
    }


    @Given("^Estoy en el sitio principal de la aplicación")
    public void iAmInAppMainSite() throws IOException {

        String url = functions.readProperties("MainAppUrlBase");
        log.info("Navegar a: " + url);
        driver.get(url);
    }

    @Given("^Navego a (.*)")
    public void navigateTo(String url){

        log.info("Navigate to: " + url);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Then("^Maximizo las ventanas")
    public void iMaximizeTheWindows() {
        driver.manage().window().maximize();
    }

    @Then("^Cargo la información del DOM (.*)")
    public void iLoadTheDOMInformation(String file) throws Exception {
        SeleniumFunctions.FileName= file.concat(".json");
        SeleniumFunctions.readJson();
        log.info("inicializar archivo: " + file );
    }

    @And("^Hago clic en el elemento (.*)")
    public void iClicInElement(String element) throws Exception {

        functions.iClicInElement(element);

    }

    @And("^Hago doble clic en el elemento que tiene (.*)")
    public void doubleClick(String element) throws Exception
    {
        functions.doubleClick(element);
    }

    @And("^Configuro el elemento (.*) con texto (.*)")
    public void iSetElementWithText(String element, String text) throws Exception {

        functions.iSetElementWithText(element, text);

    }

    @And("^Configuro el elemento (.*) con un email aleatorio")
    public void email(String element) throws Exception {

        functions.iSetElementWithText(element, functions.email());

    }

    @And("^Configuro el elemento (.*) con un nombre aleatorio")
    public void nombre(String element) throws Exception {

        functions.iSetElementWithText(element, functions.nombre());

    }

    @And("^Configuro el elemento (.*) con un apellido aleatorio")
    public void apellido(String element) throws Exception {

        functions.iSetElementWithText(element, functions.apellido());

    }

    @And("^Lo puse (.*?) con valor clave (.*?)$")
    public void iSetElementWithKeyValue(String element, String text) throws Exception {

        functions.iSetElementWithKeyValue(element, text);

    }

    /** Assert Text is present be present*/
    @Then("^Afirmar si (.*?) contiene texto (.*?)$")
    public void checkPartialTextElementPresent(String element,String text) throws Exception {

        functions.checkPartialTextElementPresent(element, text);

    }

    @Then("^Comprobar si (.*?) NO contiene texto (.*?)$")
    public void checkPartialTextElementNotPresent(String element,String text) throws Exception {

        functions.checkPartialTextElementNotPresent(element, text);

    }


    @And("^Tomo captura de pantalla: (.*)")
    public void takeScreenshot(String TestCaptura) throws IOException
    {
        functions.ScreenShot(TestCaptura);
    }


    @And("Adjunto una captura de pantalla para informar: (.*)")
    public void AttachAScreenshotToReport(String TestCaptura) {

        functions.attachScreenShot(TestCaptura);

    }

    /** Assert if element is present*/
    @Then("^Comprobar si (.*?) se visualiza$")
    public void checkIfElementIsPresent(String element) throws Exception {

        boolean isDisplayed = functions.isElementDisplayed(element);
        Assert.assertTrue("El elemento no está presente: " + element, isDisplayed);

    }

    /** Assert if element is present*/
    @Then("^Comprobar si (.*?) NO se muestra$")
    public void checkIfElementIsNotPresent(String element) throws Exception {

        boolean isDisplayed = functions.isElementDisplayed(element);
        Assert.assertFalse("El elemento está presente: " + element, isDisplayed);
    }

    /** Handle and accept a JavaScript alert */
    @Then("^Acepto alerta$")
    public void AcceptAlert()
    {
        functions.AcceptAlert();
    }

    /** Handle and dismiss a JavaScript alert */
    @Then("^Descarto alerta$")
    public void dismissAlert()
    {
        functions.dismissAlert();
    }

    /** Handle dropdown element by visible text */
    @And("Puse texto (.*?) en desplegable (.*?)$")
    public void iSetTextInDropdown(String option, String element) throws Exception {

        functions.selectOptionDropdownByText(element, option);
    }

    /** Handle dropdown element by index */
    @And("Puse el índice (.*?) en desplegable (.*?)$")
    public void selectOptionDropdownByIndex(int option, String element) throws Exception {

        functions.selectOptionDropdownByIndex(element, option);

    }

    /** Handle dropdown element by index */
    @And("Pongo valor (.*?) en desplegable (.*?)$")
    public void selectOptionDropdownByValue(String option, String element) throws Exception {

        functions.selectOptionDropdownByValue(element, option);

    }

    /** Check an option from a checkbox */
    @When("^Marque la casilla de verificación que tiene (.*?)$")
    public void checkCheckbox(String element) throws Exception
    {
        functions.checkCheckbox(element);
    }

    /** Check an option from a checkbox */
    @When("^Desmarco la casilla de verificación que tiene (.*?)$")
    public void UncheckCheckbox(String element) throws Exception
    {
        functions.UncheckCheckbox(element);
    }

    /** Refresh current page */
    @And("Actualizo la pagina$")
    public void refreshPage()
    {
        log.info("Actualizar página actual");
        driver.navigate().refresh();
    }

    /** Switch to a new windows */
    @When("^Cambio a nueva ventana$")
    public void switchNewWindow()
    {
        for(String winHandle : driver.getWindowHandles()){
            log.info("Cambiar a nuevas ventanas");
            driver.switchTo().window(winHandle);
        }
    }

    /** Switch to the previous windows */
    @When("^$Cambio a la ventana anterior")
    public void switchPreviousWindows()
    {
        log.info("Switching of previous windows");
        driver.switchTo().defaultContent();

    }

    /** I switch to Frame */
    @When("^Me cambio a Marco: (.*?)$")
    public void switchToFrame(String Frame) throws Exception {
        functions.switchToFrame(Frame);

    }

    /** Switch to a new windows by windows title */
    @When("^Cambio a la ventana que tiene título \"(.*?)\"$")
    public void switchToNewWindowsByTitle(String windowTitle) throws Exception
    {
        log.info("Cambiar a las ventanas por título: " + windowTitle);
        driver.switchTo().window(windowTitle);
    }

    /** Close a windows by title */
    @And("^Cierro ventana\"(.*?)\"$")
    public void closeNewWindows(String windowTitle)
    {
        log.info("Cambiar a las ventanas por título: " + windowTitle);
        driver.switchTo().window(windowTitle);
        log.info("Cerrar ventanas: "+ windowTitle);
        driver.close();
    }

    /** Zoom out until the element is displayed  */
    @And("^Alejo la página hasta que veo el elemento \"(.*?)\"$")
    public void zoomTillElementDisplay(String element) throws Exception
    {
        functions.zoomTillElementDisplay(element);
    }

    /** Scroll to the (top/end) of the page. */
    @And("^Me desplazo a (parte superior|extremo) de la página$")
    public void scrollPage(String to) throws Exception
    {
        functions.scrollPage(to);
    }

    /** Scroll to an element. */
    @And("^Me desplazo al elemento (.+)$")
    public void scrollToElement(String element) throws Exception
    {
        functions.scrollToElement(element);
    }


    @And("Cambio al marco principal")
    public void iSwitchToParentFrame() {

        functions.switchToParentFrame();
    }

    /** I click in JS element. */
    @And("^Hago clic en el elemento JS (.+)$")
    public void ClickJSElement(String element) throws Exception
    {
        functions.ClickJSElement(element);
    }

    /** Navigate forward */
    @And("^Navego hacia adelante")
    public void navigateForward()
    {
        log.info("Navego hacia adelante");
        driver.navigate().forward();
    }

    /** Navigate backward */
    @And("^Navego hacia atrás")
    public void navigateBack()
    {
        log.info("Navego hacia atrás");
        driver.navigate().back();
    }


    /** Close the driver instance */
    @And("^Cierro el navegador$")
    public void close()
    {
        log.info("Cerrar navegadores");
        driver.close();
    }


    /** Wait for an element to be present for a specific period of time */
    @Then("^Espero que el elemento (.*) este presente$")
    public void waitForElementPresent(String element) throws Exception
    {
        functions.waitForElementPresent(element);
    }

    /** Wait for an element to be visible for a specific period of time */
    @Then("^Espero que el elemento (.*?) sea visible$")
    public void waitForElementVisible(String element) throws Exception
    {
        functions.waitForElementVisible(element);
    }


    @Then("^Guardar como clave de contexto de escenario (.*?) con valor (.*?)$")
    public void SaveInScenarioData(String key,String text) throws NoSuchFieldException {

        functions.SaveInScenario(key, text);

    }

    @And("^Guardo el texto de (.*?) como contexto de escenario$")
    public void iSaveTextOfElementAsScenarioContext(String element) throws Exception {

        String ScenarioElementText = functions.GetTextElement(element);
        functions.SaveInScenario(element+".text", ScenarioElementText);

    }

    @And("^Tiempo espera: (.*)")
    public void time(int t) throws IOException, InterruptedException {
        functions.tSleep(t);
    }

}
