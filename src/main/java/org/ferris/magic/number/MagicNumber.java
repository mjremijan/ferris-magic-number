package org.ferris.magic.number;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class MagicNumber {

    protected Path classFile;
    protected byte[] CAFEBABEs, minors, majors;
    protected Major major;

    public MagicNumber (Path classFile) throws IOException {
        try (
            InputStream is = new FileInputStream(classFile.toFile());
        ) {
            this.classFile = classFile;

            // Magic number info:
            // https://en.wikipedia.org/wiki/Java_class_file#Magic_Number
            CAFEBABEs = new byte[4];
            is.read(CAFEBABEs);

            minors = new byte[2];
            is.read(minors);

            majors = new byte[2];
            is.read(majors);

            major = new Major(majors[1]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sp = new StringBuilder();
        sp.append(String.format("    CLASS: %s%n", classFile.toString()));
        sp.append(String.format("        minor[] = %s%n", Arrays.toString(minors)));
        sp.append(String.format("        major[] = %s%n", Arrays.toString(majors)));
        sp.append(String.format("        major = %s%n", major.toString()));
        return sp.toString();
    }

    public Major getMajor() {
        return major;
    }
}
