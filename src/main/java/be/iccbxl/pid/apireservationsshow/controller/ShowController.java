package be.iccbxl.pid.apireservationsshow.controller;

import be.iccbxl.pid.apireservationsshow.model.Show;
import be.iccbxl.pid.apireservationsshow.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ShowController {
    @Autowired
    private ShowService showService;

    /**
     * Create - Add a new show
     *
     * @param show An object show
     * @return The show object saved
     */
    @PostMapping("/show")
    public Show createShow(@RequestBody Show show) {
        return showService.saveShow(show);
    }

    /**
     * Read - Get one show
     *
     * @param id The id of the show
     * @return An Show object full filled
     */
    @GetMapping("/show/{id}")
    public Show getshow(@PathVariable("id") final Long id) {
        Optional<Show> show = showService.getShow(id);
        if (show.isPresent()) {
            return show.get();
        } else {
            return null;
        }
    }

    /**
     * Read - Get all shows
     *
     * @return - An Iterable object of Show full filled
     */
    @GetMapping("/shows")
    public Iterable<Show> getshows() {
        return showService.getshows();
    }

    /**
     * Update - Update an existing show
     *
     * @param id   - The id of the show to update
     * @param show - The show object update
     * @return
     */
    @PutMapping("/show/{id}")
    public Show updateShow(@PathVariable("id") final Long id, @RequestBody Show show) {
        Optional<Show> e = showService.getShow(id);
        if (e.isPresent()) {
            Show currentShow = e.get();

            String slug = show.getSlug();
            if (slug != null) {
                currentShow.setSlug(slug);
            }
            String title = show.getTitle();
            if (title != null) {
                currentShow.setTitle(title);
            }
            String description = show.getDescription();
            if (description != null) {
                currentShow.setDescription(description);
            }
            String posterUrl = show.getPosterUrl();
            if (posterUrl != null) {
                currentShow.setPosterUrl(posterUrl);
                ;
            }
            showService.saveShow(currentShow);
            return currentShow;
        } else {
            return null;
        }
    }

    /**
     * Delete - Delete a show
     *
     * @param id - The id of the show to delete
     */
    @DeleteMapping("/show/{id}")
    public void deleteShow(@PathVariable("id") final Long id) {
        showService.deleteShow(id);
    }
}
