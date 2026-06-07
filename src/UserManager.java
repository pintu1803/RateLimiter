import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> map;

    public UserManager() {
        this.map =  new ConcurrentHashMap<>();
    }

    public Map<String, User> getMap() {
        return map;
    }

    public void setMap(Map<String, User> map) {
        this.map = map;
    }

    public void addUser(User user) {
        map.put(user.getIp(), user);
    }

    public User getUser(String ip) {
        return map.get(ip);
    }

    
}
