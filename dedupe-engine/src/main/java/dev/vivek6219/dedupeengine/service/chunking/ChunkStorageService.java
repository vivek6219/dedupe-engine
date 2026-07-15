package dev.vivek6219.dedupeengine.service.chunking;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChunkStorageService {
    private final Path chunkStorageDirectory;

    public ChunkStorageService(@Value("${dedupe.storage.chunk-directory:./data/chunks}") String chunkStorageDirectory){
        this.chunkStorageDirectory = Path.of(chunkStorageDirectory);
    }

    public void saveChunkIfAbsent(String chunkHash, byte[] chunkBytes) throws IOException{
        System.out.println(chunkStorageDirectory.toString());

        Path chunkPath = resolveChunkPath(chunkHash);
        try{
            Files.createDirectories(chunkPath.getParent());

//                if(Files.notExists(chunkPath)) {
                 Files.write(
                    chunkPath,
                    chunkBytes,
                    StandardOpenOption.CREATE_NEW
                );
//                }
            }catch (FileAlreadyExistsException e){
        }catch (IOException e){
            throw new RuntimeException("Failed to save chunk: " + chunkHash, e);
        }
    }

    private Path resolveChunkPath(String chunkHash){
        String prefix = chunkHash.substring(0,2);

        return chunkStorageDirectory
                .resolve(prefix)
                .resolve(chunkHash =".chunk");
    }
}
