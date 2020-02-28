# Ferris Magic Number

I wrote ferris-magic-number to find every `.class` file in a directory. While walking the entire directory tree,
the major version byte value is read from the `.class` file "magic number" <https://en.wikipedia.org/wiki/Java_class_file#Magic_Number.> 
and stored. A summary report is then generated which shows all the major Java versions used to compile the `.class` files and 
how many `.class` files were compiled with that major Java version. I detailed report is also generated which lists every
`.class` file under the major Java version used to compile it.

See <https://mjremijan.blogspot.com/2020/02/discovering-magic-version-number-of.html> for more details.

Use this project in combination with <https://github.com/mjremijan/ferris-war-exploder> to get a directory full of `.class` files.
