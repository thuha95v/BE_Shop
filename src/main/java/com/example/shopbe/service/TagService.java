package com.example.shopbe.service;

import com.example.shopbe.dto.TagDTO;
import com.example.shopbe.entity.Tag;
import com.example.shopbe.payload.TrueFalseResponse;

import java.util.List;

public interface TagService {
  Tag getTag(Long id);

  Tag createTag(TagDTO tagDTO);

  Tag changeTag(Long id, TagDTO tagDTO);

  TrueFalseResponse deleteTag(Long id);

  List<Tag> getAllTag();
}
