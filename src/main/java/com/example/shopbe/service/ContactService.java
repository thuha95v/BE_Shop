package com.example.shopbe.service;

import com.example.shopbe.dto.ContactDTO;
import com.example.shopbe.entity.Contact;
import com.example.shopbe.payload.TrueFalseResponse;

import java.util.List;

public interface ContactService {

  Contact getContact(Long id);

  Contact createContact(ContactDTO contactDTO);

  Contact changeContact(Long id, ContactDTO contactDTO);

  TrueFalseResponse deleteContact(Long id);

  List<Contact> getAllContact();

}
