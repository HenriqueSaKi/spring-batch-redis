package br.com.springbatchredis.service;

import java.io.IOException;
import java.util.List;

public abstract class ReaderService<T> {

    public abstract List<T> readFile() throws IOException;

}
