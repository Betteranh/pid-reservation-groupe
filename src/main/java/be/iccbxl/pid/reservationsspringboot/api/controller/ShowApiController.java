package be.iccbxl.pid.reservationsspringboot.api.controller;

import be.iccbxl.pid.reservationsspringboot.dto.ShowDTO;
import be.iccbxl.pid.reservationsspringboot.mapper.ShowMapper;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.model.Tag;
import be.iccbxl.pid.reservationsspringboot.service.ShowService;
import be.iccbxl.pid.reservationsspringboot.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

@RestController
@RequestMapping("/api/shows")
//@CrossOrigin(origins = "http://localhost:8081")

public class ShowApiController {

    @Autowired
    private ShowService showService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ShowMapper mapper;


    @GetMapping
    public List<ShowDTO> getAllShows() {

        return showService.getAll().stream()
                .map(mapper::mapShowToShowDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ShowDTO> getShowById(@PathVariable String id) {
        Show show = showService.get(id);
        if (show == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.mapShowToShowDTO(show));
    }




    @GetMapping(params = "tag")
    public ResponseEntity<?> getShowsByTag(@RequestParam String tag) {
        var tagOpt = tagService.findByTag(tag);
        if (tagOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Tag introuvable");
        }
        var shows = showService.getByTag(tagOpt.get()).stream()
                .map(mapper::mapShowToShowDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(shows);
    }
    @GetMapping ("/exclude")
    public ResponseEntity<?> getShowsWithoutTag(@RequestParam String tag) {
        var tagOpt = tagService.findByTag(tag);
        if (tagOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Tag introuvable");
        }
        var shows = showService.getWithoutTag(tagOpt.get()).stream()
                .map(mapper::mapShowToShowDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(shows);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/tags")
    public ResponseEntity<?> addTagToShow(@PathVariable Long id, @RequestParam Long tagId) {
        Show show = showService.get(String.valueOf(id));
        if (show == null) {
            return ResponseEntity.notFound().build();
        }

        Optional<Tag> tag = tagService.find(tagId);
        if (tag.isEmpty()) {
            return ResponseEntity.badRequest().body("Tag introuvable");
        }

        show.getTags().add(tag.get());
        showService.add(show); // ou showService.save(show)
        return ResponseEntity.ok().build();
    }
}