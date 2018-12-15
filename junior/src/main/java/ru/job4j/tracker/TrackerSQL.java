package ru.job4j.tracker;

import java.io.Closeable;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 *
 * TrackerSQL
 *
 * @author maksimspiridonov
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    /**
     * Closes this resource, relinquishing any underlying resources.
     * This method is invoked automatically on objects managed by the
     * {@code try}-with-resources statement.
     *
     * <p>While this interface method is declared to throw {@code
     * Exception}, implementers are <em>strongly</em> encouraged to
     * declare concrete implementations of the {@code close} method to
     * throw more specific exceptions, or to throw no exception at all
     * if the close operation cannot fail.
     *
     * <p> Cases where the close operation may fail require careful
     * attention by implementers. It is strongly advised to relinquish
     * the underlying resources and to internally <em>mark</em> the
     * resource as closed, prior to throwing the exception. The {@code
     * close} method is unlikely to be invoked more than once and so
     * this ensures that the resources are released in a timely manner.
     * Furthermore it reduces problems that could arise when the resource
     * wraps, or is wrapped, by another resource.
     *
     * <p><em>Implementers of this interface are also strongly advised
     * to not have the {@code close} method throw {@link
     * InterruptedException}.</em>
     * <p>
     * This exception interacts with a thread's interrupted status,
     * and runtime misbehavior is likely to occur if an {@code
     * InterruptedException} is {@linkplain Throwable#addSuppressed
     * suppressed}.
     * <p>
     * More generally, if it would cause problems for an
     * exception to be suppressed, the {@code AutoCloseable.close}
     * method should not throw it.
     *
     * <p>Note that unlike the {@link Closeable#close close}
     * method of {@link Closeable}, this {@code close} method
     * is <em>not</em> required to be idempotent.  In other words,
     * calling this {@code close} method more than once may have some
     * visible side effect, unlike {@code Closeable.close} which is
     * required to have no effect if called more than once.
     * <p>
     * However, implementers of this interface are strongly encouraged
     * to make their {@code close} methods idempotent.
     *
     * @throws Exception if this resource cannot be closed
     */
    @Override
    public void close() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Item add(Item item) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("INSERT INTO items (name, \"desc\", \"create\") "
                    + "VALUES ('" + item.getName() + "', '" + item.getDesc() + "', CURRENT_TIMESTAMP)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void replace(String id, Item item) {
        try (Statement st = connection.createStatement();)
        {
        ResultSet rs = st.executeQuery("UPDATE items SET name = '" + item.getName() + "', \"desc\" = '"
                            + item.getDesc() + "', \"create\" = '" + item.getCreated() + "' WHERE id = " + Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try (Statement st = connection.createStatement();)
        {
           st.executeQuery("DELETE FROM items WHERE id = " + Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> listItems = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM items")) {
            while (rs.next()) {
                listItems.add(new Item(rs.getString("id"), rs.getString("name"),
                        rs.getString("desc"), rs.getTimestamp("create")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listItems;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> listItems = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM items WHERE name = " + key)) {
            while (rs.next()) {
                listItems.add(new Item(rs.getString("id"), rs.getString("name"),
                        rs.getString("desc"), rs.getTimestamp("create")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listItems;
    }

    @Override
    public List<Item> findById(String id) {
        List<Item> listItems = new ArrayList<>();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM items WHERE id = " + id)) {
            while (rs.next()) {
                listItems.add(new Item(rs.getString("id"), rs.getString("name"),
                        rs.getString("desc"), rs.getTimestamp("create")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listItems;
    }
}
