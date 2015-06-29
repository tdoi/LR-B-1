package com.example;

import java.util.List;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class LinearRegression {
  SimpleRegression simpleRegression = null;

  public LinearRegression(List<String> column, List<String> condeColumn) {
    setRegressionData(column, condeColumn);
  }

  private void setRegressionData(List<String> column, List<String> codeColumn) {
    this.simpleRegression = new SimpleRegression();
    int rowNumber = column.size();
    for (int i = 1; i < rowNumber; i++) {
      simpleRegression
          .addData(Integer.parseInt(column.get(i)), Integer.parseInt(codeColumn.get(i)));
    }
  }

  private double getSlope() {
    return simpleRegression.getSlope();
  }

  private double getInterCept() {
    return simpleRegression.getIntercept();
  }

  public double calculation(int value) {
    return getSlope() * value + getInterCept();
  }
}
