package br.com.springbatchredis.controller;

import br.com.springbatchredis.model.ReaderCSVModel;
import br.com.springbatchredis.model.ReaderTXTModel;
import br.com.springbatchredis.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReaderController {

    @Autowired
    ReaderService service;

    @GetMapping("/read_csv_file")
    public ResponseEntity<List<ReaderCSVModel>> readCSVFile () {
        try {
            List<ReaderCSVModel> response = service.readCSVFile();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/read_txt_file")
    public ResponseEntity<List<ReaderTXTModel>> readTXTFile () {
        try {
            List<ReaderTXTModel> response = service.readTXTFile();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
