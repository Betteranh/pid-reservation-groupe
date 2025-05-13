package be.iccbxl.pid.reservationsspringboot.mapper;


import be.iccbxl.pid.reservationsspringboot.dto.TagDTO;
import be.iccbxl.pid.reservationsspringboot.model.Tag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagMapper {

    public TagDTO toDTO(Tag tag) {
        return new TagDTO( tag.getId(), tag.getTag());
    }

    public List<TagDTO> toDTOList(List<Tag> tags) {
        return tags.stream()
                .map(this::toDTO)
                .collect( Collectors.toList() );
    }
}
