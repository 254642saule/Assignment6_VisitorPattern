package visitor.model;

import java.util.Objects;
import visitor.operation.FileSystemVisitor;

public final class DocumentFile implements FileSystemElement {
    private final String name;
    private final long sizeBytes;

    public DocumentFile(String name, long sizeBytes) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("File name must not be blank");
        }
        if (sizeBytes < 0) {
            throw new IllegalArgumentException("File size must not be negative");
        }
        this.name = name;
        this.sizeBytes = sizeBytes;
    }

    public String name() { return name; }
    public long sizeBytes() { return sizeBytes; }

    @Override
    public void accept(FileSystemVisitor visitor) {
        Objects.requireNonNull(visitor, "visitor").visitFile(this);
    }
}
