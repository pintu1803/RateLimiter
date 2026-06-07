import java.util.Deque;
import java.util.Map;

public class LeakyBucketStrategy implements RateLimitStrategyInterface {

    @Override
    public boolean handleRequest(Request request) {
        return true;
    }
    
}
