package ru.perm.v.kafka.testconsumer.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.perm.v.kafka.model.Request;
import ru.perm.v.kafka.model.Status;

@Repository
public interface RequestRepository extends JpaRepository<Request, UUID> {

  List<Request> findByStatus(Status status, Pageable pageable);
}
