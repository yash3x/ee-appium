    import io.appium.java_client.android.AndroidDriver;
    import io.appium.java_client.ios.IOSDriver;
    import io.appium.java_client.ios.IOSElement;
    import io.appium.java_client.remote.IOSMobileCapabilityType;
    import io.appium.java_client.remote.MobileCapabilityType;
    import io.appium.java_client.service.local.AppiumDriverLocalService;
    import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
    import io.appium.java_client.service.local.AppiumServiceBuilder;
    import org.openqa.selenium.remote.DesiredCapabilities;
    import org.testng.annotations.AfterTest;
    import org.testng.annotations.BeforeTest;
    import org.testng.annotations.Parameters;
    import java.io.File;
    import java.io.IOException;

    public class BaseIosTest {

        protected AppiumDriverLocalService service;
        protected IOSDriver<IOSElement> driver;
        static String appiumNodePath = "/usr/local/bin/node";
        static String appiumJSPath = "/usr/local/bin/appium";



        @Parameters({"wda", "deviceName", "port"})
        @BeforeTest(alwaysRun = true)
        public void before(String wda, String deviceName, String port) throws IOException {

            service=new AppiumServiceBuilder()
                    .usingPort(Integer.valueOf(port))
                    .usingDriverExecutable(new File(appiumNodePath))
                    .withAppiumJS(new File(appiumJSPath))
                    .build();
            service.start();

            if(service == null || !service.isRunning()){
                throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started");
            }

            File app = new File("/Users/YashwantDas/Dropbox/IntelliJIDEA/ee-appium/src/main/resources/test-app.app");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.4");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capabilities.setCapability(IOSMobileCapabilityType.WDA_LOCAL_PORT, Integer.valueOf(wda));
            //Wait
            capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 500000);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            driver = new IOSDriver<IOSElement>(service.getUrl(), capabilities);

        }

        @AfterTest(alwaysRun = true)
        public void after(){
            if(driver!=null){
                driver.quit();
            }
            if(service!=null){
                service.stop();
            }
        }

    }
