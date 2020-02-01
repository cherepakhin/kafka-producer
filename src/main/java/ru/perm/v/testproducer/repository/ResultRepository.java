package ru.perm.v.testproducer.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.perm.v.kafka.model.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, UUID> {

}
