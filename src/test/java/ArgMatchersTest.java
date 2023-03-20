import com.example.mf.tests.TestClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.nsu.MockFramework;

import static Matching.AnyAndEq.eq;


public class ArgMatchersTest {

    private TestClass testClass;

    @Before
    public void setup() {
        testClass = MockFramework.mock(TestClass.class);
    }

    @Test
    public void test1() {
        MockFramework.when(testClass.foo(eq(25))).thenReturn("mocked");

        Assert.assertEquals("mocked", testClass.foo(25));
        Assert.assertNull(testClass.foo(11));
    }


    @Test
    public void invokeRealMethodWithMatcher() {
        MockFramework.when(testClass.foo(eq(100))).invokeRealMethod();
        Assert.assertEquals("Foo", testClass.foo(100));
    }


}