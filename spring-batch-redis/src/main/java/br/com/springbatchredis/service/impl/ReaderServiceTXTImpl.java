package br.com.springbatchredis.service.impl;

import br.com.springbatchredis.model.ReaderTXTModel;
import br.com.springbatchredis.repository.ReaderTXTRepository;
import br.com.springbatchredis.service.ReaderService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderServiceTXTImpl extends ReaderService<ReaderTXTModel> {

    Logger logger = LoggerFactory.getLogger(ReaderServiceTXTImpl.class);

    @Autowired
    ReaderTXTRepository txtRepository;

    @Override
    public List<ReaderTXTModel> readFile() throws IOException {
        FileReader in = new FileReader("src/main/resources/dataset/100-random-datas.txt");
        BufferedReader reader = new BufferedReader(in);

        List<ReaderTXTModel> lines = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null) {
            String[] values = line.split(";");
            if(!"id".equals(values[0])) {
                ReaderTXTModel txtModel = ReaderTXTModel
                        .builder()
                        .id(Integer.parseInt(values[0]))
                        .name(values[1])
                        .birthday(values[2])
                        .graduation(values[3])
                        .university(values[4])
                        .build();
                lines.add(txtModel);
                txtRepository.save(txtModel);
            }
        }
        logger.info("Read TXT File response: " + new Gson().toJson(lines));
        return lines;
    }
}
