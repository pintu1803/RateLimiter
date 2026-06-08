import java.time.LocalTime;

public class TokenBucket {
    
    private int tokenAvailable;
    private int tokenBucketSize;
    private LocalTime lastTokenRefilTime;

    public TokenBucket(int tokenAvailable, int tokenBucketSize) {
        this.tokenAvailable = tokenAvailable;
        this.tokenBucketSize = tokenBucketSize;
        this.lastTokenRefilTime = null;
    }
    public int getTokenAvailable() {
        return tokenAvailable;
    }
    public void decreaseTokenAvailable() {
        this.tokenAvailable -= 1;
    }

    public void addTokenAvailable(int tokenCount) {
        this.tokenAvailable += tokenCount;
        this.tokenAvailable = tokenAvailable > tokenBucketSize ? tokenBucketSize : tokenAvailable;
    }

    public int getTokenBucketSize() {
        return tokenBucketSize;
    }
    public void setTokenBucketSize(int tokenBucketSize) {
        this.tokenBucketSize = tokenBucketSize;
    }


    public LocalTime getLastTokenRefilTime() {
        return lastTokenRefilTime;
    }
    public void setLastTokenRefilTime(LocalTime lastTokenRefilTime) {
        this.lastTokenRefilTime = lastTokenRefilTime;
    }



    
}
