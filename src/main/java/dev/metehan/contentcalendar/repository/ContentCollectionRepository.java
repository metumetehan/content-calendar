package dev.metehan.contentcalendar.repository;

import dev.metehan.contentcalendar.model.Content;
import dev.metehan.contentcalendar.model.Status;
import dev.metehan.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {

    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepository() {

    }

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findById(Integer id) {
        return contentList.stream().filter(content1 -> content1.id().equals(id)).findFirst();
    }

    @PostConstruct
    private void init() {
        Content content = new Content(
                1,
                "My First Blog Post",
                "My first description.",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                ""
        );
        contentList.add(content);

    }

    @PostMapping("")
    public void save(@RequestBody Content content) {
        contentList.removeIf(content1 -> content1.id().equals(content.id()));
        contentList.add(content);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        contentList.removeIf(content -> content.id().equals(id));
    }

    public boolean existsById(Integer id) {
        return contentList.stream().anyMatch(content -> content.id().equals(id));
    }
}
