package dev.vivek6219.dedupeengine.scanner;

import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class FileHashing {

    public String encrypt(Path file) throws NoSuchAlgorithmException {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = messageDigest.digest(
                    file.
            );
        } catch(Exception e){
            return "Error encrypting file via SHA-256: " + file + " ,error thrown: "+ e.getMessage();
        }

    }
}
