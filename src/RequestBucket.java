import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RequestBucket {
    
    private Queue<Request> requestBucket;
    private int bucketSize;
    private int leakRate;
    
    public RequestBucket(int bucketSize, int leakRate) {
        this.requestBucket = new ConcurrentLinkedQueue<>();
        this.bucketSize = bucketSize;
        this.leakRate = leakRate;

        leakTheBucket();
    }


    public Queue<Request> getRequestBucket() {
        return requestBucket;
    }
    public void setRequestBucket(Queue<Request> requestBucket) {
        this.requestBucket = requestBucket;
    }

    public boolean addRequestInBucket(Request request) {
        if(requestBucket.size() < bucketSize) {
            requestBucket.offer(request);
            return true;
        } else {
            return false;
        }
    }

    public void leakTheBucket() {

        Thread daemonThread = new Thread( () -> 
        {
            while (true) {

                for(int i = 0; i < leakRate; i++) {
                    if(requestBucket.size() > 0) {
                        Request request = requestBucket.peek();
                        System.out.println("Request : " + request.getId() + " : " + request.getUser().getIp() + " : Leaked");
                        requestBucket.poll();
                    }
                }

                try {
                    Thread.sleep(1000);
                } catch(Exception ex) {
                    System.out.println("Trace logs : " + ex.getStackTrace());
                }
            }
        }
        );

        daemonThread.setDaemon(true);
        daemonThread.start();

    }



    public int getBucketSize() {
        return bucketSize;
    }
    public void setBucketSize(int bucketSize) {
        this.bucketSize = bucketSize;
    }

    
}
