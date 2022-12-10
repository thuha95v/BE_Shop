package com.example.shopbe.controller;

import com.example.shopbe.base.RestApiV1;
import com.example.shopbe.base.VsResponseUtil;
import com.example.shopbe.dto.ColorDTO;
import com.example.shopbe.dto.ContactDTO;
import com.example.shopbe.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class ContactController {
    private  final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contacts")
    public ResponseEntity<?> getAllContact(){
        return VsResponseUtil.ok(contactService.getAllContact());
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<?> getContact(@PathVariable Long id){
        return VsResponseUtil.ok(contactService.getContact(id));
    }

    @PostMapping("/contacts")
    public ResponseEntity<?> createContact(@RequestBody ContactDTO contactDTO){
        return VsResponseUtil.ok(contactService.createContact(contactDTO));
    }

    @PatchMapping("/contacts/{id}")
    public ResponseEntity<?> changeContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO){
        return VsResponseUtil.ok(contactService.changeContact(id, contactDTO));
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<?> deleteColor(@PathVariable Long id){
        return VsResponseUtil.ok(contactService.deleteContact(id));
    }
}
