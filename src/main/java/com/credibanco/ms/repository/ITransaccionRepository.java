package com.credibanco.ms.repository;

import com.credibanco.ms.entity.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransaccionRepository extends JpaRepository<TransaccionEntity, Integer> {

    TransaccionEntity findByReferencia(String referencia);

}
