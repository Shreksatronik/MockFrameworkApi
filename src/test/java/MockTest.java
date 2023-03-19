import main.Annotations.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.example.mf.tests.TestClass;
import ru.nsu.MockFramework;
import org.junit.Test;

import static Matching.AnyAndEq.anyInt;

public class MockTest {
        private TestClass test;


    @Before
        public void setup() {
            test = MockFramework.mock(TestClass.class);
        }

        @Test
        public void shouldReturnNull() {
            Assert.assertNull(test.foo(35));
        }

        @Test
        public void shouldStub() {
            MockFramework.when(test.foo(111)).thenReturn("mocked");
            Assert.assertEquals("mocked", test.foo(111));
        }

        @Test
        public void shouldOverrideStub() {
            MockFramework.when(test.foo(anyInt())).thenReturn("anyint");
            MockFramework.when(test.foo(11)).thenReturn("override");

            Assert.assertEquals("anyint", test.foo(56));
            Assert.assertEquals("override", test.foo(11));
        }

        @Test
        public void shouldCallRealMethod() {
            MockFramework.when(test.foo(123)).invokeRealMethod();
            Assert.assertEquals("Foo", test.foo(123));
        }


        @Test(expected = IllegalArgumentException.class)
        public void shouldThrow() {
            MockFramework.when(test.foo(333)).thenThrow(new IllegalArgumentException());

            test.foo(333);
        }
    }
