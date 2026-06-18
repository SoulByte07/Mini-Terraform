package minitf.state;

import minitf.state.states.State;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StateManager {
    private final Path stateFilePath;
    private final Gson gson;

    public StateManager() {
        this("terraform-state.json");
    }

    public StateManager(String filePath) {
        this.stateFilePath = Path.of(filePath);
        this.gson = new GsonBuilder()
                .registerTypeAdapterFactory(new CloudResourceTypeAdapterFactory())
                .setPrettyPrinting()
                .create();
    }

    public void save(StateStore stateStore) throws IOException {
        String json = gson.toJson(stateStore);
        Files.writeString(stateFilePath, json);
    }

    public StateStore load() throws IOException {
        if (Files.notExists(stateFilePath)) {
            return new StateStore(); // Return empty store if file doesn't exist
        }
        try {
            String json = Files.readString(stateFilePath);
            return gson.fromJson(json, StateStore.class);
        } catch (JsonSyntaxException e) {
            throw new IOException("Failed to parse state file: " + e.getMessage(), e);
        }
    }
}
