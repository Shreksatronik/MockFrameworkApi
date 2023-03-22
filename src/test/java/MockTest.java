import Annotation.Annotations.Mock;
import Annotation.Annotations.MockInit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.example.mf.tests.TestClass;
import Main.MockFramework;

import static Matching.AnyAndEq.anyInt;

public class MockTest {
        @Mock
        TestClass test;

    @Before
    public void setUp() {
        MockInit.initMocks(this);
    }


        @Test
        public void ReturnNullTest() {
            Assert.assertNull(test.foo(35));
        }

        @Test
        public void StubTest() {
            MockFramework.when(test.foo(111)).thenReturn("mocked");
            Assert.assertEquals("mocked", test.foo(111));
        }

        @Test
        public void OverrideStubTest() {
            MockFramework.when(test.foo(anyInt())).thenReturn("anyint");
            MockFramework.when(test.foo(11)).thenReturn("override");

            Assert.assertEquals("anyint", test.foo(56));
            Assert.assertEquals("override", test.foo(11));
        }

        @Test
        public void CallRealMethodTest() {
            MockFramework.when(test.foo(123)).invokeRealMethod();
            Assert.assertEquals("Foo", test.foo(123));
        }


        @Test(expected = IllegalArgumentException.class)
        public void ThrowExceptionTest() {
            MockFramework.when(test.foo(333)).thenThrow(new IllegalArgumentException());
            test.foo(333);
        }
    }
