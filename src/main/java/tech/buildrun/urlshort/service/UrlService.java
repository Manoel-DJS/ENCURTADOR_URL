package tech.buildrun.urlshort.service;

import org.springframework.stereotype.Service;
import tech.buildrun.urlshort.repository.UrlRepository;

@Service
public class UrlService {
    private UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

}
