package tech.buildrun.urlshort.service;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.buildrun.urlshort.controller.dto.ShortenUrlRequest;
import tech.buildrun.urlshort.controller.dto.ShortenUrlResponse;
import tech.buildrun.urlshort.entity.UrlEntity;
import tech.buildrun.urlshort.repository.UrlRepository;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

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

    public ShortenUrlResponse getUrlAsString(String id){
        Optional<UrlEntity> optionalUrl = urlRepository.findById(id);

        if (optionalUrl.isPresent()) {
            var urlFull = optionalUrl.get().getFullUrl();
            return new ShortenUrlResponse(urlFull);
        } else {
            return new ShortenUrlResponse("ID não encontrado");
        }
    }

    public Optional<String> getFullUrlById(String id) {
        return urlRepository.findById(id)
                .map(UrlEntity::getFullUrl);
    }
}
