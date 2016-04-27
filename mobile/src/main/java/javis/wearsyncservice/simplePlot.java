package javis.wearsyncservice;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.LineAndPointRenderer;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.Arrays;

/**
 * Created by Me on 4/17/16.
 */
public class simplePlot extends Activity {

    private XYPlot mySimpleXYPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_plot);

        //initialized plot reference
        mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

        //create array of y-values to plot
        Number[] numbers = {6, 6, 3, 3, 8, 8, 9}; //index corresponds to x-value

        //turn above numbers to XYSeries
        XYSeries series = new SimpleXYSeries(Arrays.asList(numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series");

        //create a formatter to use for drawing a series using LineAndPointRenderer
        LineAndPointFormatter seriesFormat = new LineAndPointFormatter(
                Color.rgb(0, 200, 0),                   //line color
                Color.rgb(0, 100, 0),                   //point color
                null,                                   //fill color (none)
                new PointLabelFormatter(Color.WHITE));  //text color

        //add a new series to the xy plot
        mySimpleXYPlot.addSeries(series, seriesFormat);

        //reduce the number of range labels
//        mySimpleXYPlot.setTicksPerRangeLabel(3);
//        mySimpleXYPlot.setTicksPerDomainLabel(3);
    }
}
