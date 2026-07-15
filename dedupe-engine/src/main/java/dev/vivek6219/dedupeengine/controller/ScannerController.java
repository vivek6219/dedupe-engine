package dev.vivek6219.dedupeengine.controller;

import java.nio.file.Path;
import java.util.List;

import dev.vivek6219.dedupeengine.model.FileMetaDataModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import dev.vivek6219.dedupeengine.service.scanner.DirectoryScannerService;
import org.springframework.web.bind.annotation.RestController;

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