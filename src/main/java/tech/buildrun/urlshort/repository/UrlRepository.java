package tech.buildrun.urlshort.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.buildrun.urlshort.entity.UrlEntity;

@Repository
public interface UrlRepository extends MongoRepository<UrlEntity, String> {

}
