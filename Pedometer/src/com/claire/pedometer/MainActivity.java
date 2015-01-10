package com.claire.pedometer;

import java.util.ArrayList;
import java.util.HashMap;
<<<<<<< HEAD
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
	

=======
import java.util.Locale;

import com.baidu.mapapi.SDKInitializer;
import com.claire.adapter.MenuAdapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.claire.pedometer.*;
import com.claire.statistics.MileActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;



public class MainActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    private MenuAdapter mMenuAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext()); 
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        
        mMenuAdapter = new MenuAdapter(this);
        
        mDrawerList.setAdapter(mMenuAdapter);
        
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
       /* case R.id.action_websearch:
            // create intent to perform web search for this planet
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "", Toast.LENGTH_LONG).show();
            }
            return true;*/
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /* ç›‘å�¬è�œå�•listview */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.menu_content)[i];
            String title = getResources().getStringArray(R.array.planets_array)[i];
            
            int layoutid = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                            "layout", getActivity().getPackageName());
            View rootView = inflater.inflate(layoutid, container, false);
            Typeface tf = Typeface.createFromAsset(getResources().getAssets(), "monog.ttf");
            
            switch (title) {
			case "HOME":
				HomeActivity homeActivity = new HomeActivity();
		        homeActivity.init(rootView,tf);
				break;
			case "SPORTS INFO":
				MileActivity mileActivity = new MileActivity();
	            mileActivity.init(rootView, tf);
				break;
			case "FIND FRIENDS":
				BaiduMapActivity mapActivity = new BaiduMapActivity();
				mapActivity.init(rootView, tf);
				break;
			default:
				break;
			}
            
            
            getActivity().setTitle(title);
            return rootView;
        	
//        	 View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
//             int i = getArguments().getInt(ARG_PLANET_NUMBER);
//             String planet = getResources().getStringArray(R.array.planets_array)[i];
//
//             int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
//                             "drawable", getActivity().getPackageName());
//             ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
//             getActivity().setTitle(planet);
//             return rootView;
        }
    }
>>>>>>> origin/master
}
