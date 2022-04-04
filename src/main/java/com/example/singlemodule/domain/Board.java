package com.example.singlemodule.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
@DynamicUpdate
@DynamicInsert
public class Board {

    @Id
    @Column
    private String boardId;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String userInfoId;


    @ManyToOne
    @JoinColumn
    private UserInfo userInfo;

    @Column
    private Integer count;

}