package com.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResultAnalayzer {

    public List<Integer> result(Table table) {
        LinearRegressionCreater linearRegressionCreater = new LinearRegressionCreater(table);
        Map<Integer, LinearRegression> linearRegressions = linearRegressionCreater.create();

        Majority majority = new Majority();
        for (int i = 1; i < table.getRows().size(); i++) {
            List<String> row = table.getRow(i);
            List<Double> absoluteValues = calcuLowAbsoluteValues(row, linearRegressions);
            majority.addPoints(absoluteValues);
        }
        return majority.calculate();
    }

    private List<Double> calcuLowAbsoluteValues(List<String> row, Map<Integer, LinearRegression> linearRegressions) {

        int codeColumnNumber = row.size() - 1;
        List<Double> absoluteValues = new LinkedList<Double>();

        for (int i = 1; i < codeColumnNumber; i++) {
            double abs = calcuAbsoluteValue(row, linearRegressions, i);
            absoluteValues.add(abs);
        }
        return absoluteValues;
    }

    private double calcuAbsoluteValue(List<String> row, Map<Integer, LinearRegression> linearRegressions, int columnNumber) {
        String data = row.get(columnNumber);
        int value = Integer.parseInt(data);
        LinearRegression linearRegression = linearRegressions.get(columnNumber);

        double answer = linearRegression.calculation(value);
        int valueOfCode = Integer.parseInt(row.get(row.size() - 1));
        double abs = Math.abs(valueOfCode - answer);
        return abs;
    }
}
