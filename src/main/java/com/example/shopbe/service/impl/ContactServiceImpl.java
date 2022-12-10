package com.example.shopbe.service.impl;

import com.example.shopbe.dto.ContactDTO;
import com.example.shopbe.entity.Contact;
import com.example.shopbe.payload.TrueFalseResponse;
import com.example.shopbe.repository.ContactRepository;
import com.example.shopbe.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

  private final ContactRepository contactRepository;

  public ContactServiceImpl(ContactRepository contactRepository) {
    this.contactRepository = contactRepository;
  }

  @Override
  public Contact getContact(Long id) {
    Contact contact = contactRepository.findContactById(id);
    return contact;
  }

  @Override
  public Contact createContact(ContactDTO contactDTO) {
    Contact contact = new Contact();
    contact.setName(contactDTO.getName());
    contact.setEmail(contactDTO.getEmail());
    contact.setMessage(contactDTO.getMessage());
    contact.setSubject(contactDTO.getSubject());
    return contactRepository.save(contact);
  }

  @Override
  public Contact changeContact(Long id, ContactDTO contactDTO) {
    Contact contact = new Contact();
    contact.setName(contactDTO.getName());
    contact.setEmail(contactDTO.getEmail());
    contact.setMessage(contactDTO.getMessage());
    contact.setSubject(contactDTO.getSubject());
    return contactRepository.save(contact);
  }

  @Override
  public TrueFalseResponse deleteContact(Long id) {
    Contact contact = contactRepository.findContactById(id);
    contactRepository.delete(contact);
    return new TrueFalseResponse(true);
  }

  @Override
  public List<Contact> getAllContact() {
    return contactRepository.findAll();
  }
}
