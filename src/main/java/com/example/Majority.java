package com.example;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Majority {

    Map<Integer, Integer> scoreTable = null;

    public Majority() {
        scoreTable = new HashMap<Integer, Integer>();
    }

    public void addPoints(List<Double> absoluteValues) {
        double low = 10000;
        for (Double absoluteValue : absoluteValues) {
            if (absoluteValue < low) {
                low = absoluteValue;
            }
        }
        int i = 1;
        for (Double absoluteValue : absoluteValues) {
            Integer integer = scoreTable.get(i);
            if (integer == null) {
                integer = 0;
                scoreTable.put(i, integer);
            }
            if (low == absoluteValue) {
                scoreTable.put(i, ++integer);
            }
            i++;
        }
    }

    public List<Integer> calculate() {
        int maxOfPoints = 0;
        for (int i = 1; i < scoreTable.size(); i++) {
            Integer integer = scoreTable.get(i);
            if (integer > maxOfPoints) {
                maxOfPoints = integer;
            }
        }

        int columnIndex = 1;
        List<Integer> columnName = new LinkedList<Integer>();
        for (Integer point : scoreTable.values()) {
            if (maxOfPoints == point) {
                columnName.add(columnIndex);
            }
            columnIndex++;
        }
        return columnName;
    }


}
