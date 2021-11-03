package com.nology.marvelbackend;

import com.nology.marvelbackend.JSONPaylod.JSONAPIRequest;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

public class JSONAPIRequestTest {
    JSONAPIRequest apiRequest = new JSONAPIRequest();

    public JSONAPIRequestTest() throws IOException, ParseException {
    }


    @Test
    @DisplayName("It should give a Response code ")
    public void printNumber() throws IOException, ParseException, SQLException {
        apiRequest.getAPI();
    }
}
