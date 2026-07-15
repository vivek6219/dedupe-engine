package dev.vivek6219.dedupeengine.scanner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import dev.vivek6219.dedupeengine.model.FileMetaDataModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import dev.vivek6219.dedupeengine.service.scanner.DirectoryScannerService;

import static org.junit.jupiter.api.Assertions.*;

public class DirectoryScannerServiceTest {

    private final DirectoryScannerService directoryScannerService = new DirectoryScannerService();

    @TempDir
    Path tempDir;

    @Test
    public void scanShouldReturnFilesFromDirectoryAndSubdirectories() throws Exception{
        Path rootFile = tempDir.resolve("root-file.txt");
        Files.writeString(rootFile, "hello");

        Path subDirectory = tempDir.resolve("documents");
        Files.createDirectory(subDirectory);

        Path nestedFile = tempDir.resolve("nested-file.txt");
        Files.writeString(nestedFile,"nested content");

        //init
        List<FileMetaDataModel> res = directoryScannerService.scan(tempDir);

        //assert
        assertEquals(2,res.size());

        List<String> paths = res.stream()
                .map(FileMetaDataModel::Path)
                .toList();

        assertTrue(paths.contains(rootFile.toString()));
        assertTrue(paths.contains(nestedFile.toString()));
    }
}
