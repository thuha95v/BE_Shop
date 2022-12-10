package com.example.shopbe.entity;

import com.example.shopbe.entity.base.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bill_detail")
public class BillDetail extends AbstractAuditingEntity {

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

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "order_id")
  private Order order;

}
