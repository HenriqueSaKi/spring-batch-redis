package br.com.springbatchredis.service.impl;

import br.com.springbatchredis.model.ReaderCSVModel;
import br.com.springbatchredis.model.ReaderTXTModel;
import br.com.springbatchredis.service.ReaderService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {
    @Override
    public List<ReaderCSVModel> readCSVFile() throws IOException {
        FileReader in = new FileReader("src/main/resources/dataset/100-random-datas.csv");
        BufferedReader reader = new BufferedReader(in);

        List<ReaderCSVModel> lines = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null) {
            String[] values = line.split(";");
            if(!"id".equals(values[0])) {
                lines.add(
                    ReaderCSVModel
                        .builder()
                        .id(Integer.parseInt(values[0]))
                        .name(values[1])
                        .dateOfBirthday(values[2])
                        .currentJob(values[3])
                        .build()
                );
            }
        }
        return lines;

    }

    @Override
    public List<ReaderTXTModel> readTXTFile() throws IOException {
        FileReader in = new FileReader("src/main/resources/dataset/100-random-datas.txt");
        BufferedReader reader = new BufferedReader(in);

        List<ReaderTXTModel> lines = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null) {
            String[] values = line.split(";");
            if(!"id".equals(values[0])) {
                lines.add(
                        ReaderTXTModel
                                .builder()
                                .id(Integer.parseInt(values[0]))
                                .name(values[1])
                                .birthday(values[2])
                                .graduation(values[3])
                                .university(values[4])
                                .build()
                );
            }
        }
        return lines;
    }

}
