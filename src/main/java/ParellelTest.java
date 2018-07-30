import org.testng.annotations.Test;

public class ParellelTest extends BaseIosTest {

    @Test
    public void test(){
        driver.findElementByAccessibilityId("Alerts").click();
        driver.findElementByAccessibilityId("Create App Alert").click();
        driver.findElementByAccessibilityId("will do").click();
        driver.findElementByAccessibilityId("back").click();
        driver.findElementByAccessibilityId("Attributes").click();
        driver.findElementByAccessibilityId("Second").click();
        driver.findElementByAccessibilityId("First").click();
        driver.findElementByAccessibilityId("Back").click();
        driver.findElementByAccessibilityId("Scrolling").click();
        driver.findElementByAccessibilityId("TableView").click();
        driver.findElementByAccessibilityId("Back").click();
        driver.findElementByAccessibilityId("ScrollView").click();
        driver.findElementByAccessibilityId("Back").click();
    }
}
