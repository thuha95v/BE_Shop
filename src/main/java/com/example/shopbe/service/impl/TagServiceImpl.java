package com.example.shopbe.service.impl;

import com.example.shopbe.dto.TagDTO;
import com.example.shopbe.entity.Tag;
import com.example.shopbe.payload.TrueFalseResponse;
import com.example.shopbe.repository.TagRepository;
import com.example.shopbe.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

  private final TagRepository tagRepository;

  public TagServiceImpl(TagRepository tagRepository) {
    this.tagRepository = tagRepository;
  }

  @Override
  public Tag getTag(Long id) {
    Tag tag = tagRepository.findTagById(id);
    return tag;
  }

  @Override
  public Tag createTag(TagDTO tagDTO) {
    Tag tag = new Tag();
    tag.setName(tagDTO.getName());
    return tagRepository.save(tag);
  }

  @Override
  public Tag changeTag(Long id, TagDTO tagDTO) {
    Tag tag = tagRepository.findTagById(id);
    tag.setName(tagDTO.getName());
    return tagRepository.save(tag);
  }

  @Override
  public TrueFalseResponse deleteTag(Long id) {
    Tag tag = tagRepository.findTagById(id);
    tagRepository.delete(tag);
    return new TrueFalseResponse(true);
  }

  @Override
  public List<Tag> getAllTag() {
    return tagRepository.findAll();
  }
}
