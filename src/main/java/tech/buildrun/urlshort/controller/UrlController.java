package tech.buildrun.urlshort.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.urlshort.controller.dto.ShortenUrlRequest;
import tech.buildrun.urlshort.controller.dto.ShortenUrlResponse;
import tech.buildrun.urlshort.service.UrlService;

import java.net.URI;
import java.util.Optional;


@RestController
@Tag(name = "Encurtar URL / LINKS")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @Operation(summary = "Encurtar uma URL", description = "Recebe uma URL longa e retorna uma versão encurtada.")
    @PostMapping(value = "/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request, HttpServletRequest servletRequest){
        return ResponseEntity.ok((urlService.shortenUrl(request,servletRequest)));
    }

    @GetMapping("/")
    public String ok() {
        return "OK";
    }

    @Operation(summary = "Redirecionar para URL original / Teste com Insomnia ou Postman", description = "Recebe um ID da URL encurtada e redireciona para a URL original.")
    @GetMapping("{id}")
    public ResponseEntity<Void> getUrl(@PathVariable("id") String id){
        Optional<String> fullUrl = urlService.getFullUrlById(id);
        if(fullUrl.isEmpty()){ // se for vazia
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(fullUrl.get()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

    @Operation(summary = "Redirecionar para URL original em formato String", description = "Recebe um ID da URL encurtada e recebe uma String da URL original.")
    @GetMapping("/api/{id}/url")
    public ResponseEntity<ShortenUrlResponse> getUrlAsString(@PathVariable String id) {
        return ResponseEntity.ok(urlService.getUrlAsString(id));
    }
}
