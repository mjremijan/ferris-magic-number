package org.ferris.magic.number;

import java.nio.file.Paths;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Main {


    public static void main(String[] args) throws Exception
    {
        System.out.printf("=== Welcome to Ferris Magic Number ===%n");

        MagicNumbers.load(
            Paths.get("./src/test/jars")
        ).print();

        System.out.printf("%n=== DONE ===%n");
    }
}
