package ru.perm.v.kafka.testproducer.repository;

import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.perm.v.kafka.model.Result;

@Repository
@Transactional
public interface ResultRepository extends JpaRepository<Result, UUID> {

}
