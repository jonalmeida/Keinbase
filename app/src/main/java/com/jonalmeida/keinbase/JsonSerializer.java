package com.jonalmeida.keinbase;

import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonSerializer {
    private static final String LOGTAG = JsonSerializer.class.getSimpleName();

    private final ObjectMapper mObjectMapper;

    private static JsonSerializer ourInstance = new JsonSerializer();

    public static JsonSerializer instance() {
        return ourInstance;
    }

    private JsonSerializer() {
        mObjectMapper = new ObjectMapper();
    }

    public JsonNode readTree(InputStream jsonString) {
        JsonNode node = null;
        try {
            node = mObjectMapper.readTree(jsonString);
        } catch (IOException e) {
            Log.e(LOGTAG, "Unable to serialize node tree. Corrupt data?");
            e.printStackTrace();
        }
        return node;
    }

    public List<User> serializeUsersFromResponse(JsonNode node) throws IOException {

        JsonNode statusNode = node.get("status");
        JsonNode name = statusNode.get("name");

        Log.d(LOGTAG, "WE GOT A FUCKING STATUS! " + name.textValue());

        JsonNode themNode = node.get("them");
        List<User> users = mObjectMapper.readValue(
                themNode.traverse(),
                mObjectMapper.getTypeFactory().constructCollectionType(List.class, User.class));


        return users;
    }
}
