# This is a dedupe search engine application
- What this means is that we will not search for duplicate files and directories, once we come across a seen file or
 directory we will overlook it making it more efficient.
- I have implemented hashing (SHA-256) and look for duplicate folders and files based on the hash it is stored under.
  By doing so, we are able to come across two files that are 99% the same while taking into account and storing only the 
  1% that is different.
 
 Each file should be opened only once. During that one read:

1. calculate the full-file SHA-256 hash
2. calculate each chunk's SHA-256 hash
3. save each unique chunk
4. return the full-file hash for file metadata

## How it works:
    ./controller/ScannerController -> is called to scan directory entered
    ./scanner/DirectoryScannerService -> is then called by controller which finds the files
    ./scanner/FileChunkStorageService -> is called and each file once, and then coordinates chunking
    ./scanner/FileHashingService -> Responsible for creating SHA-256 hashes which is used in FileChunkStorageService
    ./scanner/ChunkStorageService -> Saves chunk bytes to disk by hash
    ./model/FileMetaDataModel -> Represents information model for each file
    ./scanner/FileChunkProcessingResult -> Represents results of processing one file chunk