package it.objectmethod.demo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.objectmethod.demo.spring.models.MemberObject;

@Repository
public interface MemberRepository extends JpaRepository<MemberObject, Long> {

}