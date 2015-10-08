package com.jonalmeida.keinbase.pojos;

import java.util.HashMap;
import java.util.Map;

public class Pictures {
//    "pictures": {
//        "primary": {
//            "url": "https://s3.amazonaws.com/keybase_processed_uploads/8cd0e11f7239cb4c5bde24b2336a1805_200_200_square_200.jpeg",
//            "width": 200,
//            "height": 200,
//            "source": "github"
//        }
//    }
    private static final String KEY_URL = "url";
    private static final String KEY_WIDTH = "width";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_SOURCE = "source";

    private HashMap<String, Object> primary;
    private String primaryUrl;
    private int primaryWidth;
    private int primaryHeight;
    private String primarySource;

    public HashMap<String, Object> getPrimary() {
        return primary;
    }

    public void setPrimary(HashMap<String, Object> primary) {
        this.primary = primary;
        parsePrimaryPicture(primary);
    }

    private void parsePrimaryPicture(Map<String, Object> map) {
        if (map.containsKey(KEY_URL)) {
            primaryUrl = (String) map.get(KEY_URL);
        }
        if (map.containsKey(KEY_WIDTH)) {
            primaryWidth = (Integer) map.get(KEY_WIDTH);
        }
        if (map.containsKey(KEY_HEIGHT)) {
            primaryHeight = (Integer) map.get(KEY_HEIGHT);
        }
        if (map.containsKey(KEY_URL)) {
            primarySource = (String) map.get(KEY_SOURCE);
        }

    }

    public String getPrimaryUrl() {
        return primaryUrl != null ? primaryUrl : "";
    }

    public int getPrimaryWidth() {
        return primaryWidth;
    }

    public int getPrimaryHeight() {
        return primaryHeight;
    }

    public String getPrimarySource() {
        return primarySource != null ? primarySource : "";
    }
}
