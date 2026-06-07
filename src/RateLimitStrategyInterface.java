import java.util.Deque;
import java.util.Map;

public interface RateLimitStrategyInterface {

    public boolean handleRequest(Request request);

}
