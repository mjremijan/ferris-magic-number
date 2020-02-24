package org.thoth.magic.number;

import java.util.Objects;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Major {
    protected Byte value;
    protected String description;

    public Byte getByte() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("'%s = %d'", getDescription(), getByte());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Major other = (Major) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    public Major(Byte value) {
        if (value < 45) {
            throw new IllegalArgumentException(
                String.format("The value \"%d\" is too small to be a valid Java major number byte value.%n", value)
            );
        }

        this.value = value;
        this.description = null;

        switch (this.value) {
            case 45: description = "JDK 1.1"; return;
            case 46: description = "JDK 1.2"; return;
            case 47: description = "JDK 1.3"; return;
            case 48: description = "JDK 1.4"; return;
        }

        switch (this.value) {
            case 49: description = "Java SE 5.0"; return;
            case 50: description = "Java SE 6.0"; return;
        }

        // Java SE 14 = 58 (0x3A hex),
        // Java SE 13 = 57 (0x39 hex),
        // Java SE 12 = 56 (0x38 hex),
        // Java SE 11 = 55 (0x37 hex),
        // Java SE 10 = 54 (0x36 hex),[3]
        // Java SE 9 = 53 (0x35 hex),[4]
        // Java SE 8 = 52 (0x34 hex),
        // Java SE 7 = 51 (0x33 hex),
        if (this.value >= 51) {
            description = "Java SE " + (this.value - 44);
        }
    }
}
