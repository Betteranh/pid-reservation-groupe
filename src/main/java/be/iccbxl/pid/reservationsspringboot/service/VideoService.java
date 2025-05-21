package be.iccbxl.pid.reservationsspringboot.service;

import be.iccbxl.pid.reservationsspringboot.model.Video;
import be.iccbxl.pid.reservationsspringboot.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public List<Video> findAll() {
        return StreamSupport.stream(videoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void save(Video video) {
        videoRepository.save(video);
    }

    public List<Video> findVideosByArtist(String artistName) {
        return videoRepository.findByArtistName(artistName);
    }
}
