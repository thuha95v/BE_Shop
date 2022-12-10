package com.example.shopbe.dto;

import lombok.*;
import org.hibernate.annotations.Nationalized;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BillDetailDTO {
    @Nationalized
    private String firstName;

    @Nationalized
    private String lastName;

    @Nationalized
    private String companyName;

    @Nationalized
    private String country;

    @Nationalized
    private String street;

    @Nationalized
    private String city;

    private String postcode;

    private String phone;

    private String email;

    @Nationalized
    private String orderNotes;
}
