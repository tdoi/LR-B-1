package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String file = "docs/test.csv";
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            TableGenerator tableGenerator = new TableGenerator();
            Table table = tableGenerator.make(bufferedReader);

            LinearRegressionCreater linearRegressionCreater = new LinearRegressionCreater(table);
            List<LinearRegression> linearRegressions = linearRegressionCreater.create();

            ResultAnalayzer resultAnalayzer = new ResultAnalayzer();
            List<Integer> results = resultAnalayzer.result(table, linearRegressions);

            System.out.println("最も正確に判断できるものは");
            List<String> row = table.getRow(0);
            for (Integer result : results) {
                System.out.println(row.get(result + 1));
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
