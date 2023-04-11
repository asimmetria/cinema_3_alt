package com.kata.cinema.base.service.entity.impl;

import com.kata.cinema.base.models.dto.response.MediaTitleResponseDto;
import com.kata.cinema.base.models.entitys.Categories;
import com.kata.cinema.base.models.entitys.Media;
import com.kata.cinema.base.models.enums.MediaStatus;
import com.kata.cinema.base.repository.CategoryRepository;
import com.kata.cinema.base.repository.MediaRepository;
import com.kata.cinema.base.service.entity.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void createMedia(Media media) {

        media.setEnable(false);
        media.setStatus(MediaStatus.EDITED);
        mediaRepository.save(media);

    }

    @Override
    public void updateMedia(Long id, Media media) {

        Optional<Media> optionalMedia = mediaRepository.findByIdAndStatusEquals(id, MediaStatus.EDITED);
        if (optionalMedia.isPresent()) {
            media = optionalMedia.get();
            media.setTitle(media.getTitle());
            media.setHtmlBody(media.getHtmlBody());
            Categories categories = categoryRepository.getReferenceById(id);
            media.setCategory(categories);
            mediaRepository.save(media);
        }
    }

    @Override
    public void changeEnableStatus(Long id) {

        Media media = mediaRepository.getReferenceById(id);
        media.setStatus(MediaStatus.WAIT_VERIFIED);
        mediaRepository.save(media);
    }

    @Override
    public void changeStatusWaitVerify(Long id) {

        Media media = mediaRepository.getReferenceById(id);
        media.setStatus(MediaStatus.WAIT_VERIFIED);
        mediaRepository.save(media);
    }

    @Override
    public void delete(Long id) {

        mediaRepository.deleteById(id);
    }

    @Override
    public void publish(Long id) {

        Media media = mediaRepository.getReferenceById(id);
        mediaRepository.findByIdAndStatusEquals(id, MediaStatus.EDITED);
        media.setStatus(MediaStatus.WAIT_VERIFIED);
        mediaRepository.save(media);
    }
}
