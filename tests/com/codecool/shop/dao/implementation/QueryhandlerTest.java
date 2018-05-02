package com.codecool.shop.dao.implementation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        };
        assertEquals(null, queryHandlerInstance.getConnection());
    }

    @Test
    public void testIsWrongConnectionPropertiesReturnNull() {
        Queryhandler queryHandlerInstance = new Queryhandler() {

            @Override
            public String getConnectionConfigPath() {
                return "test_resources/test_wrong_connection.properties";
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
        };
        assertTrue(queryHandlerInstance.getConnection() instanceof Connection);

    }

    @Test
    public void testIsExecuteSelectQueryWithParamsReturnValuesCorrect() {
        Queryhandler queryHandlerInstance = new Queryhandler() {

            @Override
            public String getConnectionConfigPath() {
                return "test_resources/connection.properties";
            }
        };
        String query = "SELECT * FROM suppliers WHERE id = ?";
        List<Object> parameters = new ArrayList();
        parameters.add(1);

        List<Map<String, Object>> referenceResult = new ArrayList<>();
        Map<String, Object> refMap = new HashMap<>();
        refMap.put("id", 1);
        refMap.put("name", "Állatkert");
        refMap.put("description", "Fővárosi Állatkert Kft.");
        referenceResult.add(refMap);
        assertEquals(referenceResult, queryHandlerInstance.executeSelectQuery(query, parameters));
    }

    @Test
    public void testIsExecuteSelectQueryReturnValuesCorrect() {
        Queryhandler queryHandlerInstance = new Queryhandler() {

            @Override
            public String getConnectionConfigPath() {
                return "test_resources/connection.properties";
            }
        };
        String query = "SELECT * FROM suppliers WHERE id = 1";

        List<Map<String, Object>> referenceResult = new ArrayList<>();
        Map<String, Object> refMap = new HashMap<>();
        refMap.put("id", 1);
        refMap.put("name", "Állatkert");
        refMap.put("description", "Fővárosi Állatkert Kft.");
        referenceResult.add(refMap);
        assertEquals(referenceResult, queryHandlerInstance.executeSelectQuery(query));
    }

}