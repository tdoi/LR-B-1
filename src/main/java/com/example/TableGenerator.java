package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TableGenerator {

    public Table make(BufferedReader bufferedReader) throws IOException {
        String line = null;
        StringTokenizer token = null;

        int i = 0;
        Table table = new Table();
        while ((line = bufferedReader.readLine()) != null) {
            token = new StringTokenizer(line, ",");
            table.createRow(i, token);
            i++;
        }
        return table;
    }
}
