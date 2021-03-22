package br.com.assesment.worstmovies.gateway.http;

import br.com.assesment.worstmovies.domain.Movie;
import br.com.assesment.worstmovies.gateway.response.ResponseMessage;
import br.com.assesment.worstmovies.helper.interfaces.CSVHelper;
import br.com.assesment.worstmovies.service.MovieService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(name = "/upload")
@AllArgsConstructor
public class UploadController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private CSVHelper<Movie> csvHelper;


    @ApiOperation(value = "Import csv movies list file")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty() || !csvHelper.isValid(file)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Only CSV files are allowed to upload"));
        } else {
            try {
                movieService.save(csvHelper.csvToEntity(file.getInputStream()));
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Upload successfully"));
            } catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("Error on get CSV file data."));
            }
        }
    }
}
