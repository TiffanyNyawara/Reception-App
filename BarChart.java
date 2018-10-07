/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reeption;

import java.awt.Color;
import java.awt.GradientPaint;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author triad
 */
public class BarChart extends JFrame{
    DBUpdater db = new DBUpdater();
    int females, total, males;
    int age, ageBracket1, ageBracket2, ageBracket3, ageBracket4;
    
    public BarChart(String title){
        CategoryDataset dataset2  = createDataset2();
        JFreeChart chart2 = createBarChart(dataset2, "Age Distribution");
        ChartPanel chartPanel2 = new ChartPanel(chart2);
        chartPanel2.setPreferredSize(new java.awt.Dimension(500, 300));
        setContentPane(chartPanel2);
    }
     private CategoryDataset createDataset2(){
         ResultSet r = db.chartData();
              try {
            //Male and Female Counter
            while(r.next()){               
                String gendersex = r.getString(1);
                if("Female".equals(gendersex))
                    females = females + 1;
                if("Male".equals(gendersex))
                    males = males + 1;
                String ages = r.getString(2);
                age = Integer.valueOf(ages);
                if(age <= 18)
                    ageBracket1 = ageBracket1 + 1;
                if(age>18 && age <=25)
                    ageBracket2 = ageBracket2 + 1;
                if(age>25 && age<=35)
                    ageBracket3 = ageBracket3 + 1;
                if(age>35)
                    ageBracket4 = ageBracket4 + 1;
    }} catch (SQLException ex) {
    Logger.getLogger(BarChart.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultCategoryDataset res = new DefaultCategoryDataset();
        res.addValue(ageBracket1, "0 - 18","");
        res.addValue(ageBracket2, "18 - 25","");
        res.addValue(ageBracket3, "25 - 35","");
        res.addValue(ageBracket4, "35 and Over","");
        return res;
    }
    private JFreeChart createBarChart(CategoryDataset data, String title){
        JFreeChart chart2 = ChartFactory.createBarChart(title, "Age Bracket", "Number", data);
        chart2.setBackgroundPaint(Color.WHITE);
        CategoryPlot plot2 = chart2.getCategoryPlot();
        plot2.setBackgroundPaint(Color.LIGHT_GRAY);
        plot2.setDomainGridlinePaint(Color.WHITE);
        plot2.setRangeGridlinePaint(Color.WHITE);
        NumberAxis rangeAxis = (NumberAxis) plot2.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        BarRenderer renderer = (BarRenderer) plot2.getRenderer();
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, Color.lightGray);
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f, 0.0f, Color.lightGray);
        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, Color.lightGray);
	renderer.setSeriesPaint(0, gp0);
	renderer.setSeriesPaint(1, gp1);
	renderer.setSeriesPaint(2, gp2);
        
        CategoryAxis domainAxis = plot2.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
        return chart2;
    }
}
