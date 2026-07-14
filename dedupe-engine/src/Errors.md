# For every file:

1. Open the file once.
2. Read it in 8192-byte chunks.
3. Update the full-file SHA-256 hash.
4. Hash each individual chunk.
5. Save the chunk only if it does not already exist.
6. Return metadata about the file.


# Errors and problems I debugged:
## Error:
    ArrayIndexOutOfBoundsException: Range[0, 0 + -1]
## Cause:
    The Cause of this error was I passed bytesRead as == -1 instead of != -1 into the method 
    FileChunkStorageService.saveUniqueChunksAndHashFile() in the while loop. 
## Fix:
    Changed from == -1 to != -1
## Lesson:
    -1 represents the end of the file since it is not a valid byte count, so once it reaches the 
    end it exits the loop
