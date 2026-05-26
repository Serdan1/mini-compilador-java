import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private Map<String, Symbol> table = new HashMap<>();

    public void declare(String name, String type) {
        table.put(name, new Symbol(name, type, false));
    }

    public boolean exists(String name) {
        return table.containsKey(name);
    }

    public void initialize(String name) {
        if (exists(name)) {
            table.get(name).initialized = true;
        }
    }

    public boolean isInitialized(String name) {
        return exists(name) && table.get(name).initialized;
    }
}