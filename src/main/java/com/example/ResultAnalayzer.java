package com.example;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResultAnalayzer {

    public List<Integer> result(Table table, List<LinearRegression> linearRegressions) {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        for (int i = 1; i < table.getRows().size(); i++) {
            List<String> row = table.getRow(i);
            List<Double> absoluteValues = calcuLowAbsoluteValues(row, linearRegressions);
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

    private List<Double> calcuLowAbsoluteValues(List<String> row, List<LinearRegression> linearRegressions) {

        int codeColumnNumber = row.size() - 1;
        List<Double> abloludeValues = new LinkedList<Double>();

        for (int i = 1; i < codeColumnNumber; i++) {
            double abs = calcuAbsoluteValue(row, linearRegressions, i);
            abloludeValues.add(abs);
        }
        return abloludeValues;
    }

    private double calcuAbsoluteValue(List<String> row, List<LinearRegression> linearRegressions, int columnNumber) {
        String data = row.get(columnNumber);
        int value = Integer.parseInt(data);
        LinearRegression linearRegression = linearRegressions.get(columnNumber - 1);

        double answer = linearRegression.calculation(value);
        int valueOfCode = Integer.parseInt(row.get(row.size() - 1));
        double abs = Math.abs(valueOfCode - answer);
        return abs;
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
