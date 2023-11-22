package com.ssafy.realty.realty.adapter.out.entity;

import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@DiscriminatorValue("tmp")
public class TemporaryCustomJpaEntity extends CustomJpaEntity{
}
