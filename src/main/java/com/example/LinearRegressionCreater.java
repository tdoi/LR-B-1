package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinearRegressionCreater {
    Table table = null;

    public LinearRegressionCreater(Table table) {
        this.table = table;
    }

    public Map<Integer, LinearRegression> create() {
        Map<Integer, LinearRegression> linearRegressions = new HashMap<Integer, LinearRegression>();

        int codeAvgColumnNuber = table.getColumns().size() - 1;
        for (int i = 1; i < codeAvgColumnNuber; i++) {
            List<String> column = table.getColumn(i);
            List<String> codeAvgColumn = table.getColumn(codeAvgColumnNuber);
            LinearRegression linearRegression = new LinearRegression(column, codeAvgColumn);
            linearRegressions.put(i, linearRegression);
        }
        return linearRegressions;
    }
}
