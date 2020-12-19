package com.example.mais;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.mais.model.User;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class PsikoTropicActivity extends AppCompatActivity {

    private User user;
    private BarChart lineChart;
    private BarDataSet lineDataSet;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psiko_tropic);

        Bundle bundle = getIntent().getExtras();
        user = (User) bundle.get("user");

        lineChart = findViewById(R.id.lineChart);
        listView = findViewById(R.id.listView);

        // Creamos un set de datos
        ArrayList<BarEntry> lineEntries = new ArrayList<BarEntry>();
        for (int i = 0; i<11; i++){
            float y = (int) (Math.random() * 8) + 1;
            lineEntries.add(new BarEntry((float) i,(float)y));
        }

        // Unimos los datos al data set
        lineDataSet = new BarDataSet(lineEntries,"Cells");

        // Asociamos al gráfico
        BarData lineData = new BarData();
        lineData.addDataSet(lineDataSet);
        lineChart.setData(lineData);

    }
}
