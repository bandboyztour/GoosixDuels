package net.goosix.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLDatabaseImpl implements SQLDatabase {

    private String url;
    private String userName;
    private String userPassword;
    private String databaseName;
    private Connection connection;

    public SQLDatabaseImpl(String url, String databaseName, String userName, String userPassword) {
        this.url = url;
        this.databaseName = databaseName;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    @Override
    public void connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + url + "/" + databaseName, userName, userPassword);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public PreparedStatement query(String sql, Object... objects) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if (objects != null) {
                int i = 1;
                for (Object obj : objects) {
                    preparedStatement.setObject(i, obj);
                    i++;
                }
            }
            return preparedStatement;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
