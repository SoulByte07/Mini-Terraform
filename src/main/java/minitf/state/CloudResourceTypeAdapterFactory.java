package minitf.state;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import minitf.core.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CloudResourceTypeAdapterFactory implements TypeAdapterFactory {

    private static final Map<String, Class<? extends CloudResource>> TYPE_REGISTRY = new ConcurrentHashMap<>();
    private static final Gson PLAIN_GSON = new GsonBuilder().create();

    static {
        TYPE_REGISTRY.put("ec2", VirtualEC2.class);
        TYPE_REGISTRY.put("vpc", VirtualVPC.class);
        TYPE_REGISTRY.put("s3", VirtualS3Bucket.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!CloudResource.class.isAssignableFrom(type.getRawType())) {
            return null;
        }

        return (TypeAdapter<T>) new TypeAdapter<CloudResource>() {
            @Override
            public void write(JsonWriter out, CloudResource value) throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                JsonObject json = PLAIN_GSON.toJsonTree(value).getAsJsonObject();
                json.addProperty("type", resolveTypeKey(value.getClass()));
                PLAIN_GSON.toJson(json, out);
            }

            @Override
            public CloudResource read(JsonReader in) throws IOException {
                JsonObject json = gson.fromJson(in, JsonObject.class);
                JsonElement typeElement = json.get("type");
                if (typeElement == null) {
                    throw new JsonParseException("Missing 'type' discriminator in CloudResource JSON");
                }
                String typeKey = typeElement.getAsString();
                Class<? extends CloudResource> clazz = TYPE_REGISTRY.get(typeKey);
                if (clazz == null) {
                    throw new JsonParseException("Unknown CloudResource type: " + typeKey);
                }
                json.remove("type");
                return PLAIN_GSON.fromJson(json, clazz);
            }
        };
    }

    private String resolveTypeKey(Class<? extends CloudResource> clazz) {
        if (clazz == VirtualEC2.class) return "ec2";
        if (clazz == VirtualVPC.class) return "vpc";
        if (clazz == VirtualS3Bucket.class) return "s3";
        throw new IllegalArgumentException("Unknown CloudResource class: " + clazz);
    }
}
