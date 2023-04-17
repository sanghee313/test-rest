package com.example.studio.thinkground.controller;

import com.example.studio.thinkground.data.dto.ShortUrlResponseDto;
import com.example.studio.thinkground.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/short-url")
public class ShortUrlController {

    private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlController.class);

    @Value("${naver.short.url.id}")
    private String CLIENT_ID;

    @Value("${naver.short.url.secret}")
    private String CLIENT_SECRET;

    ShortUrlService shortUrlService;

    @Autowired
    public ShortUrlController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @PostMapping() //Create
    public ShortUrlResponseDto generateShortUrl(String originalUrl) {
        LOGGER.info("[generateShortUrl] perform API. CLIENT_ID : {}, CLIENT_SECRET : {}", CLIENT_ID, CLIENT_SECRET);

        return shortUrlService.generateShortUrl(CLIENT_ID, CLIENT_SECRET, originalUrl);
    }

    @GetMapping() //Read
    public ShortUrlResponseDto getShortUrl(String originalUrl) {
        long startTime = System.currentTimeMillis();
        ShortUrlResponseDto shortUrlResponseDto = shortUrlService.getShortUrl(CLIENT_ID, CLIENT_SECRET, originalUrl);
        long endTime = System.currentTimeMillis();

        LOGGER.info("[getShortUrl] response Time : {}ms", (endTime - startTime));

        return shortUrlResponseDto;
    }

    @PutMapping("/") //update
    public ShortUrlResponseDto updateShortUrl(String originalUrl) {
        return null;
    }

    @DeleteMapping("/") //Delete
    public ResponseEntity<String> deleteShortUrl(String url) {
        try {
            shortUrlService.deleteShortUrl(url);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }


}
