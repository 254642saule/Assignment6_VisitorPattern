package visitor.operation;

import visitor.model.Directory;
import visitor.model.DocumentFile;
import visitor.model.FileSystemElement;
public final class FileCountVisitor implements FileSystemVisitor {
    private int count;

    @Override
    public void visitFile(DocumentFile file) { count++; }

    @Override
    public void visitDirectory(Directory directory) {
        for (FileSystemElement child : directory.children()) {
            child.accept(this);
        }
    }

    public int count() { return count; }
}
