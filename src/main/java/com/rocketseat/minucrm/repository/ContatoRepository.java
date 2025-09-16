package com.rocketseat.minucrm.repository;

import com.rocketseat.minucrm.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato,Long> {

}
