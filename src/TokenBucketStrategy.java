import java.time.LocalTime;
import java.util.Deque;
import java.util.Map;

public class TokenBucketStrategy implements RateLimitStrategyInterface {

    private int tokenConsumed;
    private int tokenBucketSize;
    private int refilTime;
    private LocalTime lastTokenRefilTime;
    private LocalTime nextTokenRefilTime;


    public TokenBucketStrategy(int tokenBucketSize, int refilTime) {
        this.tokenBucketSize = tokenBucketSize;
        this.refilTime = refilTime; 
        this.tokenConsumed = 0;
        this.lastTokenRefilTime = null;
        this.nextTokenRefilTime = null;
    }


    @Override
    public boolean handleRequest(Request request) {

        // if(tokenConsumed < tokenBucketSize) {
        //     lastConsumedTokenTime = request.getTime();
        //     tokenConsumed += 1;

        //     if(tokenConsumed == tokenBucketSize) {
        //         tokenConsumed = tokenBucketSize - (request.getTime().minusSeconds(lastConsumedTokenTime) * refilRate);
        //     }
        //     return true;
        // } else {
        //     return false;
        // }

        return false;

    }
    
}