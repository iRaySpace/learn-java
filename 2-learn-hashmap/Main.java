import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final Map<String, Object> map = new HashMap<>();
        map.put("name", "Ivan Ivanov Ivanovich");
        System.out.println(map);

        System.out.println("Getting the name: " + map.get("name"));        
        System.out.println("Contains key: " + map.containsKey("name"));
        System.out.println("Contains value: " + map.containsValue("Ivan Ivanov Ivanovich"));
        System.out.println("Is Empty: " + map.isEmpty());

        System.out.println("Removing the name");
        map.remove("name");

        System.out.println("Contains key: " + map.containsKey("name"));
        System.out.println("Contains value: " + map.containsValue("Ivan Ivanov Ivanovich"));
        System.out.println("Is Empty: " + map.isEmpty());

        map.put("secretKey", "$3krEtkeY");
        System.out.println(map);

        System.out.println("Clearing out: " + map);
        map.clear();

        System.out.println("Map: " + map);
    }
}
