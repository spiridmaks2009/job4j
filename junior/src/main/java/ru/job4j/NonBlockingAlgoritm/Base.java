package ru.job4j.NonBlockingAlgoritm;

/**
 * Base
 *
 * @author maksimspiridonov
 */
public class Base {
    private int id;
    private int version;

    public Base(final int id) {
        this.id = id;
    }

    public void versionUpdate() {
        this.version++;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Base base = (Base) o;
        if (id != base.id) {
            return false;
        }
        return version == base.version;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + version;
        return result;
    }
}
