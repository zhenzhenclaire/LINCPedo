package com.claire.pedometer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.renderscript.Type;
import android.util.Log;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;
import com.github.mikephil.charting.utils.Legend.LegendPosition;

public class MainActivity extends com.claire.paragraph.DemoBase implements OnSeekBarChangeListener,OnChartValueSelectedListener  {

	 private PieChart mChart;
	 private LineChart nChart;
	 private TextView statisticsTextView;
	 
	 
	 HashMap<String, Integer> today = new HashMap<String, Integer>();
	 ArrayList<Integer> allDayArrayList = new ArrayList<Integer>();
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        setContentView(R.layout.activity_main);
	        
	        mChart = (PieChart) findViewById(R.id.chart1);
	        nChart = (LineChart) findViewById(R.id.chart2);
	        statisticsTextView = (TextView) findViewById(R.id.Statistics);
	        
	        today.put("Walk", 30);
	        today.put("Run", 100);
	        today.put("Bicycle", 80);
	        setPieData(today);
	        paintPiechart(today);

	        statisticsTextView.setText("Walk:" + today.get("Walk") + "Run:" + today.get("Run") + "Bicycle" + today.get("Bicycle"));

//	        Random random = new Random();
//	        for(int i = 1;i <=24; i++){
//	        	allDayArrayList.add(random.nextInt(101));
//	        }
//	        Log.v("AllDayArrayList", allDayArrayList.toString());
//	        
//	        setLineData(allDayArrayList);
//	        paintCubicChart(allDayArrayList);
	    }
	    
	    
	    /**
	     * Paint PieChart 
	     */
	    public void paintPiechart(HashMap<String, Integer> today){
	        
	        //mChart = (PieChart) findViewById(R.id.chart1);

	        // change the color of the center-hole
	        mChart.setHoleColor(Color.rgb(235, 235, 235));

	       // Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

	       // mChart.setValueTypeface(tf);
	       // mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));

	        mChart.setHoleRadius(60f);

	        mChart.setDescription("");

	        mChart.setDrawYValues(true);
	        mChart.setDrawCenterText(true);

	        mChart.setDrawHoleEnabled(true);

	        mChart.setRotationAngle(0);

	        // draws the corresponding description value into the slice
	        mChart.setDrawXValues(true);

	        // enable rotation of the chart by touch
	        mChart.setRotationEnabled(true);

	        // display percentage values
	        mChart.setUsePercentValues(true);
	        // mChart.setUnit(" €");
	        // mChart.setDrawUnitsInChart(true);

	        // add a selection listener
	        mChart.setOnChartValueSelectedListener(this);
	        // mChart.setTouchEnabled(false);

	        mChart.setCenterText("Today");

	      
	        mChart.animateXY(1500, 1500);
	        // mChart.spin(2000, 0, 360);

	        Legend l = mChart.getLegend();
	        l.setPosition(LegendPosition.RIGHT_OF_CHART);
	        l.setXEntrySpace(7f);
	        l.setYEntrySpace(5f);
	        
	    }

	    
	    /**
	     * Paint cubic chart
	     */
	    public void paintCubicChart(ArrayList<Integer> allday){
	    	
	    	// if enabled, the chart will always start at zero on the y-axis
	        nChart.setStartAtZero(true);

	        // disable the drawing of values into the chart
	        nChart.setDrawYValues(false);

	        nChart.setDrawBorder(false);
	        
	        nChart.setDrawLegend(false);

	        // no description text
	        nChart.setDescription("");
	        nChart.setUnit(" $");

	        // enable value highlighting
	        nChart.setHighlightEnabled(true);

	        // enable touch gestures
	        nChart.setTouchEnabled(true);

	        // enable scaling and dragging
	        nChart.setDragEnabled(true);
	        nChart.setScaleEnabled(true);

	        // if disabled, scaling can be done on x- and y-axis separately
	        nChart.setPinchZoom(false);

	        nChart.setDrawGridBackground(false);
	        nChart.setDrawVerticalGrid(false);
	        
	        XLabels x = nChart.getXLabels();
	
	        YLabels y = nChart.getYLabels();

	        y.setLabelCount(5);

//	        // add data
//	        setData(45, 100);
	        
	        nChart.animateXY(2000, 2000);

	        // dont forget to refresh the drawing
	        nChart.invalidate();
	    }
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.pie, menu);
	        return true;
	    }
	    
	    /**
	     * Set data for cubic line chart
	     * @param today
	     */
	    public void setLineData(ArrayList<Integer> allDay) {

//	        ArrayList<String> xVals = new ArrayList<String>();
//	        for (int i = 1; i <= 24; i++) {
//	            xVals.add((i) + "");
//	        }
//
//	        ArrayList<Entry> vals1 = new ArrayList<Entry>();
//
//	        for (int i = 1; i <= 24; i++) {
//	            vals1.add(new Entry(allDay.get(i-1), i));
//	        }
//	        Log.v("vals1",vals1.toString());
	    	int count = 45;
	    	int range = 100;
	    	 ArrayList<String> xVals = new ArrayList<String>();
	         for (int i = 0; i < count; i++) {
	             xVals.add((1990 +i) + "");
	         }

	         ArrayList<Entry> vals1 = new ArrayList<Entry>();

	         for (int i = 0; i < count; i++) {
	             float mult = (range + 1);
	             float val = (float) (Math.random() * mult) + 20;// + (float)
	                                                            // ((mult *
	                                                            // 0.1) / 10);
	             vals1.add(new Entry(val, i));
	         }
	        
	         // create a dataset and give it a type
	         LineDataSet set1 = new LineDataSet(vals1, "DataSet 1");
	         set1.setDrawCubic(true);
	         set1.setCubicIntensity(0.2f);
	         set1.setDrawFilled(true);
	         set1.setDrawCircles(false); 
	         set1.setLineWidth(2f);
	         set1.setCircleSize(5f);
	         set1.setHighLightColor(Color.rgb(244, 117, 117));
	         set1.setColor(Color.rgb(104, 241, 175));

	         ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
	         dataSets.add(set1);

	         // create a data object with the datasets
	         LineData data = new LineData(xVals, dataSets);

	         // set data
	         nChart.setData(data);
	    }

	    /**
	     * Set Pie Chart data set
	     * @param today 
	     */
	    public void setPieData(HashMap<String, Integer> today){ 
	    	ArrayList<Entry> valueList = new ArrayList<Entry>();
	    	 ArrayList<String> typeList= new ArrayList<String>();
	    	 int i = 1;
	    	 Iterator iter = today.entrySet().iterator();
	    	 while (iter.hasNext()) {
		    	Map.Entry entry = (Map.Entry) iter.next();
		    	Log.v("key", (String) entry.getKey());
		    	Log.v("value",entry.getValue()+"");
		    	typeList.add( (String) entry.getKey());
		    	valueList.add(new Entry((Integer) entry.getValue(), i++));
		    	
	    	 }
	    	 
	    	 PieDataSet todayDataSet = new PieDataSet(valueList, "Today Sport"); 
	    	 todayDataSet.setSliceSpace(3f);
	    	 
	    	 // add a lot of colors

		        ArrayList<Integer> colors = new ArrayList<Integer>();

		        for (int c : ColorTemplate.VORDIPLOM_COLORS)
		            colors.add(c);

		        for (int c : ColorTemplate.JOYFUL_COLORS)
		            colors.add(c);

		        for (int c : ColorTemplate.COLORFUL_COLORS)
		            colors.add(c);

		        for (int c : ColorTemplate.LIBERTY_COLORS)
		            colors.add(c);
		        
		        for (int c : ColorTemplate.PASTEL_COLORS)
		            colors.add(c);
		        
		        colors.add(ColorTemplate.getHoloBlue());

		        todayDataSet.setColors(colors);
	
		        PieData data = new PieData(typeList, todayDataSet);
		        Log.v("typelist",typeList.size()+"");
		        Log.v("todayDataset",todayDataSet.toString());
			    Log.v("PieData",data.toString());
			    
		        mChart.setData(data);
		        Log.v("mChart", mChart.toString());

		        // undo all highlights
		        mChart.highlightValues(null);

		        mChart.invalidate();
	    }
	    
	    @Override
	    public void onValueSelected(Entry e, int dataSetIndex) {

	        if (e == null)
	            return;
	        Log.i("VAL SELECTED",
	                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
	                        + ", DataSet index: " + dataSetIndex);
	    }

	    @Override
	    public void onNothingSelected() {
	        Log.i("PieChart", "nothing selected");
	    }

	    @Override
	    public void onStartTrackingTouch(SeekBar seekBar) {
	        // TODO Auto-generated method stub

	    }

	    @Override
	    public void onStopTrackingTouch(SeekBar seekBar) {
	        // TODO Auto-generated method stub

	    }

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			// TODO Auto-generated method stub
			
		}

		

	    // private void removeLastEntry() {
	    //
	    // PieData data = mChart.getDataOriginal();
	    //
	    // if (data != null) {
	    //
	    // PieDataSet set = data.getDataSet();
	    //
	    // if (set != null) {
	    //
	    // Entry e = set.getEntryForXIndex(set.getEntryCount() - 1);
	    //
	    // data.removeEntry(e, 0);
	    // // or remove by index
	    // // mData.removeEntry(xIndex, dataSetIndex);
	    //
	    // mChart.notifyDataSetChanged();
	    // mChart.invalidate();
	    // }
	    // }
	    // }
	    //
	    // private void addEntry() {
	    //
	    // PieData data = mChart.getDataOriginal();
	    //
	    // if (data != null) {
	    //
	    // PieDataSet set = data.getDataSet();
	    // // set.addEntry(...);
	    //
	    // data.addEntry(new Entry((float) (Math.random() * 25) + 20f,
	    // set.getEntryCount()), 0);
	    //
	    // // let the chart know it's data has changed
	    // mChart.notifyDataSetChanged();
	    //
	    // // redraw the chart
	    // mChart.invalidate();
	    // }
	    // }
	

}
