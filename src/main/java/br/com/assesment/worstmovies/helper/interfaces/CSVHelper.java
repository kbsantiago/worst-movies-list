package br.com.assesment.worstmovies.helper.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

public interface CSVHelper<T> {
     boolean isValid(MultipartFile file);
     List<T> csvToEntity(InputStream inputStream);
}
