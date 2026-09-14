package com.spicalabs.bloop.repository;

import com.spicalabs.bloop.entity.Meme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemeRepo extends JpaRepository<Meme, UUID> {

    @Query(value = "Select * from Meme order by random() limit 1", nativeQuery = true)
    Optional<Meme> findRandomMeme();

    List<Meme> findByCategorySlug(String slug);

    @Modifying
    @Query("update Meme m set m.playCount = m.playCount + 1 where m.id = :id")
    void updatePlayCount(@Param("id") UUID id);

    @Modifying
    @Query("update Meme m set m.shareCount = m.shareCount + 1 where m.id  = :id")
    void updateShareCount(@Param("id") UUID id);
}
