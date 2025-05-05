package be.iccbxl.pid.reservationsspringboot.controller;

import be.iccbxl.pid.reservationsspringboot.dto.ShowDto;
import be.iccbxl.pid.reservationsspringboot.dto.ShowRequest;
import be.iccbxl.pid.reservationsspringboot.model.Show;
import be.iccbxl.pid.reservationsspringboot.model.Tag;
import be.iccbxl.pid.reservationsspringboot.service.ShowMapper;
import be.iccbxl.pid.reservationsspringboot.service.ShowService;
import be.iccbxl.pid.reservationsspringboot.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/shows")
public class ShowRestController {
    @Autowired
    private ShowService showService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ShowMapper showMapper;

    /**
     * GET /api/v1/shows?tag=musique
     */
    @GetMapping
    public List<ShowDto> list(@RequestParam Optional<String> tag) {
        List<Show> entities;
        if (tag.isPresent() && !tag.get().isBlank()) {
            // on cherche le Tag ⇒ ShowService.getByTag(Tag)
            Tag t = tagService.findByTag(tag.get())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag introuvable"));
            entities = showService.getByTag(t);
        } else {
            entities = showService.getAll();
        }
        return entities.stream()
                .map(showMapper::toDto)
                .toList();
    }

    /**
     * GET /api/v1/shows/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ShowDto> getOne(@PathVariable Long id) {
        Show e = showService.get(id.toString());
        if (e == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(showMapper.toDto(e));
    }

    /**
     * POST /api/v1/shows
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShowDto create(@Valid @RequestBody ShowRequest req) {
        Show s = showMapper.toEntity(req);
        showService.add(s);
        return showMapper.toDto(s);
    }

    /**
     * PUT /api/v1/shows/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ShowDto> update(@PathVariable Long id,
                                          @Valid @RequestBody ShowRequest req) {
        Show existing = showService.get(id.toString());
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        Show s = showMapper.toEntity(req);
        s.setId(id);                           // on remet l’ID pathVariable
        showService.update(id.toString(), s);

        return ResponseEntity.ok(showMapper.toDto(s));
    }

    /**
     * DELETE /api/v1/shows/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Show existing = showService.get(id.toString());
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        showService.delete(id.toString());
        return ResponseEntity.noContent().build();
    }
}
