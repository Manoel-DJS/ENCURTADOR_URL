package tech.buildrun.urlshort.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.buildrun.urlshort.entity.UrlEntity;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {

}
