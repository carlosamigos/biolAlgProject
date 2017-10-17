//BarChart.java - Jimmy Kurian

import javax.swing.*;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.Rectangle;

public class BarChart
{
    private int width;
    private int height;
    private ArrayList<String> lengths;
    private ArrayList<Integer> values;

    public BarChart(int aWidth, int aHeight) {
        width = aWidth;
        height = aHeight;
        values = new ArrayList<Integer>();
        lengths = new ArrayList<String>();
    }

    public void add(String length, int value) {
        lengths.add(length);
        values.add(value);
    }

    public void draw(Graphics2D g2) {
        int i = 0;
        double max = 0;

        for (Integer wrapper : values)
            if(max < wrapper)
                max = wrapper;

        int xwidth = width - 1;
        int yheight = height - 1;

        int xleft = 0;

        for (i = 0; i < values.size(); i++) {
            int xright = xwidth * (i + 1) / values.size();
            int barWidth = xwidth / values.size();
            int barHeight = (int) Math.round(yheight * values.get(i) / max);

            Rectangle bar =
                    new Rectangle(xleft, yheight - barHeight,
                            barWidth, barHeight);
            g2.draw(bar);

            xleft = xright;
        }
    }

}