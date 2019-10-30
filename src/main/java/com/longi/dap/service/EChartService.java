package com.longi.dap.service;

import com.github.abel533.echarts.AxisPointer;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.Toolbox;
import com.github.abel533.echarts.Tooltip;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.feature.Feature;
import com.longi.dap.dao.InitDaoMapper;
import com.longi.dap.entity.CapacityEntity;
import com.longi.dap.entity.MarketShareEntity;
import com.longi.dap.tookits.ChartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class EChartService {

    @Autowired
    InitDaoMapper initDao;

    public Option getCapacityData(String typeName){
        List<CapacityEntity> capInfoList = initDao.getCapacityData(typeName);

        Option option = new Option();
        Tooltip tooltip = new Tooltip();
        tooltip.trigger(Trigger.axis);
        AxisPointer axisPointer = new AxisPointer();
        tooltip.axisPointer(axisPointer.type(PointerType.shadow));
        option.title("产能分析").tooltip(tooltip);
        List<String> legendList = new ArrayList<>();
        legendList.add("组件");
        legendList.add("电池");
        legendList.add("硅棒");
        legendList.add("硅片");
        option.legend().data(legendList);
        List<String> colorList = new ArrayList<>();
        colorList.add("#003366");
        colorList.add("#006699");
        colorList.add("##4cabce");
        colorList.add("#e5323e");
        option.color(colorList);
        Toolbox toolBox = new Toolbox();
        toolBox.show(true);
        toolBox.setOrient(Orient.vertical);
        toolBox.left(X.right);
        toolBox.top(Y.center);
        Feature feature = new Feature();
        Map<String,Feature> featrueMap = new HashMap<String,Feature>();
        toolBox.setFeature(featrueMap);
        option.toolbox(toolBox);

        return option;
    }

    /**
     * 获取市场份额信息
     * @return
     */
    public String getMarketShareData(){
        List<MarketShareEntity> mktInfoList = initDao.getMarketShareData();
        String dataStr = assmbleData(mktInfoList);
        return dataStr;
    }

    private String assmbleData(List<MarketShareEntity> mktInfoList){
        DecimalFormat df = new DecimalFormat("#.00");
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("{ \"color\": [ \"#e5323e\"],  ")
                .append(" \"tooltip\": { \"trigger\": \"axis\",\"axisPointer\": {  \"type\": \"shadow\" } }, ")
                .append("  \"grid\": {  \"left\": \"3%\", \"right\": \"3%\", \"bottom\": \"3%\", \"containLabel\": true  }, ")
                .append(" \"xAxis\" : [  { \"type\" : \"category\",  \"data\" : [  ");
        StringBuffer dataBuf = new StringBuffer();
        StringBuffer mDataBuf = new StringBuffer();
        for(MarketShareEntity mktInfo : mktInfoList){
            dataBuf.append("\"").append(mktInfo.getCountry()).append("\",");
            mDataBuf.append(Double.parseDouble(df.format(mktInfo.getRate() * 100))).append(",");
        }

        String nDataStr = dataBuf.substring(0,dataBuf.length()-1);
        strBuf.append(nDataStr);
        String mDataBStr = mDataBuf.substring(0,mDataBuf.length()-1);

        strBuf.append("], \" axisTick\": {  \"alignWithLabel\": true  },  ")
                .append(ChartUtil.setAxisLine("#87cefa"))
                .append(ChartUtil.setAxisLabel("0","40","13","#fff"))
                .append(" } ],");

        strBuf.append(" \"yAxis\" : [  { \"type\" : \"value\" ,    ")
                .append(ChartUtil.setAxisLine("#87cefa"))
                .append(ChartUtil.setAxisLabel(null,null,null,"#fff"))
                .append("}],")
                .append("  \"series\" : [  {  \"name\":\"占比(%)\",  \"type\":\"bar\",")
                .append("  \"barWidth\": \"30%\", \"data\":[ ").append(mDataBStr).append("] }] }");
        return strBuf.toString();
    }
}
