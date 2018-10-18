import com.brad.DedupeStrategy;
import junit.framework.TestCase;

public class DedupeTest extends TestCase {

    public DedupeTest(String name) {
        super( name );
    }

    public void testSanity() throws Exception{
        assertTrue( true);
    }

    public void testDuplicationDetection() throws Exception{
        DedupeStrategy dedupeStrategy = new DedupeStrategy();

        dedupeStrategy.addKey("123456789");
        assertTrue( dedupeStrategy.isDuplicate(("123456789")));
    }

    public void testNegativeDuplicationDetection(){
        DedupeStrategy dedupeStrategy = new DedupeStrategy();

        dedupeStrategy.addKey("123456789");
        dedupeStrategy.addKey("8");
        dedupeStrategy.addKey("10");
        assertTrue( dedupeStrategy.isDuplicate("9") == false );
    }
}
