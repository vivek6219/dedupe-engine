package dev.vivek6219.dedupeengine.scanner;

import java.util.List;

public class FileChunkProcessingResult {

    private final String fileHash;
    private final List<String> chunkHashes;

    public FileChunkProcessingResult(String fileHash, List<String> chunkHashes) {
        this.fileHash = fileHash;
        this.chunkHashes = chunkHashes;
    }

    public String getFileHash() {
        return fileHash;
    }

    public List<String> getChunkHashes() {
        return chunkHashes;
    }
}