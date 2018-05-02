package com.codecool.shop.dao.implementation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class QueryhandlerTest {

    static Queryhandler queryHandlerInstance;


    @Test
    public void testIsFileNotFoundReturnNull() {
        Queryhandler queryHandlerInstance = new Queryhandler() {

            @Override
            public String getConnectionConfigPath() {
                return "wrongpath/connection.properties";
            }

            @Override
            public void setConnectionConfigPath() {
            }
        };
        assertEquals(null, queryHandlerInstance.getConnection());
    }

    @Test
    public void testIsWrongConnectionPropertiesReturnNull() {
        Queryhandler queryHandlerInstance = new Queryhandler() {

            @Override
            public String getConnectionConfigPath() {
                return "test_wrong_connection.properties";
            }

            @Override
            public void setConnectionConfigPath() {
            }
        };
        assertEquals(null, queryHandlerInstance.getConnection());
    }

    @Test
    public void testIsRightConnectionPropertiesReturnConnection() {
        Queryhandler queryHandlerInstance = new Queryhandler() {

            @Override
            public String getConnectionConfigPath() {
                return "src/main/resources/connection.properties";
            }

            @Override
            public void setConnectionConfigPath() {
            }
        };
        assertTrue(queryHandlerInstance.getConnection() instanceof Connection);

    }
}