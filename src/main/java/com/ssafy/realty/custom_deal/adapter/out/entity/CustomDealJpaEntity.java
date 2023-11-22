package com.ssafy.realty.custom_deal.adapter.out.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "customs")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomDealJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Integer view;

    @Column
    private Integer star;

    @Column(name = "is_tmp")
    private String isTmp;

    @Column(name = "createDate")
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private CustomUserJpaEntity user;

//    @OneToMany(mappedBy = "custom", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<MarkerJpaEntity> markers;

    @OneToMany
    private Set<UserStarCustomJpaEntity> usersWhoStarred;

    public void viewIncrease(){
        this.view += 1;
    }

}
