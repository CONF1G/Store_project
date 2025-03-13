package Repo;

import Domain.Product;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

public class SQLRepo extends RepoMemory implements AutoCloseable {

    public final String JDBC_URL = "jdbc:sqlite:Cumparaturi.db";

    private Connection connection;

    public SQLRepo() {
        openConnection();
        createTable();
        loadData();
    }

    private void loadData() {
        try (PreparedStatement stmt = connection.prepareStatement("select * from shop")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("id"), rs.getString("marca"), rs.getString("name"), rs.getInt("price"), rs.getString("quantity"));
                repo.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS shop(id int, marca varchar(400),name varchar(400), price int, quantity varchar(400))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void openConnection() {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(JDBC_URL);
        try {
            if (connection == null || connection.isClosed()) {
                connection = ds.getConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
