package lk.vidathya.examservice.dao;

import lk.vidathya.examservice.entity.Paper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperDAO extends CrudRepository<Paper, Integer> {
}
