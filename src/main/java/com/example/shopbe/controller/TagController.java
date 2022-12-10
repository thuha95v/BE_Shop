package com.example.shopbe.controller;

import com.example.shopbe.base.RestApiV1;
import com.example.shopbe.base.VsResponseUtil;
import com.example.shopbe.dto.SizeDTO;
import com.example.shopbe.dto.TagDTO;
import com.example.shopbe.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestApiV1
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    @GetMapping("/tags")
    public ResponseEntity<?> getAllTag(){
        return VsResponseUtil.ok(tagService.getAllTag());
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<?> getTag(@PathVariable Long id){
        return VsResponseUtil.ok(tagService.getTag(id));
    }

    @PostMapping("/tags")
    public ResponseEntity<?> createTag(@RequestBody TagDTO tagDTO){
        return VsResponseUtil.ok(tagService.createTag(tagDTO));
    }

    @PatchMapping("/tags/{id}")
    public ResponseEntity<?> changeTag(@PathVariable Long id, @RequestBody TagDTO tagDTO){
        return VsResponseUtil.ok(tagService.changeTag(id, tagDTO));
    }

    @DeleteMapping("/tags/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Long id){
        return VsResponseUtil.ok(tagService.deleteTag(id));
    }
}

