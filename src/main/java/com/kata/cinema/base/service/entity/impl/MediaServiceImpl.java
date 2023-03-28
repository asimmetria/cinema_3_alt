package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.entitys.Media;
import com.kata.cinema.base.repository.MediaRepository;
import com.kata.cinema.base.service.entity.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Override
    public Media getMediaById(Long id) {
        Media media = new Media();
        Optional<Media> optional = mediaRepository.findById(id);
        if(optional.isPresent()) {
            media = optional.get();
        }
        return media;
    }

}
