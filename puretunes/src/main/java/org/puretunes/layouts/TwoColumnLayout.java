package org.puretunes.layouts;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TwoColumnLayout extends GridPane {
    private final int leftColPercent;
    private final int rightColPercent;

    public TwoColumnLayout(int leftColPercent, int rightColPercent) {
        super();
        this.leftColPercent = leftColPercent;
        this.rightColPercent = rightColPercent;
    }

    public void addColumns(Pane leftColumn, Pane rightColumn) {
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(leftColPercent);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(rightColPercent);
        this.getColumnConstraints().addAll(col1, col2);
        this.add(leftColumn, 0, 0);
        this.add(rightColumn, 1, 0);
    }
}
