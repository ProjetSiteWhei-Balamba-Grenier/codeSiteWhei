package fr.hei.iti.sitewebwhei.dao.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {

    private static MysqlDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            // Declaration des parametres de connexion a la base de donnees
            dataSource = new MysqlDataSource();
            dataSource.setServerName("wyqk6x041tfxg39e.chr7pe7iynqr.eu-west-1.rds.amazonaws.com");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("v2phllc6cwf9seh0");
            dataSource.setUser("oo7vmh48olkx5qgz");
            dataSource.setPassword("c8th558rgirk3wep");
        }
        return dataSource;
    }
}
