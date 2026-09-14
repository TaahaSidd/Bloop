package com.spicalabs.bloop.config;

import com.spicalabs.bloop.entity.Category;
import com.spicalabs.bloop.entity.Meme;
import com.spicalabs.bloop.repository.CategoryRepo;
import com.spicalabs.bloop.repository.MemeRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepo categoryRepo;
    private final MemeRepo memeRepo;

    @Override
    public void run(String... args) {
        if (categoryRepo.count() > 0) {
            log.info("Data already seeded. Skipping initialization.");
            return;
        }

        log.info("Starting data seeding.");

        //Creating Category
        Category funny = categoryRepo.save(Category.builder()
                .name("Funny")
                .slug("funny")
                .isActive(true)
                .build());

        Category gaming = categoryRepo.save(Category.builder()
                .name("Gaming")
                .slug("gaming")
                .isActive(true)
                .build());

        Category reactions = categoryRepo.save(Category.builder()
                .name("Reactions")
                .slug("reactions")
                .isActive(true)
                .build());

        //Creating Memes.
        Meme meme1 = Meme.builder()
                .title("Bruh Sound Effect")
                .imageUrl("https://images.unsplash.com/photo-1543852786-1cf6624b9987")
                .soundUrl("https://www.myinstants.com/media/sounds/bruh.mp3")
                .category(funny)
                .playCount(0L)
                .shareCount(0L)
                .isActive(true)
                .build();

        Meme meme2 = Meme.builder()
                .title("Rage Quit")
                .imageUrl("https://images.unsplash.com/photo-1543852786-1cf6624b9987")
                .soundUrl("https://www.myinstants.com/media/sounds/bruh.mp3")
                .category(reactions)
                .playCount(0L)
                .shareCount(0L)
                .isActive(true)
                .build();


        memeRepo.save(meme1);
        memeRepo.save(meme2);

        log.info("Data seeding completed");
    }
}
