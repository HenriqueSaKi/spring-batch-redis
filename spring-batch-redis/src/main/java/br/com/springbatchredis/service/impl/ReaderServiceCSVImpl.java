package br.com.springbatchredis.service.impl;

import br.com.springbatchredis.model.ReaderCSVModel;
import br.com.springbatchredis.repository.ReaderCSVRepository;
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
public class ReaderServiceCSVImpl extends ReaderService<ReaderCSVModel> {

    Logger logger = LoggerFactory.getLogger(ReaderServiceCSVImpl.class);

    @Autowired
    ReaderCSVRepository csvRepository;

    @Override
    public List<ReaderCSVModel> readFile() throws IOException {
        FileReader in = new FileReader("src/main/resources/dataset/100-random-datas.csv");
        BufferedReader reader = new BufferedReader(in);

        List<ReaderCSVModel> lines = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null) {
            String[] values = line.split(";");
            if(!"id".equals(values[0])) {
                ReaderCSVModel csvModel = ReaderCSVModel
                        .builder()
                        .id(values[0])
                        .name(values[1])
                        .dateOfBirthday(values[2])
                        .currentJob(values[3])
                        .build();
                lines.add(csvModel);
                csvRepository.save(csvModel);
            }
        }
        logger.info("Read CSV File response: " + new Gson().toJson(lines));
        return lines;
    }
}
