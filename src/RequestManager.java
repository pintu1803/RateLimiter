import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RequestManager {
    
    RateLimitStrategyInterface rateLimitStrategy = null;


    public void setRateLimtingAlgorithm(RateLimitStrategyInterface rateLimitStrategy) {
        this.rateLimitStrategy = rateLimitStrategy;
    }
    

    public void handleRequest(Request request) {
        boolean verdict = rateLimitStrategy.handleRequest(request);
        String status = verdict ? "Accepted" : "Rejected";
        System.out.println("Request : "+ request.getId() + " : " + request.getUser().getIp() + " : " + status);
    }


}
