package visitor.model;

import visitor.operation.FileSystemVisitor;

public interface FileSystemElement {
    void accept(FileSystemVisitor visitor);
}
