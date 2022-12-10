package com.example.shopbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    @Nationalized
    private String name;

    private String email;

    @Nationalized
    private String subject;

    @Nationalized
    private String message;
}
