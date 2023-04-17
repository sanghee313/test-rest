package com.example.studio.thinkground.data.repository;

import com.example.studio.thinkground.data.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl,Long> {

    ShortUrl findByUrl(String url);

    ShortUrl findByOrgUrl(String originalUrl);

}
