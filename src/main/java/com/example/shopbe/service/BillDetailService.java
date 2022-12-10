package com.example.shopbe.service;

import com.example.shopbe.dto.BillDetailDTO;
import com.example.shopbe.entity.BillDetail;
import com.example.shopbe.payload.TrueFalseResponse;

import java.util.List;

public interface BillDetailService {
    BillDetail getBillDetail(Long id);
    List<BillDetail> getAllBillDetail();
    BillDetail createBillDetail(BillDetailDTO billDetailDTO);
    TrueFalseResponse deleteBillDetail(Long id);
    BillDetail changeBillDetail(Long id, BillDetailDTO billDetailDTO);
}
