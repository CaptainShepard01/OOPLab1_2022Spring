package ua.university.config;

import liquibase.Liquibase;
import liquibase.change.Change;
import liquibase.change.core.CreateTableChange;
import liquibase.changelog.ChangeSet;
import liquibase.changelog.RanChangeSet;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DataSourceConfig {
    //  Database credentials
    static final String DB_URL = "jdbc:postgresql://localhost:5432/FacultyOOPLabs";
    static final String USER = "postgres";
    static final String PASS = "password";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return null;
        }

        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);

//        try {
//            this.liquibase(connection);
//        } catch (LiquibaseException e) {
//            e.printStackTrace();
//        }

        return connection;
    }

    public void liquibase(Connection connection) throws LiquibaseException {
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase liquibase = new Liquibase("E:\\Projects\\University\\OOP\\OOPLabs2022Spring\\src\\main\\resources\\liquibaseSettings\\dev\\db\\changelog\\changelog-master.xml", new FileSystemResourceAccessor(), database);
        List<ChangeSet> changeSets = liquibase.listUnrunChangeSets(null);
        liquibase.update("");
        for (RanChangeSet ranChangeSet : database.getRanChangeSetList()) {
            ChangeSet found = null;
            for (ChangeSet changeSet : changeSets) {
                if (ranChangeSet.isSameAs(changeSet)) {
                    found = changeSet;
                    break;
                }
            }

            if (found == null) {
                // ran previously
                continue;
            }
            System.out.println(ranChangeSet.getChangeLog());
            for (Change change : found.getChanges()) {
                if (change instanceof CreateTableChange) {
                    System.out.println(((CreateTableChange) change).getTableName());
                }
                // handle others
            }
        }
    }
}