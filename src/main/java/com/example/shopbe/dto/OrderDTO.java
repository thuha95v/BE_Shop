package com.example.shopbe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

  private String shipping;

  private List<OrderDetailDto> lstProduct;

  private String firstName;

  private String lastName;

  private String companyName;

  private String country;

  private String street;

  private String city;

  private String postcode;

  private String phone;

  private String email;

  private String orderNotes;

}
