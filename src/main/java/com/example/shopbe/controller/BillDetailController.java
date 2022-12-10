package com.example.shopbe.controller;

import com.example.shopbe.base.RestApiV1;
import com.example.shopbe.base.VsResponseUtil;
import com.example.shopbe.dto.BillDetailDTO;
import com.example.shopbe.service.BillDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class BillDetailController {
    private final BillDetailService billDetailService;

    public BillDetailController(BillDetailService billDetailService) {
        this.billDetailService = billDetailService;
    }

    @GetMapping("/billDetails/{id}")
    public ResponseEntity<?> getBillDetail(@PathVariable Long id){
        return VsResponseUtil.ok(billDetailService.getBillDetail(id));
    }

    @GetMapping("/billDetails")
    public ResponseEntity<?> getAllBillDetail(){
        return VsResponseUtil.ok(billDetailService.getAllBillDetail());
    }

    @PostMapping("/billDetails")
    public ResponseEntity<?> createBillDetail(BillDetailDTO billDetailDTO){
        return VsResponseUtil.ok(billDetailService.createBillDetail(billDetailDTO));
    }

    @DeleteMapping("/billDetails/{id}")
    public ResponseEntity<?> deleteBillDetail(@PathVariable Long id){
        return VsResponseUtil.ok(billDetailService.deleteBillDetail(id));
    }

    @PatchMapping("/billDetails/{id}")
    public ResponseEntity<?> changeBillDetail(@PathVariable Long id, BillDetailDTO billDetailDTO){
        return VsResponseUtil.ok(billDetailService.changeBillDetail(id, billDetailDTO));
    }
}
