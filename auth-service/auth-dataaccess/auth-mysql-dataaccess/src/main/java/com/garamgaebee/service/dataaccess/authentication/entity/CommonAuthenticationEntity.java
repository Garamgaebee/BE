package com.garamgaebee.service.dataaccess.authentication.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("COMMON")
@SuperBuilder
public class CommonAuthenticationEntity extends AuthenticationEntity {
    private String password;

}
