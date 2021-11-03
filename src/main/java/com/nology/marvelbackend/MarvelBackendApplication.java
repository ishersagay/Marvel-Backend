package com.nology.marvelbackend;

import com.nology.marvelbackend.JSONPaylod.JSONAPIRequest;
import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

@SpringBootApplication
public class MarvelBackendApplication {

    public static void main(String[] args) throws IOException, SQLException, ParseException {
        JSONAPIRequest request = new JSONAPIRequest();
        request.getAPI();
        SpringApplication.run(MarvelBackendApplication.class, args);
    }

}
