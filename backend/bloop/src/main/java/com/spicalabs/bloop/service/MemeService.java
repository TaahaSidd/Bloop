package com.spicalabs.bloop.service;

import com.spicalabs.bloop.dto.response.MemeResponse;
import com.spicalabs.bloop.entity.Meme;
import com.spicalabs.bloop.mapper.DtoMapper;
import com.spicalabs.bloop.repository.MemeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemeService {

    private final MemeRepo memeRepo;
    private final DtoMapper dtoMapper;

    //Get all Memes
    public List<MemeResponse> getAllMemes() {
        return memeRepo.findAll().stream()
                .map(dtoMapper::toMemeResponse)
                .collect(Collectors.toList());
    }

    //Get Memes by Id
    public MemeResponse getMemeById(UUID id) {
        Meme meme = memeRepo.findById(id).orElseThrow(() -> new RuntimeException("Meme not found with id: " + id));
        return dtoMapper.toMemeResponse(meme);
    }

    //Get Random Meme
    public MemeResponse getRandomMeme() {
        Meme meme = memeRepo.findRandomMeme()
                .orElseThrow(() -> new RuntimeException("Meme not found"));

        return dtoMapper.toMemeResponse(meme);
    }

    //Get memes by category
    public List<MemeResponse> getMemeByCategory(String slug) {
        List<Meme> memes = memeRepo.findByCategorySlug(slug);

        return memes.stream()
                .map(dtoMapper::toMemeResponse)
                .toList();
    }

    @Transactional
    public void incrementPlayCount(UUID id) {
        if (!memeRepo.existsById(id)) {
            throw new RuntimeException(("Meme not found with id : " + id));
        }

        memeRepo.updatePlayCount(id);
    }

    @Transactional
    public void incrementShareCount(UUID id) {
        if (!memeRepo.existsById(id)) {
            throw new RuntimeException(("Meme not found with id : " + id));
        }

        memeRepo.updateShareCount(id);
    }
}
