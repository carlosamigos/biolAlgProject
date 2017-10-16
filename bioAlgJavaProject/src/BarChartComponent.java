//BarChartComponent.java - Jimmy Kurian

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BarChartComponent extends JComponent {

    private ArrayList<String> lengths ;
    private ArrayList<Integer> values;

    public BarChartComponent(ArrayList<String> lengths, ArrayList<Integer> values){
        this.lengths = lengths;
        this.values = values;
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        BarChart c = new BarChart(getWidth(), getHeight());
        for (int i = 0; i < lengths.size(); i++) {
            c.add(lengths.get(i), values.get(i));
        }
        c.draw(g2);
    }
}