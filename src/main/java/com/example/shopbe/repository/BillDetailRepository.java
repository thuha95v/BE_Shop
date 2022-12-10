package com.example.shopbe.repository;

import com.example.shopbe.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
    BillDetail findBillDetailById(Long id);
}
