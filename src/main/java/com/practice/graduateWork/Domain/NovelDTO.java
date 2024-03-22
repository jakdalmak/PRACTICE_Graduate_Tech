package com.practice.graduateWork.Domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class NovelDTO {
    private Long id;
    private String pageName;
    private String content;


    public static NovelDTO toDTO(NovelEntity entity) {
        return new NovelDTO(entity.getId(), entity.getPageName(), entity.getContent());
    }
}
