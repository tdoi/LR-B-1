package com.example;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Table {

  private Map<Integer, List<String>> rows = null;
  private Map<Integer, List<String>> columns = null;

  public Table() {
    rows = new HashMap<Integer, List<String>>();
    columns = new HashMap<Integer, List<String>>();
  }

  public void createRow(int rowNumber, StringTokenizer token) {
    List<String> row = new LinkedList<String>();

    int i = 0;
    while (token.hasMoreTokens()) {
      String element = (String) token.nextToken();
      row.add(element);
      addColumn(i, element);
      i++;
    }
    rows.put(rowNumber, row);
  }

  private void addColumn(int i, String element) {
    List<String> column = columns.get(i);
    if (column == null) {
      column = new LinkedList<String>();
      columns.put(i, column);
    }
    column.add(element);
  }

  public Map<Integer, List<String>> getRows() {
    return rows;
  }

  public List<String> getRow(int rowNumber) {
    return rows.get(rowNumber);
  }

  public Map<Integer, List<String>> getColumns() {
    return columns;
  }

  public List<String> getColumn(int columnNumber) {
    return columns.get(columnNumber);
  }
}
