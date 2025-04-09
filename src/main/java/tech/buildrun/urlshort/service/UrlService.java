package tech.buildrun.urlshort.service;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import tech.buildrun.urlshort.controller.dto.ShortenUrlRequest;
import tech.buildrun.urlshort.controller.dto.ShortenUrlResponse;
import tech.buildrun.urlshort.entity.UrlEntity;
import tech.buildrun.urlshort.repository.UrlRepository;

import java.time.LocalDateTime;

@Service
public class UrlService {
    private UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public ShortenUrlResponse shortenUrl(ShortenUrlRequest request, HttpServletRequest servletRequest){
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));
        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(2)));
        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", id);
        return new ShortenUrlResponse(redirectUrl);
    }
}
