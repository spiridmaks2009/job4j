package ru.job4j.statistic;

/**
 * class Info - информация об изменении данных пользователей
 *
 * @author maksimspiridonov
 */
public class Info {

    /**
     * Количество добавленных
     */
    private int added;

    /**
     * Количество удаленных
     */
    private int deleted;

    /**
     * Количество измененных
     */
    private int changed;

    public Info(int added, int deleted, int changed) {
        this.added = added;
        this.deleted = deleted;
        this.changed = changed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Info info = (Info) o;

        if (added != info.added) {
            return false;
        }
        if (deleted != info.deleted) {
            return false;
        }
        return changed == info.changed;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + added;
        result = 37 * result + deleted;
        result = 37 * result + changed;
        return result;
    }
}
