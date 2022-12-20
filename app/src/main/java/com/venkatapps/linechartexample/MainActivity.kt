package com.venkatapps.linechartexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class MainActivity : AppCompatActivity() {
    private lateinit var lineChart : LineChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lineChart = findViewById(R.id.line_chart)
        setUpLineChart()
        setDataToLineChart()
    }

    private fun setUpLineChart() {
        with(lineChart) {
            description.isEnabled = false
            xAxis.setDrawGridLines(false)
            xAxis.isEnabled = false
            axisRight.isEnabled = false
            axisLeft.isEnabled = false
            legend.isEnabled = false
        }
    }

    private fun week1(): ArrayList<Entry> {
        val sales = ArrayList<Entry>()
        sales.add(Entry(0f, 15f))
        sales.add(Entry(1f, 16f))
        sales.add(Entry(2f, 13f))
        sales.add(Entry(3f, 22f))
        sales.add(Entry(4f, 20f))
        return sales
    }

    /*private fun week2(): ArrayList<Entry> {
        val sales = ArrayList<Entry>()
        sales.add(Entry(0f, 11f))
        sales.add(Entry(1f, 13f))
        sales.add(Entry(2f, 18f))
        sales.add(Entry(3f, 16f))
        sales.add(Entry(4f, 22f))
        return sales
    }*/

    private fun setDataToLineChart() {

        val weekOneSales = LineDataSet(week1(),"")
        weekOneSales.lineWidth = 3f
        weekOneSales.setDrawValues(false)
        weekOneSales.mode = LineDataSet.Mode.LINEAR
        weekOneSales.setDrawCircles(false)
        weekOneSales.setDrawHorizontalHighlightIndicator(false)
        weekOneSales.setDrawVerticalHighlightIndicator(false)

        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(weekOneSales)

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        lineChart.invalidate()
    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        private var items = arrayListOf("Milk", "Butter", "Cheese", "Ice cream", "Milkshake")

        override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
            val index = value.toInt()
            return if (index < items.size) {
                items[index]
            } else {
                null
            }
        }
    }
}