package driver;

import io.qameta.allure.Step;
import org.testng.Assert;

public final class ViceAssert {

    @Step("Asserting {0} with {1}")
    public static void assertEquals(Object var0, Object var1) {
        Assert.assertEquals(var0, var1);
    }

    @Step("Asserting {0} with {1}")
    public static void assertEquals(boolean var0, boolean var1) {
        Assert.assertEquals(var0, var1);
    }
}
