package com.nology.marvelbackend.JSONPaylod;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

import com.nology.marvelbackend.Utility.thumbnail;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONAPIRequest {
    HttpURLConnection conn;
    thumbnail thumbnail;
    URL url = new URL("https://gateway.marvel.com:443/v1/public/characters?limit=100&ts=1&apikey=d70ac2cb5c5aa5b6d5d08b219f957254&hash=7e53eab8e67483af6e918dce9d140929");

    public static Connection ConnectToDB() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        String mysql = "jdbc:mysql://localhost:3306";
        Connection mysqlCon = DriverManager.getConnection(mysql, "root", "password");
        System.out.println("Connection good");
        return mysqlCon;
    }

    public JSONAPIRequest() throws MalformedURLException {
    }

    public void getAPI() throws IOException, ParseException, SQLException {
        HashMap<Object, Object> characters = new HashMap<>();
        Connection mysqlConnection = ConnectToDB();
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responsecode = conn.getResponseCode();
        //Code will only run depending on the response code
        if (responsecode != 200){
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {
            String inline = "";
            Scanner scanner = new Scanner(url.openStream());
            //Write all the JSON data into a string using a scanner
            while (scanner.hasNext()) {
                inline += scanner.nextLine();
            }
            scanner.close();
            //Converts the String into readable JSON data that I require
            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(inline);
            JSONObject obj = (JSONObject) data_obj.get("data");
            JSONArray jsonArray = (JSONArray) obj.get("results");
            JSONObject test = (JSONObject) jsonArray.get(0);
            //JSON TO mySQL database
            PreparedStatement prepState = mysqlConnection.prepareStatement("INSERT INTO marvelAPI.marvel_character(id, name, description) VALUES (?,?,?)");
            for (Object o : jsonArray) {
                JSONObject noMorePain = (JSONObject) o;
//                characters.put(noMorePain.get("id"), noMorePain.get("name"));
                prepState.setLong(1, (Long) noMorePain.get("id"));
                prepState.setString(2, (String) noMorePain.get("name"));
                prepState.setString(3, (String) noMorePain.get("description"));
                prepState.executeUpdate();
                //TODO Ask Ollie on how to get a JSON to pass into the table

            }
        }
    }
}
