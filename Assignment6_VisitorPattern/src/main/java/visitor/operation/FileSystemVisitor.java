package visitor.operation;

import visitor.model.Directory;
import visitor.model.DocumentFile;

public interface FileSystemVisitor {
    void visitFile(DocumentFile file);
    void visitDirectory(Directory directory);
}
