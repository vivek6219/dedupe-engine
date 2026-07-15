package dev.vivek6219.dedupeengine.service.chunking;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import dev.vivek6219.dedupeengine.service.hashing.FileHashingService;

@Service
public class FileChunkStorageService {
    private static final int BYTE_SIZE = 8192;

    private final FileHashingService fileHashingService;
    private final ChunkStorageService chunkStorageService;

    public FileChunkStorageService(FileHashingService fileHashingService, ChunkStorageService chunkStorageService) {
        this.fileHashingService = fileHashingService;
        this.chunkStorageService = chunkStorageService;
    }

    public FileChunkProcessingResult saveUniqueChunksAndHashFile(Path file) {
        List<String> chunkHashes = new ArrayList<>();
        MessageDigest fullFileDigest = fileHashingService.createSha256Digest();

        try (InputStream inputStream = Files.newInputStream(file)) {
            byte[] buffer = new byte[BYTE_SIZE];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                // 1. Update hash for entire file
                fullFileDigest.update(buffer, 0, bytesRead);
                // 2. Hash individual chunk
                String chunkHash = fileHashingService.hashChunk(buffer, bytesRead);
                // 3. Copy only valid bytes from buffer
                byte[] chunkBytes = Arrays.copyOf(buffer, bytesRead);
                //4. Save chunk only if it does not exist already
                chunkStorageService.saveChunkIfAbsent(chunkHash, chunkBytes);
                //5. track files chunk hashes
                chunkHashes.add(chunkHash);
            }

            String fileHash = fileHashingService.bytesToHex(fullFileDigest.digest());

            return new FileChunkProcessingResult(fileHash, chunkHashes);
        } catch (
                Exception e) {
            throw new RuntimeException("Failed to process and save file chunks for: " + file, e);
        }
    }


}
