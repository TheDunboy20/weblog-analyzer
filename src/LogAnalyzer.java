
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;


import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
     private String extractFromString(String parsedDate){
        StringBuilder returnString = new StringBuilder("");
        returnString = returnString.append(parsedDate.substring(4,10));
        return returnString.toString();
     }
     public void readFile(String filename) {
         FileResource file = new FileResource(filename);
         for (String line: file.lines()) {
            LogEntry entry = WebLogParser.parseEntry(line);
            records.add(entry);
         }
     }
     public int countUniqueIP () {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry record: records) {
            String currIp = record.getIpAdress();
            if (!uniqueIPs.contains(currIp)) {
                uniqueIPs.add(currIp);
            }
        }
        return uniqueIPs.size();
     }
     public void printAllHigherThanNum(int num) {
        for (LogEntry record: records) {
            int currCode = record.getStatusCode();
            if (currCode > num) {
                System.out.println(record);
            }
        }
    }       
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public ArrayList<String> uniqueIPVisitsOnDay(String someDay){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry record: records) {
            
            String currDate = record.getAcessTime().toString();
            String newDate = extractFromString(currDate);
            if (newDate.equals(someDay)) {
                if (!uniqueIPs.contains(record.getIpAdress())) {
                    uniqueIPs.add(record.getIpAdress());
                }
            }
        }
        return uniqueIPs;

     }
    public int countUniqueIPsInRange (int low, int high) {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry record: records){
            int currentCode = record.getStatusCode();
            String currentIp = record.getIpAdress();
            if ((currentCode >= low) && (currentCode <= high)) {
                if (!uniqueIPs.contains(currentIp)){
                    uniqueIPs.add(currentIp);
                }
            }
        }
        return uniqueIPs.size();
    }
    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> counts = new HashMap<String, Integer>();

        for (LogEntry entry: records) {
            String currentIp = entry.getIpAdress();
            if (counts.containsKey(currentIp)) {
                int value = counts.get(currentIp);
                value = value + 1;
                counts.put(currentIp, value);
            }else {
                counts.put(currentIp, 1);
            }
        }
        return counts;
    }
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
        int max = 0;
        for (int value: counts.values()) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
        int maxNumber = mostNumberVisitsByIP(counts);
        ArrayList<String> maxIPs = new ArrayList<String>();
        for (String key: counts.keySet()) {
            int currentVal = counts.get(key);
            if (currentVal == maxNumber) {
                maxIPs.add(key);
            }

        }
        return maxIPs;
    }
    public HashMap<String, ArrayList<String>>iPsForDays() {
        HashMap<String,ArrayList<String>> IpCounts = new HashMap<String,ArrayList<String>>(); 
        for (LogEntry record: records){
            ArrayList<String> IpArr = new ArrayList<String>();
            String currDate = record.getAcessTime().toString();
            String newDate = extractFromString(currDate);
            if (IpCounts.containsKey(newDate)) {
                ArrayList<String> currenArr = IpCounts.get(newDate);
                currenArr.add(record.getIpAdress());
                IpCounts.put(newDate, currenArr);
            }else {
                IpArr.add(record.getIpAdress());
                IpCounts.put(newDate, IpArr);

            }
        }
        return IpCounts;
    }
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> IPsByDay) {
        int max = 0;
        String currentMaxString = "";
        for (String day: IPsByDay.keySet()) {
            int currentNum = IPsByDay.get(day).size();
            if (currentNum > max) {
                max = currentNum;
                currentMaxString = day;
            }
        }
        return currentMaxString;
    }
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>>ipDays, String day){
        ArrayList<String> maxIps = new ArrayList<String>();
        ArrayList<String> ipOnGivenDay = new ArrayList<String>();
        HashMap<String, Integer> partialMap = new HashMap<String, Integer>();
        ipOnGivenDay = ipDays.get(day);

        for (String ipAdress : ipOnGivenDay) {
            if (partialMap.containsKey(ipAdress)) {
                int value = partialMap.get(ipAdress);
                value = value + 1;
                partialMap.put(ipAdress, value);
            }else {
                partialMap.put(ipAdress, 1);
            }
        }
        maxIps = iPsMostVisits(partialMap);
        return maxIps;
    }
}
