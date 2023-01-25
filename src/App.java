import java.util.ArrayList;
import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {
        LogAnalyzer test = new LogAnalyzer();
            test.readFile("logs/weblog1_log");
            // test.printAll();
            // System.out.println("Number of unique ips: " + test.countUniqueIP());
            // System.out.println(test.countUniqueIPsInRange(300, 399));
            // test.printAllHigherThanNum(400);
            // int returnedVal = test.uniqueIPVisitsOnDay("Mar 17").size();
            // System.out.println(returnedVal);
            // System.out.println(test.countVisitsPerIP());
            // HashMap<String, Integer> counts = test.countVisitsPerIP();
            // System.out.println(test.mostNumberVisitsByIP(counts));
            // System.out.println(test.iPsMostVisits(counts));
            HashMap<String, ArrayList<String>> counts = test.iPsForDays();
            // System.out.println(test.dayWithMostIPVisits(counts));
            System.out.println(test.iPsWithMostVisitsOnDay(counts, "Mar 17"));
    }
}
