package org.thoth.magic.number;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class MagicNumbers extends SimpleFileVisitor<Path> {

    protected Map<Major, List<MagicNumber>> breakdown;

    private MagicNumbers() {
        breakdown = new HashMap<>();
    }

    public static MagicNumbers load(Path pathOfDirectoryContainingClassFiles) throws IOException {
        MagicNumbers magicNumber = new MagicNumbers();
        Files.walkFileTree(pathOfDirectoryContainingClassFiles, magicNumber);
        return magicNumber;
    }

    protected void add(Path pathToClassFile) throws IOException {
        MagicNumber magicNumber = new MagicNumber(pathToClassFile);
        add(magicNumber);
    }

    protected void add(MagicNumber magicNumber) {
        Major key = magicNumber.getMajor();
        if (!breakdown.containsKey(key)) {
            breakdown.put(key, new LinkedList<MagicNumber>());
        }
        breakdown.get(key).add(magicNumber);
    }

    void print() {

        breakdown.keySet()
            .stream()
            .sorted(Comparator.comparing(Major::getByte).reversed())
            .forEach(k -> {
                    System.out.printf("%n%n+++ %d +++%n%n", k.getByte());
                    System.out.printf("    Count: %d%n%n", breakdown.get(k).size());
                    breakdown.get(k).forEach(m -> System.out.print(m.toString()));
                }
            );

        System.out.printf("%n%n+++ SUMMARY +++%n%n");

        System.out.printf("    Different major version count: %d%n", breakdown.keySet().size());

        breakdown.keySet()
            .stream()
            .sorted(Comparator.comparing(Major::getByte).reversed())
            .forEach(k -> {
                    System.out.printf("    %s class count: %d%n", k.toString(), breakdown.get(k).size());
                }
            );
    }


    //
    //
    // SimpleFileVisitor
    //
    //
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
        if (attr.isRegularFile() && file.getFileName().toString().endsWith(".class")) {
            add(file);
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }
}
