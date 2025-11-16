package com.example.ruichiji.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.nio.file.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DataService {
    private final Path dataDir;
    private final Path imagesDir;
    private final ObjectMapper mapper = new ObjectMapper();

    public DataService(Path workspaceRoot) {
        this.dataDir = workspaceRoot.resolve("assets").resolve("data");
        this.imagesDir = workspaceRoot.resolve("assets").resolve("images");
    }

    public List<ObjectNode> readList(String kind) throws IOException {
        Path p = dataDir.resolve(kind + ".json");
        if (!Files.exists(p)) return new ArrayList<>();
        try (InputStream in = Files.newInputStream(p)) {
            JsonNode root = mapper.readTree(in);
            if (root == null || !root.isArray()) return new ArrayList<>();
            ArrayNode arr = (ArrayNode) root;
            List<ObjectNode> list = new ArrayList<>();
            arr.forEach(n -> {
                if (n.isObject()) list.add((ObjectNode) n);
            });
            return list;
        }
    }

    public void writeList(String kind, List<ObjectNode> items) throws IOException {
        Path p = dataDir.resolve(kind + ".json");
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
        // backup
        if (Files.exists(p)) {
            String bakName = kind + ".json.bak-" + Instant.now().toString().replace(':','-');
            Files.copy(p, dataDir.resolve(bakName), StandardCopyOption.REPLACE_EXISTING);
        }
        ArrayNode arr = mapper.createArrayNode();
        items.forEach(arr::add);
        try (OutputStream out = Files.newOutputStream(p, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(out, arr);
        }
    }

    public Path importImage(File srcFile) throws IOException {
        if (!Files.exists(imagesDir)) {
            Files.createDirectories(imagesDir);
        }
        String baseName = srcFile.getName();
        Path dest = imagesDir.resolve(baseName);
        // if exists, append timestamp
        if (Files.exists(dest)) {
            String name = baseName;
            int dot = name.lastIndexOf('.');
            String nm = (dot >= 0) ? name.substring(0, dot) : name;
            String ext = (dot >= 0) ? name.substring(dot) : "";
            String newName = nm + "-" + Instant.now().toEpochMilli() + ext;
            dest = imagesDir.resolve(newName);
        }
        Files.copy(srcFile.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
        return dest;
    }
}
