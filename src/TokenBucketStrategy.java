import java.time.Duration;
import java.time.LocalTime;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class TokenBucketStrategy implements RateLimitStrategyInterface {

    //Assumption: TOken bucket size will be same for all users
    //Rate filling is also same for allusers

    private Map<User, TokenBucket> tokenMap;
    private int tokenBucketSize;
    private int refilRatePerSecond;


    public TokenBucketStrategy(int tokenBucketSize, int refilRatePerSecond) {
        this.tokenMap = new HashMap<>();
        this.tokenBucketSize = tokenBucketSize;
        this.refilRatePerSecond = refilRatePerSecond;
    }


    /*
    Create the full capacity bucket on arrival of first request
    For subsequent request, first add the tokens if any timeElapsed b/w prev and current request.
    Finally consume the token to allow the request
    */

    @Override
    public boolean handleRequest(Request request) {

        User user = request.getUser();
        tokenMap.putIfAbsent(user, new TokenBucket(tokenBucketSize, tokenBucketSize));

        TokenBucket userBucket = tokenMap.get(user);
        LocalTime lastRefilTime = userBucket.getLastTokenRefilTime();
        if(lastRefilTime == null) {
            userBucket.setLastTokenRefilTime(request.getTime());
        } else {
            long timeElapsed = Duration.between(lastRefilTime, request.getTime()).getSeconds();
            userBucket.addTokenAvailable((int)timeElapsed * refilRatePerSecond);
            userBucket.setLastTokenRefilTime(request.getTime());
        }

        // System.out.println("Size="+tokenBucketSize+" avail="+userBucket.getTokenAvailable() + " lastTime="+userBucket.getLastTokenRefilTime());
 
        if(userBucket.getTokenAvailable() > 0) {
            userBucket.decreaseTokenAvailable();
            return true;
        }

        return false;

    }
    
}