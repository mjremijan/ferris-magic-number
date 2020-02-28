# Ferris Magic Number

I wrote ferris-magic-number because I needed to know what version of Java was used to compile all of the `.class` files in a directory.

Update `Main.java` to point to a directory on your file system that contains a bunch of `.class` files. When executed, the application will walk
the entire directory tree and determine the Java version used to compile every `.class` file it encounters. The application then
generates two reports.

1. A summary report shows a count of `.class` files for each Java version found.
1. A detailed report lists every `.class` file for each Java version found.

See <https://mjremijan.blogspot.com/2020/02/discovering-magic-version-number-of.html> for more details.

Use this project in combination with <https://github.com/mjremijan/ferris-war-exploder> to get a directory full of `.class` files.
