package com.epam.chuikov.db;

import java.sql.Connection;

public class ConnectionPool {
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static void set(Connection connection){
        threadLocal.set(connection);
    }

    public static Connection get(){
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
