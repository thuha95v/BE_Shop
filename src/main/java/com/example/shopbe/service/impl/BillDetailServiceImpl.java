package com.example.shopbe.service.impl;

import com.example.shopbe.dto.BillDetailDTO;
import com.example.shopbe.entity.BillDetail;
import com.example.shopbe.payload.TrueFalseResponse;
import com.example.shopbe.repository.BillDetailRepository;
import com.example.shopbe.service.BillDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillDetailServiceImpl implements BillDetailService {

    private final BillDetailRepository billDetailRepository;
    private final ModelMapper modelMapper;

    public BillDetailServiceImpl(BillDetailRepository billDetailRepository, ModelMapper modelMapper) {
        this.billDetailRepository = billDetailRepository;

        this.modelMapper = modelMapper;
    }

    @Override
    public BillDetail getBillDetail(Long id) {
        BillDetail billDetail = billDetailRepository.findBillDetailById(id);
        return billDetail;
    }

    @Override
    public List<BillDetail> getAllBillDetail() {
        return billDetailRepository.findAll();
    }

    @Override
    public BillDetail createBillDetail(BillDetailDTO billDetailDTO) {

        BillDetail billDetail = modelMapper.map(billDetailDTO, BillDetail.class);


        return billDetailRepository.save(billDetail);
    }

    @Override
    public TrueFalseResponse deleteBillDetail(Long id) {
        BillDetail billDetail = billDetailRepository.findBillDetailById(id);
        billDetailRepository.delete(billDetail);
        return new TrueFalseResponse(true);
    }

    @Override
    public BillDetail changeBillDetail(Long id, BillDetailDTO billDetailDTO) {
        BillDetail billDetail = billDetailRepository.findBillDetailById(id);
        modelMapper.map(billDetailDTO, billDetail);

        return billDetailRepository.save(billDetail);
    }
}
