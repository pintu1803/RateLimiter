import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class LeakyBucketStrategy implements RateLimitStrategyInterface {

    //Assumption : Leak rate is per second
    //For example: leakRate = 5

    private Map<User, RequestBucket> bucketMap;
    private int requestBucketSize;
    private int leakRate;
    
    public LeakyBucketStrategy(int requestBucketSize, int leakRate) {
        this.bucketMap = new HashMap<>();
        this.requestBucketSize = requestBucketSize;
        this.leakRate = leakRate;
    }


    @Override
    public boolean handleRequest(Request request) {
        User user = request.getUser();
        bucketMap.putIfAbsent(user, new RequestBucket(requestBucketSize, leakRate));

        RequestBucket userBucket = bucketMap.get(user);
        if(userBucket.addRequestInBucket(request))
            return true;
        else 
            return false;
    }


    
}