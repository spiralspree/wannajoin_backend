package com.spiralspree.wannajoin;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataBaseManager {

    public DataSource getDataSource() {
        PGSimpleDataSource source = new PGSimpleDataSource();
        source.setServerNames(new String[] {System.getenv("WANNAJOIN_DB_SERVER")});
        source.setDatabaseName(System.getenv("WANNAJOIN_DB_NAME"));
        source.setPortNumbers(new int[] {Integer.parseInt(System.getenv("WANNAJOIN_DB_PORT"))});
        source.setUser(System.getenv("WANNAJOIN_DB_USER"));
        source.setPassword(System.getenv("WANNAJOIN_DB_PW"));
        source.setSslMode("require");
        try {
            System.out.println("Data source parameters set. Testing connection...");
            source.getConnection().close();
            System.out.println("Connection test was successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return source;
    }
}
