package javis.wearsyncservice;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Arrays;

public class DashboardWeek extends SlidingMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.dashboard_week);

        if (savedInstanceState!=null)
        {
            savedInstanceState.putString("TITLE", "DASHBOARD");
            savedInstanceState.putInt("LAYOUT", R.layout.dashboard_week);
            savedInstanceState.putInt("LAYOUT_ID", R.id.dashboard_week_rel);
            super.onCreate(savedInstanceState);
        }
        else
        {
            Bundle b = new Bundle();
            b.putString("TITLE", "DASHBOARD");
            b.putInt("LAYOUT", R.layout.dashboard_week);
            b.putInt("LAYOUT_ID", R.id.dashboard_week_rel); //id of top level Relative/Linear etc Layout
            super.onCreate(b);
        }

        Bitmap bmp = getImageBitmap(this, "profile","BMP");;
        ImageView img = (ImageView) findViewById(R.id.cropped_final);
        img.setImageDrawable(new RoundedAvatarDrawable(bmp));

        //this is beginning of plot stuff
        XYPlot mySimpleXYPlot;

        //initialized plot reference
        mySimpleXYPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
//        mySimpleXYPlot.setBorderStyle(Plot.BorderStyle.NONE, null, null); //makes legend white instead of gray
        mySimpleXYPlot.setPlotMargins(0, 0, 0, 0);
        mySimpleXYPlot.setPlotPadding(0, 0, 0, 0);
        mySimpleXYPlot.setGridPadding(0, 10, 5, 0);
        mySimpleXYPlot.setBackgroundColor(getResources().getColor(R.color.purple)); //doesn't seem to work


        //create array of y-values to plot
        Number[] numbers = {20, 50, 80, 60, 75, 80, 90}; //index corresponds to x-value
        //figure out how to write to memory

        mySimpleXYPlot.getGraphWidget().getBackgroundPaint().setColor(Color.rgb(155, 24, 155));// sets axis colors
        mySimpleXYPlot.getGraphWidget().getGridBackgroundPaint().setColor(Color.rgb(155, 24, 155)); //sets the graph background color

        // Domain
        mySimpleXYPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, numbers.length);
        mySimpleXYPlot.setDomainValueFormat(new DecimalFormat("0"));
        mySimpleXYPlot.setDomainStepValue(1);

        //Range
        mySimpleXYPlot.setRangeBoundaries(0, 100, BoundaryMode.FIXED);
        mySimpleXYPlot.setRangeStep(XYStepMode.INCREMENT_BY_VAL, numbers.length);
        mySimpleXYPlot.setRangeStepValue(10);
        //mySimpleXYPlot.setRangeStep(XYStepMode.SUBDIVIDE, values.length);
        mySimpleXYPlot.setRangeValueFormat(new DecimalFormat("0"));

        //Remove legend
        mySimpleXYPlot.getLayoutManager().remove(mySimpleXYPlot.getLegendWidget());
//        mySimpleXYPlot.getLayoutManager().remove(mySimpleXYPlot.getDomainLabelWidget());
//        mySimpleXYPlot.getLayoutManager().remove(mySimpleXYPlot.getRangeLabelWidget());
//        mySimpleXYPlot.getLayoutManager().remove(mySimpleXYPlot.getTitleWidget());

        // Turn the above arrays into XYSeries':
//        XYSeries series1 = new SimpleXYSeries(
//                Arrays.asList(days),
//                Arrays.asList(numbers),
//                "Series1");                             // Set the display title of the series

        //turn above numbers to XYSeries
        XYSeries series = new SimpleXYSeries(Arrays.asList(numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series");

        //create a formatter to use for drawing a series using LineAndPointRenderer

        LineAndPointFormatter seriesFormat = new LineAndPointFormatter(
                Color.WHITE,                   //line color
                Color.WHITE,                   //point color
                null,                                   //fill color (none)
                null);  //text color

        //add a new series to the xy plot
        mySimpleXYPlot.addSeries(series, seriesFormat);

        //customize x-axis labels
        mySimpleXYPlot.getGraphWidget().setDomainValueFormat(new GraphXLabelFormat());

        //remove grid lines (this code from a google groups conversation, by Sergii Nezdolii)
        mySimpleXYPlot.getGraphWidget().getDomainGridLinePaint().setColor(Color.TRANSPARENT);
        mySimpleXYPlot.getGraphWidget().getRangeGridLinePaint().setColor(Color.TRANSPARENT);





        //reduce the number of range labels
//        mySimpleXYPlot.setTicksPerRangeLabel(3);
//        mySimpleXYPlot.setTicksPerDomainLabel(3);
        //this is end of plot stuff

    }

    //this code gotten from stackOverflow user Aegonis

    class GraphXLabelFormat extends Format {

        String[] LABELS = {"Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat"}; //set these to be days

        @Override
        public StringBuffer format(Object object, StringBuffer buffer, FieldPosition field) {
            int parsedInt = Math.round(Float.parseFloat(object.toString()));
            String labelString = LABELS[parsedInt];
            buffer.append(labelString);
            return buffer;
        }

        @Override
        public Object parseObject(String string, ParsePosition position) {
            return java.util.Arrays.asList(LABELS).indexOf(string);
        }
    }

    public Bitmap getImageBitmap(Context context,String name,String extension){
        name=name+"."+extension;
        try{
            FileInputStream fis = context.openFileInput(name);
            Bitmap b = BitmapFactory.decodeStream(fis);
            fis.close();
            return b;
        }
        catch(Exception e){
        }
        //return null;
        //If there is no selected image displays the fox icon
        Bitmap Icon = BitmapFactory.decodeResource(getResources(), R.drawable.foxicon);
        return Icon;
    }
}
