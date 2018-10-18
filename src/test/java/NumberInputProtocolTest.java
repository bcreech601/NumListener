import com.brad.NumberInputProtocol;
import junit.framework.TestCase;

public class NumberInputProtocolTest extends TestCase {

    public void testSanity() throws Exception{
        assertTrue( true);
    }

    public void testTermination(){
        NumberInputProtocol numberInputProtocol = new NumberInputProtocol();

        assertTrue( numberInputProtocol.isTerminateInput(("terminate")));
    }

    public void testTerminationNegative(){
        NumberInputProtocol numberInputProtocol = new NumberInputProtocol();

        assertFalse( numberInputProtocol.isTerminateInput("jhdlkfjasdhl"));
    }

    public void testInputValidNumber(){
        NumberInputProtocol numberInputProtocol = new NumberInputProtocol();

        assertTrue( numberInputProtocol.isValidInput("123456789"));
        assertTrue( numberInputProtocol.isValidInput("000000001"));
    }

    public void TestInputValidNumberNegative(){
        NumberInputProtocol numberInputProtocol = new NumberInputProtocol();

        assertFalse( numberInputProtocol.isValidInput("daadgfd"));
        assertFalse( numberInputProtocol.isValidInput("12345678"));
        assertFalse( numberInputProtocol.isValidInput("1234567890"));
        assertFalse( numberInputProtocol.isValidInput("12345678a"));
        assertFalse( numberInputProtocol.isValidInput("q23456789"));




    }
}
