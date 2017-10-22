package com.example.user.matapp;

        import android.app.AlertDialog;
        import android.content.ComponentName;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.ServiceConnection;
        import android.graphics.Color;
        import android.os.Handler;
        import android.os.IBinder;
        import android.os.Message;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.SwipeDismissBehavior;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.Toolbar;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.github.mikephil.charting.charts.LineChart;
        import com.github.mikephil.charting.components.Legend;
        import com.github.mikephil.charting.components.LimitLine;
        import com.github.mikephil.charting.components.XAxis;
        import com.github.mikephil.charting.components.YAxis;
        import com.github.mikephil.charting.data.Entry;
        import com.github.mikephil.charting.data.LineData;
        import com.github.mikephil.charting.data.LineDataSet;
        import com.github.mikephil.charting.listener.OnChartGestureListener;
        import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
        import com.github.mikephil.charting.utils.ColorTemplate;

        import java.util.ArrayList;
        import java.util.List;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import butterknife.OnClick;
        import io.realm.Realm;
        import io.realm.RealmConfiguration;

public class Statistika extends AppCompatActivity {
//    ArrayList<DataNgawur> dataObjects = new ArrayList<>();
private LineChart mChart;


//keperluan timer
    public static final String REALM_NAME = "FastBeer";
    public static Handler sHandler;
    private int secs = 0;
    private int mins = 0;
    private int millis = 0;
    private long currentTime = 0L;
    private boolean isBound = false;
    private boolean isRuning = false;
    private MyService myService;
    private Intent mIntent;
    private Realm mRealm;
    private AlertDialog.Builder mDialogBuilder;
    @BindView(R.id.timer)
    TextView time;
    @BindView(R.id.fab_playPause)
    FloatingActionButton fabPlayPause;




    //keperluan popup
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;


    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistika);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        myService = new MyService();
        ButterKnife.bind(this);

        mRealm = Realm.getInstance(new RealmConfiguration.Builder(this)
                .name(Statistika.REALM_NAME)
                .build());

        mIntent = new Intent(this, MyService.class);
        startService(mIntent);
        bindService(mIntent, myConnection, Context.BIND_AUTO_CREATE);

        Statistika.sHandler = new Handler() {

            @Override
            public void handleMessage(Message timeMsg) {
                super.handleMessage(timeMsg);
                currentTime = Long.valueOf(timeMsg.obj.toString());
                secs = (int) (currentTime / 1000);
                mins = secs / 60;
                secs = secs % 60;
                millis = (int) (currentTime % 1000);
                setTime();
            }
        };

        if(KelasBerisiVariable.flag == 0){
            KelasBerisiVariable.flag++;
            myService.startStopTanpaTombol();
        }


//        LineChart grafik = (LineChart) findViewById(R.id.GrafikGaris);

//        DataNgawur[] dataObjects = new DataNgawur[10];
//        for (int i = 0; i < 10; i++) {
//            dataObjects[i] = new DataNgawur();
//            dataObjects[i].X =   Math.random();
//            dataObjects[i].Y =  Math.random();
//        }
//
////        ngisiDataKuy();
//
//        List<Entry> entries = new ArrayList<Entry>();
//
//        for (DataNgawur data : dataObjects) {
//            // turn your data into Entry objects
//            entries.add(new Entry((float)data.getX(), (float)data.getY()));
//        }
//
//        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
//        dataSet.setColor(R.color.waranku);
////        dataSet.setDrawCubic(true);
//        dataSet.setDrawCircles(true);
//
//        dataSet.setValueTextColor(R.color.putih);
//
//        LineData lineData = new LineData(dataSet);
//        grafik.setData(lineData);
//        grafik.invalidate(); // refresh


        mChart = (LineChart) findViewById(R.id.GrafikGaris);
//ini adalah linechartactivity 1
//        // no description text
//        mChart.getDescription().setEnabled(false);
//
//        // enable touch gestures
//        mChart.setTouchEnabled(true);
//
//        mChart.setDragDecelerationFrictionCoef(0.9f);
//
//        // enable scaling and dragging
//        mChart.setDragEnabled(true);
//        mChart.setScaleEnabled(true);
//        mChart.setDrawGridBackground(false);
//        mChart.setHighlightPerDragEnabled(true);
//
//        // if disabled, scaling can be done on x- and y-axis separately
//        mChart.setPinchZoom(true);
//
//        // set an alternative background color
////        mChart.setBackgroundColor(R.color.waranku));
//
//        // add data
//        setData(20, 30);
//
//        mChart.animateX(2500);
//
//        // get the legend (only possible after setting data)
//        Legend l = mChart.getLegend();
//
//        // modify the legend ...
//        l.setForm(Legend.LegendForm.LINE);
////        l.setTypeface(mTfLight);
//        l.setTextSize(11f);
//        l.setTextColor(R.color.putih);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
////        l.setYOffset(11f);
//
//        XAxis xAxis = mChart.getXAxis();
////        xAxis.setTypeface(mTfLight);
//        xAxis.setTextSize(11f);
//        xAxis.setTextColor(R.color.putih);
//        xAxis.setDrawGridLines(false);
//        xAxis.setDrawAxisLine(false);
//
//        YAxis leftAxis = mChart.getAxisLeft();
////        leftAxis.setTypeface(mTfLight);
//        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
//        leftAxis.setAxisMaximum(200f);
//        leftAxis.setAxisMinimum(0f);
//        leftAxis.setDrawGridLines(true);
//        leftAxis.setGranularityEnabled(true);
//
//        YAxis rightAxis = mChart.getAxisRight();
////        rightAxis.setTypeface(mTfLight);
//        rightAxis.setTextColor(R.color.waranku);
//        rightAxis.setAxisMaximum(900);
//        rightAxis.setAxisMinimum(-200);
//        rightAxis.setDrawGridLines(false);
//        rightAxis.setDrawZeroLine(false);
//        rightAxis.setGranularityEnabled(false);




