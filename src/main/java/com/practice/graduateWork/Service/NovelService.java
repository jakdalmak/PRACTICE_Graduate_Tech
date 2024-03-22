package com.practice.graduateWork.Service;

import com.practice.graduateWork.Domain.NovelDTO;
import com.practice.graduateWork.Domain.NovelEntity;
import com.practice.graduateWork.Repository.NovelRepository;
import org.springframework.stereotype.Service;

@Service
public class NovelService {
    public NovelRepository novelRepository;

    public NovelService(NovelRepository novelRepository) {
        this.novelRepository = novelRepository;
    }



    public void saveNovel(NovelDTO dto) {
        NovelEntity entity = NovelEntity.toEntity(dto);

        novelRepository.save(entity);
    }

    public NovelDTO makePage(String pageName) {
        NovelEntity entity = new NovelEntity(null, pageName, null);

        NovelEntity result = novelRepository.save(entity);

        return NovelDTO.toDTO(result);
    }
}
