package dev.vivek6219.dedupeengine.service.restore;

import java.nio.file.Path;

import org.springframework.stereotype.Service;

@Service
public class RestoreService {
    public void restore(Path manifestPath, Path outputPath){
        System.out.println("Restore command received");
        System.out.println("Manifest Path: " + manifestPath);
        System.out.println("Output Path: " + outputPath);
        System.out.println("Restore functionality is not implemented just yet");
    }
}
