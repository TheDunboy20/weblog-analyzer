import java.util.Date;
public class LogEntry {
    private String ipAdress;
    private Date acessTime;
    private String request;
    private int statusCode;
    private int bytesReturned;

    public LogEntry(String ip, Date time, String req, int status, int bytes) {
        ipAdress = ip;
        acessTime = time;
        request = req;
        statusCode = status;
        bytesReturned = bytes;
    }
    public String getIpAdress() {
        return ipAdress;
    }
    public Date getAcessTime () {
        return acessTime;
    }
    public String getRequest() {
        return request;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public int getBytesReturned () {
        return bytesReturned;
    }

    public String toString() {
        return ipAdress + " " + acessTime + " " + request + " " + statusCode + " " + bytesReturned;
    }

}