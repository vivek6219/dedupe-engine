package dev.vivek6219.dedupeengine.commands;

import java.nio.file.Path;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import dev.vivek6219.dedupeengine.service.restore.RestoreService;
import java.util.Arrays;

@Component
public class RestoreCommand implements ApplicationRunner {
    private final RestoreService restoreService;

    public RestoreCommand(RestoreService restoreService){
        this.restoreService = restoreService;
    }

    @Override
    public void run(ApplicationArguments arguments){
        List<String> commandArgs = arguments.getNonOptionArgs();
        if(commandArgs.isEmpty())
                return;

        String commandName = commandArgs.get(0);
        if(!commandName.equalsIgnoreCase("restore")){
            return;
        }

        if(commandArgs.size() != 3){
            System.out.println("Usage: restore <manifest.path> <output-path>");
            return;
        }

        Path manifestpath = Path.of(commandArgs.get(1));
        Path outputPath = Path.of(commandArgs.get(2));

        restoreService.restore(manifestpath,outputPath);
    }
}
