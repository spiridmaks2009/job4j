package ru.job4j.parser;

import java.io.Closeable;
import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для работы с базой данных
 *
 * @author maksimspiridonov
 */
public class Database implements AutoCloseable {

    private Connection connection;

    private static final Logger LOG = LogManager.getLogger(Database.class.getName());


    public boolean init() {
        try (InputStream in = Database.class.getClassLoader().getResourceAsStream("parser.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("jdbc.driver"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("jdbc.url"),
                    config.getProperty("jdbc.username"),
                    config.getProperty("jdbc.password")
            );

        } catch (Exception e) {
            LOG.error("Exception",e);
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    public void createNewTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Vacancy ( id serial primary key, name varchar(200) unique," +
                    " text text, link text, \"create\" timestamp);");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS LastDate ( id serial primary key, date timestamp);");
        } catch (SQLException e) {
            LOG.error("Exception",e);
        }
    }

    public void addVacancyInDb(Vacancy newVacancy) {
        String query = "INSERT INTO Vacancy (name, text, link, \"create\") VALUES (?, ?, ?, ?)";
        try(PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, newVacancy.getName());
            st.setString(2, newVacancy.getText());
            st.setString(3, newVacancy.getLink());
            st.setDate(4, (java.sql.Date) newVacancy.getDate());
            int rows = st.executeUpdate();
            LOG.info("Добавлена вакансия: " + newVacancy.getName());
        } catch (SQLException e) {
            LOG.error("Exception",e);
        }
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
                LOG.error("Exception",e);
            }
        }
    }

    public void setLastDate(Date date) {
        String query = "INSERT INTO LastDate (date) VALUES (?)";
        try(PreparedStatement st = connection.prepareStatement(query)) {
            st.setDate(1, (java.sql.Date) date);
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Exception",e);
        }
    }

    public Date getLastDate() {
        Date date = new Date();
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM LastDate ORDER BY id DESC LIMIT 1")) {
            rs.next();
            date = rs.getDate(1);
        } catch (SQLException e) {
            LOG.error("Exception",e);
        }
        return date;
    }
}
