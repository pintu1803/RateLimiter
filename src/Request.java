import java.time.LocalDate;
import java.time.LocalTime;

public class Request {
    
    private User user;
    private String id;
    private LocalTime time;

    public Request(User user, String id, LocalTime time) {
        this.user = user;
        this.id = id;
        this.time = time;
    }
    

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    

}