//        mChart.setOnChartGestureListener((OnChartGestureListener) this);
//        mChart.setOnChartValueSelectedListener((OnChartValueSelectedListener) this);
        mChart.setDrawGridBackground(false);

        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        // mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        // mChart.setBackgroundColor(Color.GRAY);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
//        MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);
//        mv.setChartView(mChart); // For bounds control
//        mChart.setMarker(mv); // Set the marker to the chart

        // x-axis limit line
        LimitLine llXAxis = new LimitLine(10f, "Index 10");
        llXAxis.setLineWidth(4f);
        llXAxis.enableDashedLine(10f, 10f, 0f);
        llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        llXAxis.setTextSize(10f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        //xAxis.setValueFormatter(new MyCustomXAxisValueFormatter());
        //xAxis.addLimitLine(llXAxis); // add x-axis limit line


//        Typeface tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        LimitLine ll1 = new LimitLine(150f, "Upper Limit");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);
//        ll1.setTypeface(tf);

        LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);
//        ll2.setTypeface(tf);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaximum(200f);
        leftAxis.setAxisMinimum(-50f);
        //leftAxis.setYOffset(20f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);

        // limit lines are drawn behind data (and not on top)
        leftAxis.setDrawLimitLinesBehindData(true);

        mChart.getAxisRight().setEnabled(false);

        //mChart.getViewPortHandler().setMaximumScaleY(2f);
        //mChart.getViewPortHandler().setMaximumScaleX(2f);

        // add data
        setData(45, 100);

//        mChart.setVisibleXRange(20);
//        mChart.setVisibleYRange(20f, AxisDependency.LEFT);
//        mChart.centerViewTo(20, 50, AxisDependency.LEFT);

        mChart.animateX(2500);
        //mChart.invalidate();

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);









    }


