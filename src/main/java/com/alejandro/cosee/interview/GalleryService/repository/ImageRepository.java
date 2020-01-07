package com.alejandro.cosee.interview.GalleryService.repository;

import com.alejandro.cosee.interview.GalleryService.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
}
