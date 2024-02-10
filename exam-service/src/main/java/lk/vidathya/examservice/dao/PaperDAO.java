package lk.vidathya.examservice.dao;

import lk.vidathya.examservice.entity.Paper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperDAO extends MongoRepository<Paper, Integer> {
}
