package visitor.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import visitor.model.Directory;
import visitor.model.DocumentFile;
import visitor.model.FileSystemElement;

public final class SearchVisitor implements FileSystemVisitor {
    private final String searchTerm;
    private final List<String> matches = new ArrayList<>();
    private final List<String> directories = new ArrayList<>();

    public SearchVisitor(String searchTerm) {
        if (searchTerm == null || searchTerm.isBlank()) {
            throw new IllegalArgumentException("Search term must not be blank");
        }
        this.searchTerm = searchTerm.toLowerCase(Locale.ROOT);
    }

    @Override
    public void visitFile(DocumentFile file) {
        if (file.name().toLowerCase(Locale.ROOT).contains(searchTerm)) {
            matches.add(pathPrefix() + file.name());
        }
    }

    @Override
    public void visitDirectory(Directory directory) {
        directories.add(directory.name());
        try {
            for (FileSystemElement child : directory.children()) {
                child.accept(this);
            }
        } finally {
            directories.remove(directories.size() - 1);
        }
    }

    private String pathPrefix() { return String.join("/", directories) + "/"; }
    public List<String> matches() { return List.copyOf(matches); }
}
