import com.example.mf.tests.TestClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Main.MockFramework;

import static Matching.AnyAndEq.anyInt;
import static Matching.AnyAndEq.eq;


public class ArgMatchersTest {

    private TestClass testClass;

    @Before
    public void setup() {
        testClass = MockFramework.mock(TestClass.class);
    }

    @Test
    public void StubArgumentMatchersTest() {
        MockFramework.when(testClass.foo(eq(25))).thenReturn("mocked");

        Assert.assertEquals("mocked", testClass.foo(25));
        Assert.assertNull(testClass.foo(11));
    }


    @Test
    public void invokeRealMethodWithMatcherTest_1() {
        MockFramework.when(testClass.foo(eq(100))).invokeRealMethod();
        Assert.assertEquals("Foo", testClass.foo(100));
    }

    @Test
    public void invokeRealMethodWithMatcherTest_2() {
        MockFramework.when(testClass.foo(anyInt())).invokeRealMethod();
        Assert.assertEquals("Foo", testClass.foo(56));
    }

}