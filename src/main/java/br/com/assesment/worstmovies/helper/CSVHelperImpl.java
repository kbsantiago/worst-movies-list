package br.com.assesment.worstmovies.helper;

import br.com.assesment.worstmovies.domain.Movie;
import br.com.assesment.worstmovies.helper.interfaces.CSVHelper;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CSVHelperImpl<T> implements CSVHelper {

    private final String TYPE = "csv";
    private final String[] HEADERS = {"year", "title", "studios", "produces", "winner"};

    @Override
    public boolean isValid(MultipartFile file) {
        boolean result = TYPE.equals(file.getOriginalFilename().split("\\.")[1]);
        return result;
    }

    @Override
    public List csvToEntity(InputStream inputStream) {
        List<Movie> resultList = new ArrayList<Movie>();
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().withDelimiter(';'));) {

            csvParser.getRecords().stream().forEach(csvRecord -> {
                String replaced = csvRecord.get("producers").replace(" and ", ",");
                String[] producers = replaced.split(",");
                for (String producer : producers) {
                    resultList.add(new Movie().builder()
                            .year(Integer.parseInt(csvRecord.get("year")))
                            .title(csvRecord.get("title"))
                            .studios(csvRecord.get("studios"))
                            .producer(producer.trim())
                            .winner(csvRecord.get("winner").equals("yes") ? true : false)
                            .build());
                }
            });

            return resultList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
