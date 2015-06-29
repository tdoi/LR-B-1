package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class LinearRegressionCreater {
  HashMap<Integer, List<String>> table = null;

  public LinearRegressionCreater(BufferedReader bufferedReader) throws IOException {
    table = generateTable(bufferedReader);
  }

  public HashMap<Integer, List<String>> getTable() {
    return table;
  }

  private HashMap<Integer, List<String>> generateTable(BufferedReader bufferedReader)
      throws IOException {
    HashMap<Integer, List<String>> table = new HashMap<Integer, List<String>>();
    String line = null;
    StringTokenizer token = null;

    int i = 0;
    while ((line = bufferedReader.readLine()) != null) {
      token = new StringTokenizer(line, ",");

      List<String> row = crateRow(token);
      table.put(i, row);
      i++;
    }
    return table;
  }

  private List<String> crateRow(StringTokenizer token) {
    LinkedList<String> linkedList = new LinkedList<String>();
    while (token.hasMoreTokens()) {
      String element = (String) token.nextToken();
      linkedList.add(element);
    }
    return linkedList;
  }

  public List<LinearRegression> create() {
    HashMap<Integer, List<String>> columns = new HashMap<Integer, List<String>>();

    for (int i = 1; i < table.get(0).size(); i++) {
      LinkedList<String> column = new LinkedList<String>();
      int first = 0;
      for (List<String> row : table.values()) {
        if (first == 0) {
          first++;
          continue;
        }
        column.add(row.get(i));
      }
      columns.put(i, column);
    }
    return createLinearRegressions(columns);
  }

  private List<LinearRegression> createLinearRegressions(HashMap<Integer, List<String>> columns) {

    LinkedList<LinearRegression> linearRegressions = new LinkedList<LinearRegression>();
    int columnSize = columns.keySet().size();
    for (int i = 1; i < columnSize; i++) {
      List<String> column = columns.get(i);
      int numberOfCodeColumn = columns.keySet().size();
      List<String> codeColumn = columns.get(numberOfCodeColumn);
      LinearRegression linearRegression = new LinearRegression(column, codeColumn);
      linearRegressions.add(linearRegression);
    }
    return linearRegressions;
  }
}
