package dev.vivek6219.dedupeengine.model;

import java.time.Instant;

public record FileMetaDataModel(
    String Path,
    long sizeBytes,
    Instant lastModifiedTime
){}