//
//
//    public void ngisiDataKuy(){
//        dataObjects.add(new DataNgawur(0.99998750002660041,0.99995000041890042));
//        dataObjects.add(new DataNgawur(0.99995000041890042,0.99988750211438813));
//        dataObjects.add(new DataNgawur(0.99988750211438813,0.99980000667551784));
//        dataObjects.add(new DataNgawur(0.99980000667551784,0.99968751626639035));
//        dataObjects.add(new DataNgawur(0.99968751626639035,0.99955003371323016));
//        dataObjects.add(new DataNgawur(0.99955003371323016,0.99938756251827427));
//        dataObjects.add(new DataNgawur(0.99938756251827427,0.99920010669673128));
//        dataObjects.add(new DataNgawur(0.99920010669673128,0.99898767093498489));
//        dataObjects.add(new DataNgawur(0.99898767093498489,0.998750260543915810));
//        dataObjects.add(new DataNgawur(0.998750260543915810,0.998487881458769111));
//        dataObjects.add(new DataNgawur(0.998487881458769111,0.998200540239005512));
//        dataObjects.add(new DataNgawur(0.998200540239005512,0.99788824382616413));
//        dataObjects.add(new DataNgawur(0.99788824382616413,0.99755100023243514));
//        dataObjects.add(new DataNgawur(0.99755100023243514,0.997188817888899515));
//        dataObjects.add(new DataNgawur(0.997188817888899515,0.996801705850107116));
//        dataObjects.add(new DataNgawur(0.996801705850107116,0.99638967379384917));
////        dataObjects.add(new DataNgawur(0.99638967379384917,0.99595273202091618));
////        dataObjects.add(new DataNgawur(0.99595273202091618,0.995490891454841719));
////        dataObjects.add(new DataNgawur(0.995490891454841719,0.995004163641628520));
////        dataObjects.add(new DataNgawur(0.995004163641628520,0.994492560749459121));
////        dataObjects.add(new DataNgawur(0.994492560749459121,0.993956095568393422));
////        dataObjects.add(new DataNgawur(0.993956095568393422,0.993394781510046923));
////        dataObjects.add(new DataNgawur(0.993394781510046923,0.992808632607257324));
////        dataObjects.add(new DataNgawur(0.992808632607257324,0.992197663513732125));
////        dataObjects.add(new DataNgawur(0.992197663513732125,0.99156189046953326));
////        dataObjects.add(new DataNgawur(0.99156189046953326,0.990901328476997227));
////        dataObjects.add(new DataNgawur(0.990901328476997227,0.990215994050108428));
////        dataObjects.add(new DataNgawur(0.990215994050108428,0.989505904322159129));
////        dataObjects.add(new DataNgawur(0.989505904322159129,0.988771077045321630));
////        dataObjects.add(new DataNgawur(0.988771077045321630,0.988011530590204431));
////        dataObjects.add(new DataNgawur(0.988011530590204431,0.987227283945393332));
////        dataObjects.add(new DataNgawur(0.987227283945393332,0.98641835671697633));
////        dataObjects.add(new DataNgawur(0.98641835671697633,0.985584769128052534));
////        dataObjects.add(new DataNgawur(0.985584769128052534,0.984726542018229435));
////        dataObjects.add(new DataNgawur(0.984726542018229435,0.983843696843098936));
////        dataObjects.add(new DataNgawur(0.983843696843098936,0.982936255673702237));
////        dataObjects.add(new DataNgawur(0.982936255673702237,0.982004241195978238));
////        dataObjects.add(new DataNgawur(0.982004241195978238,0.981047676710195539));
////        dataObjects.add(new DataNgawur(0.981047676710195539,0.980066586130371240));
////        dataObjects.add(new DataNgawur(0.980066586130371240,0.979060993983671641));
////        dataObjects.add(new DataNgawur(0.979060993983671641,0.978030925409800342));
////        dataObjects.add(new DataNgawur(0.978030925409800342,0.976976406160368643));
////        dataObjects.add(new DataNgawur(0.976976406160368643,0.975897462598252844));
////        dataObjects.add(new DataNgawur(0.975897462598252844,0.974794121696934245));
////        dataObjects.add(new DataNgawur(0.974794121696934245,0.973666411039825246));
////        dataObjects.add(new DataNgawur(0.973666411039825246,0.972514358819579847));
////        dataObjects.add(new DataNgawur(0.972514358819579847,0.971337993837388548));
////        dataObjects.add(new DataNgawur(0.971337993837388548,0.970137345502258649));
////        dataObjects.add(new DataNgawur(0.970137345502258649,0.968912443830278550));
//
//
//    }


    private void setData(int count, float range) {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = range / 2f;
            float val = (float) (Math.random() * mult) + 50;
            yVals1.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        for (int i = 0; i < count-1; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + 450;
            yVals2.add(new Entry(i, val));
//            if(i == 10) {
//                yVals2.add(new Entry(i, val + 50));
//            }
        }

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + 500;
            yVals3.add(new Entry(i, val));
        }

        LineDataSet set1, set2, set3;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, "DataSet 1");

            set1.setAxisDependency(YAxis.AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(R.color.putih);
            set1.setLineWidth(2f);
            set1.setCircleRadius(3f);
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
//            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);

            // create a dataset and give it a type
            set2 = new LineDataSet(yVals2, "DataSet 2");
            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.WHITE);
            set2.setLineWidth(2f);
            set2.setCircleRadius(3f);
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setHighLightColor(Color.rgb(244, 117, 117));
            //set2.setFillFormatter(new MyFillFormatter(900f));

            set3 = new LineDataSet(yVals3, "DataSet 3");
            set3.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set3.setColor(Color.YELLOW);
            set3.setCircleColor(Color.WHITE);
            set3.setLineWidth(2f);
            set3.setCircleRadius(3f);
            set3.setFillAlpha(65);
            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
            set3.setDrawCircleHole(false);
            set3.setHighLightColor(Color.rgb(244, 117, 117));

            // create a data object with the datasets
            LineData data = new LineData(set1, set2, set3);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);

            // set data
            mChart.setData(data);
        }
    }

















    @OnClick(R.id.fab_playPause)
    public void playPause() {

        myService.startStop();
        if(!isRuning){
            fabPlayPause.setImageResource(R.drawable.ic_menu_share);
            isRuning = true;
        } else if(isRuning){
            fabPlayPause.setImageResource(R.drawable.coins);
            isRuning = false;
        }

    }



    @OnClick(R.id.fab_reset)
    public void reset() {
        myService.reset();
        mins = 0;
        secs = 0;
        millis = 0;
        setTime();
        isRuning=false;
        DialogForm();
    }




    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void setTime() {
        time.setText("" + mins + ":" + String.format("%02d", secs) + ":"
                + String.format("%03d", millis));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }




    private void DialogForm() {
        dialog = new AlertDialog.Builder(Statistika.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.popup_durasi, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Toast.makeText(getApplicationContext(), "percobaan", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void pencet(View v){
        Toast.makeText(getApplicationContext(), "percobaan", Toast.LENGTH_SHORT).show();

    }






}
