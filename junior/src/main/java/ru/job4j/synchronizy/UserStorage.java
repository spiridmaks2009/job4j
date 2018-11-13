package ru.job4j.synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * класс UserStorage
 *
 * @author maksimspiridonov
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private HashMap<Integer,User> userMap;

    public UserStorage() {
        userMap = new HashMap<>();
    }

    public synchronized boolean add(User user) {
        if (!userMap.containsKey(user.getId())) {
            userMap.put(user.getId(), user);
            return true;
        }
        return false;
    }

    public synchronized boolean update(User user) {
        if (userMap.containsKey(user.getId())) {
            userMap.replace(user.getId(), user);
            return true;
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        if (userMap.containsKey(user.getId())) {
            userMap.remove(user.getId());
            return true;
        }
        return false;
    }

    public void transfer(int fromId, int toId, int amount) {
        synchronized (this) {
            if (userMap.containsKey(fromId) && userMap.containsKey(toId)) {
                update(new User(toId, userMap.get(fromId).getAmount() - amount));
                update(new User(fromId, userMap.get(fromId).getAmount() + amount));
            }
        }
    }
}
