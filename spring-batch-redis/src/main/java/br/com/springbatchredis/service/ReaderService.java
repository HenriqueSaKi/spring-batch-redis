package br.com.springbatchredis.service;

import br.com.springbatchredis.model.ReaderCSVModel;
import br.com.springbatchredis.model.ReaderTXTModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

public interface ReaderService {

    List<ReaderCSVModel> readCSVFile() throws IOException;

    List<ReaderTXTModel> readTXTFile() throws IOException;

}
