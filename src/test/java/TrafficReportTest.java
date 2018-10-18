import com.brad.TrafficReport;
import junit.framework.TestCase;

public class TrafficReportTest extends TestCase {

    public void testSanity() throws Exception {
        assertTrue(true);
    }

    public void testDuplicateRegistration() {
        TrafficReport trafficReport = new TrafficReport();

        assertTrue(trafficReport.getRecieved() == 0);
        assertTrue(trafficReport.getDuplicates() == 0);
        assertTrue(trafficReport.getTotal() == 0);

        trafficReport.incrementRecieved();

        assertTrue(trafficReport.getRecieved() == 1);
        assertTrue(trafficReport.getDuplicates() == 0);
        assertTrue(trafficReport.getTotal() == 1);

        trafficReport.incrementDuplicate();

        assertTrue(trafficReport.getDuplicates() == 1);
    }


    public void testReportReset() {
        TrafficReport trafficReport = new TrafficReport();

        trafficReport.incrementRecieved();
        trafficReport.incrementDuplicate();

        trafficReport.emitReport();

        assertTrue(trafficReport.getRecieved() == 0);
        assertTrue(trafficReport.getDuplicates() == 0);
        assertTrue(trafficReport.getTotal() == 1);
    }
}