package com.example;

import java.util.LinkedList;
import java.util.List;

public class LinearRegressionCreater {
    Table table = null;

    public LinearRegressionCreater(Table table) {
        this.table = table;
    }

    public List<LinearRegression> create() {
        List<LinearRegression> linearRegressions = new LinkedList<LinearRegression>();

        int codeAvgColumnNuber = table.getColumns().size() - 1;
        for (int i = 1; i < codeAvgColumnNuber; i++) {
            List<String> column = table.getColumn(i);
            List<String> codeAvgColumn = table.getColumn(codeAvgColumnNuber);
            LinearRegression linearRegression = new LinearRegression(column, codeAvgColumn);
            linearRegressions.add(linearRegression);
        }
        return linearRegressions;
    }
}
