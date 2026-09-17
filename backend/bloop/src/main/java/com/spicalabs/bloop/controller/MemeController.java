package com.spicalabs.bloop.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spicalabs.bloop.dto.request.MemeRequest;
import com.spicalabs.bloop.dto.response.MemeResponse;
import com.spicalabs.bloop.service.MemeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/memes")
@RequiredArgsConstructor
public class MemeController {

    private final MemeService memeService;


    @PostMapping
    public ResponseEntity<MemeResponse> createMeme(@RequestBody MemeRequest req){
        return ResponseEntity.ok(memeService.createMeme(req));
    }

    // GET /api/v1/memes OR /api/v1/memes?category=slug
    @GetMapping
    public ResponseEntity<List<MemeResponse>> getMemes(
            @RequestParam(required = false, name = "category") String categorySlug
    ) {
        if (categorySlug != null) {
            return ResponseEntity.ok(memeService.getMemeByCategory(categorySlug));
        }
        return ResponseEntity.ok(memeService.getAllMemes());
    }

//    @GetMapping
//    public ResponseEntity<List<MemeResponse>> getAllMemes() {
//        return ResponseEntity.ok(memeService.getAllMemes());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<MemeResponse> getMemeById(@PathVariable UUID id) {
        return ResponseEntity.ok(memeService.getMemeById(id));
    }

    @GetMapping("/random")
    public ResponseEntity<MemeResponse> getRandomMeme() {
        return ResponseEntity.ok(memeService.getRandomMeme());
    }

//    @GetMapping
//    public ResponseEntity<List<MemeResponse>> getMemesByCategory(@RequestParam(required = false) String slug) {
//        return ResponseEntity.ok(memeService.getMemeByCategory(slug));
//    }

    @PostMapping("/{id}/play")
    public ResponseEntity<Void> incrementPlayCount(@PathVariable UUID id) {
        memeService.incrementPlayCount(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/share")
    public ResponseEntity<Void> incrementShareCount(@PathVariable UUID id) {
        memeService.incrementShareCount(id);
        return ResponseEntity.ok().build();
    }

}
