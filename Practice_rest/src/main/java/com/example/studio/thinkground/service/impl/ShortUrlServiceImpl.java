package com.example.studio.thinkground.service.impl;

import com.example.studio.thinkground.data.dao.ShortUrlDao;
import com.example.studio.thinkground.data.dto.NaverUriDto;
import com.example.studio.thinkground.data.dto.ShortUrlResponseDto;
import com.example.studio.thinkground.data.entity.ShortUrl;
import com.example.studio.thinkground.service.ShortUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlServiceImpl.class);
    private final ShortUrlDao shortUrlDao;

    @Autowired
    public ShortUrlServiceImpl(ShortUrlDao shortUrlDao) {
        this.shortUrlDao = shortUrlDao;
    }


    @Override
    public ShortUrlResponseDto getShortUrl(String clientId, String clientSecret, String originalUrl) {
        LOGGER.info("[getShortUrl] request data : {}", originalUrl);

        ShortUrl getShortUrl = shortUrlDao.getShortUrl(originalUrl);

        String orgUrl;
        String shortUrl;

        if (getShortUrl == null) {
            LOGGER.info("[getShortUrl] No Entity in Database.");
            ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret,
                    originalUrl);

            orgUrl = responseEntity.getBody().getResult().getOrgUrl();
            shortUrl = responseEntity.getBody().getResult().getUrl();
            String hash = responseEntity.getBody().getResult().getHash();

            ShortUrl shortUrlEntity = new ShortUrl();
            shortUrlEntity.setOrgUrl(orgUrl);
            shortUrlEntity.setUrl(shortUrl);
            shortUrlEntity.setHash(hash);

            shortUrlDao.saveShortUrl(shortUrlEntity);

        } else {
            orgUrl = getShortUrl.getOrgUrl();
            shortUrl = getShortUrl.getUrl();
        }

        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);

        LOGGER.info("[getShortUrl] Response DTO : {}", shortUrlResponseDto);
        return shortUrlResponseDto;
    }

    @Override
    public ShortUrlResponseDto generateShortUrl(String clientId, String clientSecret,
                                                String originalUrl) {

        LOGGER.info("[generateShortUrl] request data : {}", originalUrl);

        if (originalUrl.contains("me2.do")) {
            throw new RuntimeException();
        }

        ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret,
                originalUrl);

        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
        String shortUrl = responseEntity.getBody().getResult().getUrl();
        String hash = responseEntity.getBody().getResult().getHash();

        ShortUrl shortUrlEntity = new ShortUrl();
        shortUrlEntity.setOrgUrl(orgUrl);
        shortUrlEntity.setUrl(shortUrl);
        shortUrlEntity.setHash(hash);

        shortUrlDao.saveShortUrl(shortUrlEntity);

        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);


        LOGGER.info("[generateShortUrl] Response DTO : {}", shortUrlResponseDto);
        return shortUrlResponseDto;
    }

    @Override
    public ShortUrlResponseDto updateShortUrl(String clientId, String clientSecret, String originalUrl) {
        return null;
    }

    @Override
    public void deleteShortUrl(String url) {
        if (url.contains("me2.do")) {
            LOGGER.info("[deleteShortUrl] Request Url is 'ShortUrl'.");
            deleteByShortUrl(url);
        } else {
            LOGGER.info("[deleteShortUrl] Request Url is 'OriginalUrl'.");
            deleteByOriginalUrl(url);
        }
    }

    private void deleteByShortUrl(String url) {
        LOGGER.info("[deleteByShortUrl] delete record");
        shortUrlDao.deleteByShortUrl(url);
    }

    private void deleteByOriginalUrl(String url) {
        LOGGER.info("[deleteByOriginalUrl] delete record");
        shortUrlDao.deleteByOriginalUrl(url);
    }



    private ResponseEntity<NaverUriDto> requestShortUrl(String clientId, String clientSecret,
                                                        String originalUrl) {
        LOGGER.info("[requestShortUrl] client ID : ***, client Secret : ***, original URL : {}", originalUrl);

        //uri나 url이나 비슷한거
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/util/shorturl")
                .queryParam("url", originalUrl)
                .encode()
                .build()
                .toUri();

        LOGGER.info("[requestShortUrl] set HTTP Request Header");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        RestTemplate restTemplate = new RestTemplate();

        LOGGER.info("[requestShortUrl] request by restTemplate");
        ResponseEntity<NaverUriDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
                entity, NaverUriDto.class);

        LOGGER.info("[requestShortUrl] request has been successfully complete.");

        return responseEntity;
    }


}
