package ru.job4j.statistic;

import java.util.List;

/**
 * class Store - статистика по пользователям
 *
 * @author maksimspiridonov
 */
class Store {

    /**
     * Получить информацию об изменении данных пользователей
     *
     * @param previous
     * @param current
     * @return
     */
    Info diff(List<User> previous, List<User> current) {

        return new Info(countAdded(previous, current), countDeleted(previous, current), countChanged(previous, current));
    }

    /**
     * class User - данные одного пользователя
     */
    static class User {
        /**
         * id
         */
        int id;

        /**
         * имя
         */
        String name;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Посчитать добавленных
     *
     * @param previous
     * @param current
     * @return
     */
    int countAdded(List<User> previous, List<User> current) {
        int added = 0;
            for(User currUser : current) {
                boolean isAdded = true;
                for(User user: previous) {
                    if(user.getId() == currUser.getId()) {
                        isAdded = false;
                        break;
                    }
                }
                if (isAdded) {
                    added++;
                }
            }
        return added;
    }

    /**
     * Посчитать измененных
     *
     * @param previous
     * @param current
     * @return
     */
    int countChanged(List<User> previous, List<User> current) {
        int changed = 0;
        for(User currUser : current) {
            boolean isChanged = false;
            for(User user: previous) {
                if(user.getId() == currUser.getId() && !user.getName().equals(currUser.getName())) {
                    isChanged = true;
                    break;
                }
            }
            if(isChanged) {
                changed++;
            }
        }
        return changed;
    }

    /**
     * Посчитать удаленных
     *
     * @param previous
     * @param current
     * @return
     */
    int countDeleted(List<User> previous, List<User> current) {
        int deleted = 0;
        for(User user: previous) {
            boolean isDeleted = true;
            for(User currUser: current) {
                if(user.getId() == currUser.getId()) {
                    isDeleted = false;
                    break;
                }
            }
            if(isDeleted) {
                deleted++;
            }
        }
        return deleted;
    }
}
