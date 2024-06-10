package com.willian.mscartoes.infra;

import com.willian.mscartoes.domain.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repository extends JpaRepository<Cartao, Long> {
}
