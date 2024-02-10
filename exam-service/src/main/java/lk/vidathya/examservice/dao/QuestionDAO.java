package lk.vidathya.examservice.dao;

import lk.vidathya.examservice.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDAO extends MongoRepository<Question, Integer>{
}
