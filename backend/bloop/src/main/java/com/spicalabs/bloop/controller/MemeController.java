package com.spicalabs.bloop.controller;


import com.spicalabs.bloop.dto.response.MemeResponse;
import com.spicalabs.bloop.service.MemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/memes")
@RequiredArgsConstructor
public class MemeController {

    private final MemeService memeService;

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
