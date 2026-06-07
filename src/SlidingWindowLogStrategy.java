import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLogStrategy implements RateLimitStrategyInterface {

    private int windowSizeInSeconds;
    private Map<User, Deque<Request>> requestMap;


    public SlidingWindowLogStrategy(int windowSizeInSeconds) {
        this.windowSizeInSeconds = windowSizeInSeconds;
        this.requestMap = new ConcurrentHashMap<>();
    }


    @Override
    public boolean handleRequest(Request request) {
        
        LocalTime currentTime = request.getTime();
        LocalTime windowBeginTime = currentTime.minusSeconds(windowSizeInSeconds);

        requestMap.putIfAbsent(request.getUser(), new ArrayDeque<>());

        Deque<Request> userDeque = requestMap.get(request.getUser());
        while(!userDeque.isEmpty() && userDeque.peekFirst().getTime().isBefore(windowBeginTime)) {
            userDeque.pollFirst();
        }

        if(userDeque.size() < request.getUser().getRequestCountThreshold()) {
            userDeque.offerLast(request);
            return true;
        } else {
            return false;
        }
    }

    
}
