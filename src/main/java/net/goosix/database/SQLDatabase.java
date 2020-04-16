package net.goosix.database;

import java.sql.PreparedStatement;

public interface SQLDatabase {

    void connect();

    void disconnect();

    PreparedStatement query(String sql, Object... objects);

}
