package ru.job4j.xml;

import java.io.Closeable;
import java.sql.*;
import java.util.*;

/**
 * StoreSQL
 *
 * @author maksimspiridonov
 */
public class StoreSQL implements AutoCloseable{

    private Connection connection;

    /**
     * Connect to a sample database
     *
     * @param fileName the database file name
     */
    public void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:" + fileName;

        try  {
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createNewTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS entry");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS entry ( field integer)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generate(int n) {
        try (Statement st = connection.createStatement()) {
                for (int i = 0; i < n; i++) {
                    st.executeUpdate(String.format("INSERT INTO entry VALUES (%s)",i));
                }
                connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void selectAll() {
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM entry");
            while (rs.next()) {
                System.out.println(rs.getInt("field"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Entry> selectData() {
        List<Entry> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM entry");
            while (rs.next()) {
                Entry entry = new Entry(rs.getInt("field"));
                result.add(entry);
            }
            rs.close();
        } catch (SQLException e) {

        }
        return result;
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
}
