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

        // requestManager.setRateLimtingAlgorithm(new FixedWindowCouterStrategy(5));

        // requestManager.handleRequest(r1);
        // requestManager.handleRequest(r2);
        // requestManager.handleRequest(r3);
        // requestManager.handleRequest(r4);


        // Thread t1 = new Thread(() -> { requestManager.handleRequest(r1); } );
        // Thread t2 = new Thread(() -> { requestManager.handleRequest(r2); } );
        // Thread t3 = new Thread(() -> { requestManager.handleRequest(r3); } );

        // t1.start();
        // t2.start();
        // t3.start();

        //================================================================================================

        requestManager.setRateLimtingAlgorithm(new SlidingWindowLogStrategy(5));

        requestManager.handleRequest(r1);
        requestManager.handleRequest(r2);
        requestManager.handleRequest(r3);
        requestManager.handleRequest(r4);

        //================================================================================================


        


    }
}
