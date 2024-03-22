package com.practice.graduateWork.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NovelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String pageName;

    @Column
    private String content;

    public static NovelEntity toEntity(NovelDTO dto) {
        return new NovelEntity(dto.getId(), dto.getPageName(), dto.getContent());
    }
}
