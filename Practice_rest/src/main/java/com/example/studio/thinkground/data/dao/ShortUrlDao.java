package com.example.studio.thinkground.data.dao;

import com.example.studio.thinkground.data.entity.ShortUrl;

public interface ShortUrlDao {

    ShortUrl saveShortUrl(ShortUrl shortUrl); //shortUrl 저장

    ShortUrl getShortUrl(String originalUrl); //데베에서 축약한거 가져오기

    ShortUrl getOriginalUrl(String shortUrl); //데베에서 원본 가져오기

    ShortUrl updateShortUrl(ShortUrl newShortUrl); //데배 갱신

    void deleteByShortUrl(String shortUrl); //축약 삭제

    void deleteByOriginalUrl(String originalUrl); //원본 삭제제

}
