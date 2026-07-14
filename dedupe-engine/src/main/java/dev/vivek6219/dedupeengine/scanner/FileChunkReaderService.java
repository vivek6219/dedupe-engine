package dev.vivek6219.dedupeengine.scanner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Service;

@Service
public class FileChunkReaderService {
    //            chunk data into bytes so it doesnt load all into memory and crash/slow the app down
    private static final int CHUNK_SIZE=8192; //8KB

    public void readChunks(Path filepath) throws IOException{
        byte[] buffer = new byte[CHUNK_SIZE];

        try(InputStream inputStream = Files.newInputStream(filepath)){
            int bytesread;
            while((bytesread = inputStream.read(buffer)) != -1){
                processChunk(buffer, bytesread);
            }
        }
    }

    private void processChunk(byte[] buffer, int bytesread){
        System.out.println("Processing chunk of size: " + bytesread + " bytes");

    }
}
