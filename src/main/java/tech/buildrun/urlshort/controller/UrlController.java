package tech.buildrun.urlshort.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.urlshort.controller.dto.ShortenUrlRequest;
import tech.buildrun.urlshort.controller.dto.ShortenUrlResponse;
import tech.buildrun.urlshort.entity.UrlEntity;
import tech.buildrun.urlshort.repository.UrlRepository;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Random;

@RestController
@Tag(name = "Encurtar URL / LINKS")
public class UrlController {

    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Operation(summary = "Encurtar uma URL", description = "Recebe uma URL longa e retorna uma versão encurtada.")
    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request, HttpServletRequest servletRequest){
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1)));

        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", id);

        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
    }

    @GetMapping("/")
    public String ok() {
        return "OK";
    }

    @Operation(summary = "Redirecionar para URL original", description = "Recebe um ID da URL encurtada e redireciona para a URL original.")
    @GetMapping("{id}")
    public ResponseEntity<String> redirect(@PathVariable("id") String id){

        var url = urlRepository.findById(id);

        // Se a URL for vazia
        if(url.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(url.get().getFullUrl());
    }

}
