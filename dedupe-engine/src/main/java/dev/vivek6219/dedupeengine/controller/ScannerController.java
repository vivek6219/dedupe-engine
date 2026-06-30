package dev.vivek6219.dedupeengine.controller;

import java.nio.file.Path;
import java.util.List;

import dev.vivek6219.dedupeengine.model.FileMetaDataModel;
import dev.vivek6219.dedupeengine.scanner.DirectoryScannerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8080/scan?path=/home/snow/github/interview-year/dedupe-engine/src/test/test_folder

@RestController
public class ScannerController {
    private final DirectoryScannerService directoryScannerService;

    public ScannerController(DirectoryScannerService directoryScannerService){
        this.directoryScannerService=directoryScannerService;
    }

    @GetMapping("/scan")
    public List<FileMetaDataModel> scanDirectory(@RequestParam String path) throws Exception {
        return directoryScannerService.scan(Path.of(path));
    }
}