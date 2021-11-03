package com.nology.marvelbackend.controller;

import com.nology.marvelbackend.JSONPaylod.JSONAPIRequest;
import com.nology.marvelbackend.Utility.MarvelCharacter;
import com.nology.marvelbackend.Utility.IMarvelAPI;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

@RestController()
@RequestMapping("/characters")
public class marvelController {

    @Autowired
    IMarvelAPI repo;

    @GetMapping("")
    public List<MarvelCharacter> getCharacters() throws SQLException, IOException, ParseException {
        return this.repo.findAll();
    }
}
