package com.credibanco.ms.repository;


import com.credibanco.ms.entity.TarjetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITarjetaRepository extends JpaRepository<TarjetaEntity, Integer> {

    TarjetaEntity findByIdSistema(String idSistema);

}
