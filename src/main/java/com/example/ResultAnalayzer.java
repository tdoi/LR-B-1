package com.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResultAnalayzer {

  public List<Integer> result(Map<Integer, List<String>> table,
      List<LinearRegression> linearRegressions) throws IOException {

    Map<Integer, Integer> result = new HashMap<Integer, Integer>();
    boolean first = true;
    for (List<String> row : table.values()) {
      if (first) {
        first = false;
        continue;
      }
      List<Double> absoluteValues = colcLowAbsoluteValues(row, linearRegressions);
      score(result, absoluteValues);
    }

    return judge(result);
  }

  private void score(Map<Integer, Integer> result, List<Double> absoluteValues) {
    double low = 10000;
    for (Double absoluteValue : absoluteValues) {
      if (absoluteValue < low) {
        low = absoluteValue;
      }
    }

    int i = 0;
    for (Double absoluteValue : absoluteValues) {
      Integer integer = result.get(i);
      if (integer == null) {
        integer = 0;
        result.put(i, integer);
      }
      if (low == absoluteValue) {
        result.put(i, ++integer);
      }
      i++;
    }
  }

  private List<Double> colcLowAbsoluteValues(List<String> row,
      List<LinearRegression> linearRegressions) {

    int codeColumnNumber = row.size() - 1;
    List<Double> abloludeValues = new LinkedList<Double>();
    for (int columnNumber = 1; columnNumber < codeColumnNumber; columnNumber++) {
      String data = row.get(columnNumber);
      int value = Integer.parseInt(data);
      int valueOfCode = Integer.parseInt(row.get(codeColumnNumber));

      LinearRegression linearRegression = linearRegressions.get(columnNumber - 1);
      double answer = linearRegression.colcu(value);

      double abs = Math.abs(valueOfCode - answer);
      abloludeValues.add(abs);
    }
    return abloludeValues;
  }

  private List<Integer> judge(Map<Integer, Integer> result) {

    int maxOfPoints = 0;
    for (int i = 0; i < result.size(); i++) {
      Integer integer = result.get(i);
      if (integer > maxOfPoints) {
        maxOfPoints = integer;
      }
    }

    int columnIndex = 0;
    List<Integer> columnName = new LinkedList<Integer>();
    for (Integer point : result.values()) {
      if (maxOfPoints == point) {
        columnName.add(columnIndex);
      }
      columnIndex++;
    }
    return columnName;
  }

}
