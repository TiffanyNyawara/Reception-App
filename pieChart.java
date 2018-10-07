/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reeption;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;


/**
 *
 * @author triad
 */
public class pieChart extends JFrame {
    DBUpdater db = new DBUpdater();
    int females, total, males;
    int age, ageBracket1, ageBracket2, ageBracket3, ageBracket4;
   
    public pieChart(String appTitle, String chartTitle){
       PieDataset dataset1 = createDataset1();
       JFreeChart chart1 = createChart(dataset1, chartTitle);
       ChartPanel chartPanel1 = new ChartPanel(chart1);
       chartPanel1.setPreferredSize(new java.awt.Dimension(500, 300));
       setContentPane(chartPanel1);
    }
    private PieDataset createDataset1(){
        DefaultPieDataset result = new DefaultPieDataset();
        try {
            ResultSet r = db.chartData();
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
    Logger.getLogger(pieChart.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        result.setValue("Male", males);
        result.setValue("Female", females);
        return result;
    }
    private JFreeChart createChart(PieDataset dataset1, String title){
        JFreeChart chart1 = ChartFactory.createPieChart3D(title, dataset1, true, true, Locale.ITALY);
        PiePlot3D plot = (PiePlot3D) chart1.getPlot();
        plot.setStartAngle(0);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(1f);
        return chart1;
    }
}

