package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    String file = "docs/test.csv";
    try {
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      LinearRegressionCreater linearRegressionCreater = new LinearRegressionCreater(bufferedReader);
      List<LinearRegression> linearRegressions = linearRegressionCreater.create();

      ResultAnalayzer resultAnalayzer = new ResultAnalayzer();
      Map<Integer, List<String>> table = linearRegressionCreater.getTable();
      List<Integer> results = resultAnalayzer.result(table, linearRegressions);

      System.out.println("最も正確に判断できるものは");
      List<String> list = table.get(0);
      for (Integer result : results) {
        System.out.println(list.get(result + 1));
      }
      bufferedReader.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
