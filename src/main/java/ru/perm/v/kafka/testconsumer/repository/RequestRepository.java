package ru.perm.v.kafka.testconsumer.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.perm.v.kafka.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, UUID> {

}
