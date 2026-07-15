package dev.vivek6219.dedupeengine.service.scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import dev.vivek6219.dedupeengine.model.FileMetaDataModel;
import org.springframework.stereotype.Service;
import dev.vivek6219.dedupeengine.service.chunking.FileChunkProcessingResult;
import dev.vivek6219.dedupeengine.service.chunking.FileChunkStorageService;

@Service
public class DirectoryScannerService {
    private final FileChunkStorageService fileChunkStorageService;

    public DirectoryScannerService(FileChunkStorageService fileChunkStorageService) {
        this.fileChunkStorageService = fileChunkStorageService;
    }

    public List<FileMetaDataModel> scan(Path root) throws IOException {
        try (var paths = Files.walk(root)) {
            return paths.filter(Files::isRegularFile)
                        .filter(path -> !path.toString().toLowerCase().contains("gradle")) //filter out gradle files
                        .filter(path -> !path.toString().toLowerCase().endsWith(".gitignore")) //filter out gradle files
                        .map(this::processFile)
                        .filter(Objects::nonNull)
                        .toList();
        }
    }

    public FileMetaDataModel processFile(Path path) {
        try {
            FileChunkProcessingResult result = fileChunkStorageService.saveUniqueChunksAndHashFile(path);

            return new FileMetaDataModel(
                    path.toString(),
                    Files.size(path),
                    Files.getLastModifiedTime(path).toInstant(),
                    result.getFileHash()
            );
        } catch (
                IOException e) {
            throw new RuntimeException("Failed to read file metadata for: " + path, e);
        }
    }
}
