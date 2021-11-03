package com.nology.marvelbackend.Utility;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMarvelAPI extends JpaRepository<MarvelCharacter,Long> {
    Optional<MarvelCharacter> findById(Long id);
}
