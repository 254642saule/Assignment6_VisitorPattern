package visitor.operation;

import visitor.model.Directory;
import visitor.model.DocumentFile;
import visitor.model.FileSystemElement;

public final class SizeCalculatorVisitor implements FileSystemVisitor {
    private long totalBytes;

    @Override
    public void visitFile(DocumentFile file) {
        totalBytes = Math.addExact(totalBytes, file.sizeBytes());
    }

    @Override
    public void visitDirectory(Directory directory) {
        for (FileSystemElement child : directory.children()) {
            child.accept(this);
        }
    }

    public long totalBytes() { return totalBytes; }
}
