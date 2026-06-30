package dev.vivek6219.dedupeengine.scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import dev.vivek6219.dedupeengine.model.FileMetaDataModel;
import org.springframework.stereotype.Service;

@Service
public class DirectoryScannerService {
    private final FileChunkReaderService fileChunkReaderService;

    public DirectoryScannerService(FileChunkReaderService fileChunkReaderService){
        this.fileChunkReaderService = fileChunkReaderService;
    }

    public List<FileMetaDataModel> scan(Path root) throws IOException{
        try(var paths = Files.walk(root)){
            return paths.filter(Files::isRegularFile)
                    .map(this::processFile)
                    .filter(Objects::nonNull)
                    .toList();
        }
    }

    public FileMetaDataModel processFile(Path path){
        try{
            fileChunkReaderService.readChunks(path);
            return new FileMetaDataModel(
                    path.toString(),
                    Files.size(path),
                    Files.getLastModifiedTime(path).toInstant()
            );
        }catch (IOException e){
            throw new RuntimeException("Failed to read file metadata for: " + path, e);
        }
    }
}
