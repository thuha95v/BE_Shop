package com.example.shopbe.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.shopbe.config.exception.VsException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

@Component
public class UploadFileUtil {
  private final Cloudinary cloudinary;

  public UploadFileUtil(Cloudinary cloudinary) {
    this.cloudinary = cloudinary;
  }

  @SneakyThrows
  public static File convertMultipartToFile(MultipartFile file) {
    File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(file.getBytes());
    fos.close();
    return convFile;
  }

  @SneakyThrows
  public static File writeToFile(String content, String fileName) {
    Path path = Paths.get(fileName);
    Files.write(path, content.getBytes());
    return path.toFile();
  }

  public String getUrlFromFile(MultipartFile multipartFile) {
    try {
      Map<?, ?> map = cloudinary.uploader().upload(multipartFile.getBytes(), ObjectUtils.emptyMap());
      return map.get("secure_url").toString();
    } catch (IOException e) {
      throw new VsException("Upload image failed");
    }
  }

  public void removeImageFromUrl(String url) {
    try {
      cloudinary.uploader().destroy(url, ObjectUtils.emptyMap());
    } catch (IOException e) {
      throw new VsException("Upload image failed");
    }
  }

}