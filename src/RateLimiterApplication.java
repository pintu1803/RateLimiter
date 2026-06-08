import java.time.LocalTime;

public class RateLimiterApplication {
    public static void main(String[] args) throws Exception {
        
        UserManager userManager = new UserManager();
        RequestManager requestManager = new RequestManager();

        userManager.addUser(new User("Amit", 2));
        userManager.addUser(new User("Bavesh", 6));

        LocalTime time = LocalTime.of(22, 53, 01);

        Request r1 = new Request(userManager.getUser("Amit"), "r1", time);
        Request r2 = new Request(userManager.getUser("Amit"), "r2", time);
        Request r3 = new Request(userManager.getUser("Amit"), "r3", LocalTime.of(22, 53, 6));
        Request r4 = new Request(userManager.getUser("Amit"), "r4", LocalTime.of(22, 53, 7));

        //================================================================================================

        // requestManager.setRateLimtingAlgorithm(new FixedWindowCouterStrategy(5));

        // requestManager.handleRequest(r1);
        // requestManager.handleRequest(r2);
        // requestManager.handleRequest(r3);
        // requestManager.handleRequest(r4);


        //================================================================================================

        // requestManager.setRateLimtingAlgorithm(new SlidingWindowLogStrategy(5));

        // requestManager.handleRequest(r1);
        // requestManager.handleRequest(r2);
        // requestManager.handleRequest(r3);
        // requestManager.handleRequest(r4);

        //================================================================================================

        
        // requestManager.setRateLimtingAlgorithm(new TokenBucketStrategy(1,1));

        // requestManager.handleRequest(r1);
        // requestManager.handleRequest(r2);
        // requestManager.handleRequest(r3);
        // requestManager.handleRequest(r4);

        
        
        //================================================================================================

        
        requestManager.setRateLimtingAlgorithm(new LeakyBucketStrategy(3,1));

        requestManager.handleRequest(r1);//01
        requestManager.handleRequest(r2);//01
        try {
            Thread.sleep(5000);
        } catch(Exception ex) {
            System.out.println("Trace logs : " + ex.getStackTrace());
        }
        requestManager.handleRequest(r3);//06
        try {
            Thread.sleep(1000);
        } catch(Exception ex) {
            System.out.println("Trace logs : " + ex.getStackTrace());
        }
        requestManager.handleRequest(r4);//07
        




    }
}
