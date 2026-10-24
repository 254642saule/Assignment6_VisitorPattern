# Assignment 6 — File System Visitor Pattern

Java 17+ console project for Software Design Patterns (SE-2501).

## Structure
- `src/main/java/visitor/model/` — Element interface and immutable File/Directory elements
- `src/main/java/visitor/operation/` — Visitor interface and three concrete visitors
- `src/main/java/visitor/demo/` — sample structure and console demo
- `src/test/java/visitor/` — dependency-free verification tests
- `docs/` — UML diagram source

## Run in IntelliJ IDEA
Open this folder as a Java project, mark `src/main/java` as Sources Root,
and run `visitor.demo.FileSystemDemo`. Run `visitor.VisitorTests` as a second Java application.
Set SDK to JDK 17 or later.

## Compile/run from a terminal (macOS/Linux)
```bash
mkdir -p out
javac -d out $(find src/main/java src/test/java -name '*.java')
java -cp out visitor.demo.FileSystemDemo
java -cp out visitor.VisitorTests
```
On Windows PowerShell:
```powershell
New-Item -ItemType Directory -Force out | Out-Null
$files = Get-ChildItem src -Recurse -Filter *.java | ForEach-Object FullName
javac -d out $files
java -cp out visitor.demo.FileSystemDemo
java -cp out visitor.VisitorTests
```

## Visitor mechanism
`root.accept(visitor)` invokes `visitDirectory(this)`; nested files invoke `visitFile(this)`.
No `instanceof` or switch-by-type is used. All visitors operate on the same tree.

## Concurrency
The model uses final fields and immutable snapshots (`List.copyOf`). Visitors have mutable
accumulators, so **never share the same visitor between threads**. Create one new visitor per
traversal/thread. This is demonstrated by the tests.

## Git submission
Create your own GitHub repository and make incremental commits (model, visitors,
demo, tests/documentation). This ZIP does not claim an existing Git history.
