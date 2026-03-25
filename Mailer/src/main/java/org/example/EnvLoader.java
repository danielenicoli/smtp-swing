package org.example;

import java.io.*;
import java.util.*;

public class EnvLoader {
    private static Map<String, String> env = new HashMap<>();

    public static void load(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty() || line.startsWith("#")) continue;
            String[] parts = line.split("=", 2);
            env.put(parts[0].trim(), parts[1].trim());
        }

        br.close();
    }

    public static String get(String key) {
        return env.get(key);
    }
}