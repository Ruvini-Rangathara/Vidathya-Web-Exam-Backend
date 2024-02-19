package lk.vidathya.examservice.dao;

import lk.vidathya.examservice.entity.MyExam;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyExamDAO extends MongoRepository<MyExam, Integer> {
    boolean existsByPaperIdAndNic(int paperId, String nic);
    List<MyExam> findAllByNic(String nic);

    MyExam findByPaperIdAndNic(int paperId, String nic);

    long countPaperByNic(String nic);
}
