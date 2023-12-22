package br.com.springbatchredis.controller;

import br.com.springbatchredis.model.ReaderCSVModel;
import br.com.springbatchredis.model.ReaderTXTModel;
import br.com.springbatchredis.service.impl.ReaderServiceCSVImpl;
import br.com.springbatchredis.service.impl.ReaderServiceTXTImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/", produces = {"application/json"})
public class ReaderController {

    Logger logger = LoggerFactory.getLogger(ReaderController.class);

    @Autowired
    private ReaderServiceCSVImpl csvService;

    @Autowired
    private ReaderServiceTXTImpl txtService;

    @Operation(summary = "Read CSV file and store data into redis cache", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CSV file read successfully!"),
            @ApiResponse(responseCode = "400", description = "Failed to read CSV file")
    })
    @GetMapping("/read_csv_file")
    public ResponseEntity<List<ReaderCSVModel>> readCSVFile () {
        try {
            List<ReaderCSVModel> response = csvService.readFile();
            logger.info("CSV File read successfully at: " + LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            logger.error("Failed to read file. Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Read TXT file and store data into redis cache", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TXT file read successfully!"),
            @ApiResponse(responseCode = "400", description = "Failed to read TXT file")
    })
    @GetMapping("/read_txt_file")
    public ResponseEntity<List<ReaderTXTModel>> readTXTFile () {
        try {
            List<ReaderTXTModel> response = txtService.readFile();
            logger.info("TXT File read successfully at: " + LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            logger.error("Failed to read file. Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
