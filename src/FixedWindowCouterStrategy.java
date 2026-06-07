import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowCouterStrategy implements RateLimitStrategyInterface {
    
    private LocalTime startTime = null;
    private int windowSizeInSeconds;
    private Map<User, Deque<Request>> requestMap;


    public FixedWindowCouterStrategy(int windowSizeInSeconds) {
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.requestMap = new ConcurrentHashMap<>();
    }


    @Override
    public boolean handleRequest(Request request) {
        
        LocalTime requestTime = request.getTime();
        

        if(startTime == null) { 
            startTime = requestTime;
            requestMap.putIfAbsent(request.getUser(), new ArrayDeque<>());
            requestMap.get(request.getUser()).add(request);
            return true;

        }

        if( requestTime.isBefore(startTime.plusSeconds(windowSizeInSeconds)) || requestTime.equals(startTime.plusSeconds(windowSizeInSeconds)) ) {
            if(requestMap.get(request.getUser()).size() < request.getUser().getRequestCountThreshold()) {
                requestMap.get(request.getUser()).add(request);
                return true;
            } else {
                return false;
            }
        }

        if( requestTime.isAfter(startTime.plusSeconds(windowSizeInSeconds)) ) {
            startTime = requestTime;
            requestMap.put(request.getUser(), new ArrayDeque<>());
            requestMap.get(request.getUser()).add(request);
            return true;
        }

        return false;
    }


}
