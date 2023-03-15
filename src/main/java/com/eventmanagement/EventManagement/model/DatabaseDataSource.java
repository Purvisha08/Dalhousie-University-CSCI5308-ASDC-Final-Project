package com.eventmanagement.EventManagement.model;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class DatabaseDataSource
{
    private static DatabaseDataSource instance = null;
    private static DriverManagerDataSource driverManagerDataSource;

    private DatabaseDataSource()
    {
        this.setDriverManagerDataSource();
    }

    public static DatabaseDataSource instance()
    {
        if(instance == null)
        {
            instance = new DatabaseDataSource();
        }
        return instance;
    }

    private void setDriverManagerDataSource()
    {
       driverManagerDataSource = new DriverManagerDataSource();
       String url = "jdbc:mysql://" + System.getenv("DB_HOST") + ":" + System.getenv("DB_PORT") + "/" + System.getenv("DB_NAME");
       driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
       driverManagerDataSource.setUrl(url);
       driverManagerDataSource.setUsername(System.getenv("DB_USER"));
       driverManagerDataSource.setPassword(System.getenv("DB_PASSWORD"));
    }

    public DriverManagerDataSource getDriverManagerDataSource()
    {
        return this.driverManagerDataSource;
    }

}
