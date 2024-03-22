package com.practice.graduateWork.Repository;

import com.practice.graduateWork.Domain.NovelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NovelRepository extends JpaRepository<NovelEntity, Long> {

}
