package visitor.demo;

import java.util.List;
import visitor.model.Directory;
import visitor.model.DocumentFile;
import visitor.operation.FileCountVisitor;
import visitor.operation.SearchVisitor;
import visitor.operation.SizeCalculatorVisitor;

public final class FileSystemDemo {
    private FileSystemDemo() { }

    public static Directory sampleTree() {
        Directory reports = new Directory("reports", List.of(
                new DocumentFile("report-2026.pdf", 1_200),
                new DocumentFile("notes.txt", 300)));
        Directory images = new Directory("images", List.of(
                new DocumentFile("zoo-report.png", 2_500)));
        return new Directory("project", List.of(
                new DocumentFile("README.md", 100), reports, images));
    }

    public static void main(String[] args) {
        Directory root = sampleTree();
        SizeCalculatorVisitor sizes = new SizeCalculatorVisitor();
        root.accept(sizes);
        System.out.println("Total size: " + sizes.totalBytes() + " bytes");

        SearchVisitor search = new SearchVisitor("report");
        root.accept(search);
        System.out.println("Files containing 'report':");
        search.matches().forEach(path -> System.out.println("  " + path));

        FileCountVisitor counter = new FileCountVisitor();
        root.accept(counter);
        System.out.println("File count: " + counter.count());
    }
}
