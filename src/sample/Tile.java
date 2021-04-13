package sample;

import javafx.geometry.Pos;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class Tile extends StackPane {
    private Text text = new Text();
    public String symbol;
    public int column;
    public int row;
    public Tile(Rectangle border) {
        border.setFill(null);
        border.setStroke(Color.BLACK);
        text.setFont(Font.font(72));

        setAlignment(Pos.CENTER);
        getChildren().addAll(border, text);

        setOnMouseClicked(event -> {
            draw(Main.symbol);
            ClientConnectionHandler.tile[column][row] = symbol;
        });
    }

    public String getValue() {
        return text.getText();
    }

    private void draw(String symbol) {
        text.setText(symbol);
    }
    public void setColumn(int number)
    {
        column = number;
    }
    public void setRow(int number)
    {
        row = number;
    }
}

