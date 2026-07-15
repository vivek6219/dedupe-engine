package dev.vivek6219.dedupeengine.service.hashing;

import java.security.MessageDigest;

import org.springframework.stereotype.Service;

@Service
public class FileHashingService {
    public MessageDigest createSha256Digest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (
                Exception e) {
            throw new IllegalStateException("SHA-256 algo is not available", e);
        }
    }

    public String hashChunk(byte[] buffer, int bytesRead) {
        MessageDigest messageDigest = createSha256Digest();
        messageDigest.update(buffer, 0, bytesRead);

        return bytesToHex(messageDigest.digest());
    }

    public String bytesToHex(byte[] encodedBytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedBytes) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }
}
