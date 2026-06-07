public class User {
    private String ip;
    private int requestCountThreshold;

    public User(String ip, int requestCountThreshold) {
        this.ip = ip;
        this.requestCountThreshold = requestCountThreshold;
    }

    
    public int getRequestCountThreshold() {
        return requestCountThreshold;
    }

    public void setRequestCountThreshold(int requestCountThreshold) {
        this.requestCountThreshold = requestCountThreshold;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    
}
