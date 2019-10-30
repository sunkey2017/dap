package com.longi.dap.service.impl;

import com.longi.dap.service.ShipmentsService;
import com.longi.dap.tookits.ChartUtil;
import com.longi.dap.tookits.StringBeginUtil;
import com.longi.dap.tookits.StringbreakUtil;
import com.longi.dap.tookits.UtilJson;
import message.oa.messageoapush.CxfClient;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.longi.dap.entity.Shipments;
import com.longi.dap.dao.ShipmentsMapper;

/**
 * @author jiangsida
 */
@Service
public class ShipmentsServiceImpl implements ShipmentsService {

    String companyname=null;

    String[] peopelName={"jiangsida","sunke5"};

    @Resource
    private ShipmentsMapper shipmentsMapper;

    @Override
    public int insert(Shipments shipments){
        return shipmentsMapper.insert(shipments);
    }

    @Override
    public int insertSelective(Shipments shipments){
        return shipmentsMapper.insertSelective(shipments);
    }

    @Override
    public int insertList(List<Shipments> shipmentss){
        return shipmentsMapper.insertList(shipmentss);
    }

    @Override
    public int updateByPrimaryKeySelective(Shipments shipments){
        return shipmentsMapper.updateByPrimaryKeySelective(shipments);
    }

    @Override
    public String selectAllCompany() {
        List<Shipments> shipments = shipmentsMapper.selectAllCompany();

        StringBuffer sb=new StringBuffer();
        sb.append("[");
        for (int i=0; i<shipments.size();i++){
            sb.append("\"");
            sb.append(shipments.get(i).getCompany()+"\",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return  sb.toString();

    }





    @Override
    public   String selectEurope(String company) {
        companyname = selectAllCompany();
        List<Shipments> list = shipmentsMapper.selectEurope(company,"17");
        String s = UtilJson.getJson(list, "\"2017\":[",companyname);
        List<Shipments> list2 = shipmentsMapper.selectEurope(company,"18");
        String json = UtilJson.getJson(list2, "\"2018\":[",companyname);
        return s+","+json;
    }

    @Override
    public String selectSinglecrystal( String singlecrystal) {

        companyname = selectAllCompany();
        List<Shipments> list = shipmentsMapper.selectSinglecrystal("17", singlecrystal);
        String json = UtilJson.getJson(list, "\"2017\":[",companyname);
        List<Shipments> list2= shipmentsMapper.selectSinglecrystal("18", singlecrystal);
        String jsons = UtilJson.getJson(list2, "\"2018\":[",companyname);
        return json+","+jsons;
    }

    @Override
    public String selectOrderMap() {
        StringBuffer sb=new StringBuffer();
       List<Map<String, Object>> selectOrderMap = shipmentsMapper.selectordermap();


        sb.append("{\"baseOption\": {\"timeline\":{\"axisType\": \"category\",\"show\": true,\"autoPlay\": true,\" playInterval\": 3000,\"data\":[");
        for (int i=0;i<selectOrderMap.size();i++){
            //noinspection AlibabaLowerCamelCaseVariableNaming
            String saleOrder = (String)selectOrderMap.get(i).get("sale_order");
            sb.append("\""+saleOrder+"订单命中率"+"\",");
            sb.append("\""+saleOrder+"订单完成率"+"\",");

        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("]}, \"tooltip\": {}},");
        sb.append("\"options\":[");

        for (int i=0; i<selectOrderMap.size();i++){
            sb.append("{\"series\": [{\"name\": \""+selectOrderMap.get(i).get("sale_order")+"订单命中率\",\"type\": \"gauge\",\"detail\": {\"formatter\":\"{value}%\"},\"data\": [{\"value\": "+selectOrderMap.get(i).get("hit_rate")+", \"name\":\""+selectOrderMap.get(i).get("sale_order")+"\"}]}]},");
            sb.append("{\"series\": [{\"name\": \""+selectOrderMap.get(i).get("sale_order")+"订单完成率\",\"type\": \"gauge\",\"detail\": {\"formatter\":\"{value}%\"},\"data\": [{\"value\": "+selectOrderMap.get(i).get("finished_rate")+", \"name\":\""+selectOrderMap.get(i).get("sale_order")+"\"}]}]},");
         }
        sb.delete(sb.length()-1,sb.length());
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public String selectWipInfo() {
        List<Map<String, Object>> wipList = shipmentsMapper.selectwip();
       StringBuffer sbAll=new StringBuffer();
        StringBuffer sbArea=new StringBuffer();
        StringBuffer sbData=new StringBuffer();
        sbAll.append("{");
        sbArea.append("\"area\":[");
        sbData.append("\"data\":[");
        for (int i=0;i<wipList.size();i++){
            Double value=(Double) wipList.get(i).get("value");
            Double l1=(Double) wipList.get(i).get("l1");
            Double l2=(Double) wipList.get(i).get("l2");
            String areName=(String)wipList.get(i).get("area");
            sbArea.append("\""+areName+"\",");
            if (value>l2){
                CxfClient.cl1("[L2"+areName+"alarm]西安组件工厂包装区超过L2报警线，实际值"+value+"，报警线"+l2,peopelName);
            }
            else if (value<l2&&value>l1){
                CxfClient.cl1("[L1"+areName+"alarm]西安组件工厂包装区超过L1报警线，实际值"+value+"，报警线"+l1,peopelName);
              }


            sbData.append(value+",");

        }
        sbData.delete(sbData.length()-1,sbData.length());
        sbArea.delete(sbArea.length()-1,sbArea.length());
        sbArea.append("]");
        sbData.append("],");
        sbAll.append(sbData.toString());
        sbAll.append(sbArea.toString());
        sbAll.append("}");

//        StringBuffer sb=new StringBuffer();
//        sb.append("{\"title\":{\"text\":\"各工序WIP\"},\"xAxis\":{\"type\":\"category\",\"data\":[");
//        for (int i=0;i<wiplist.size();i++){
//            sb.append("\"");
//           sb.append(wiplist.get(i).get("area"));
//            sb.append("\",");
//        }
//        sb.delete(sb.length()-1,sb.length());
//        sb.append("],");
//        sb.append(ChartUtil.setAxisLine("#87cefa"));
//        sb.append(ChartUtil.setAxisLabel("0","40","13","#fff"));
//        sb.append("},");
//        sb.append("\"yAxis\":{\"type\":\"value\" ,");
//        sb.append(ChartUtil.setAxisLine("#87cefa"));
//        sb.append(ChartUtil.setAxisLabel(null,null,null,"#fff"));
//        sb.append("},");
//        sb.append("\"series\":[{\"tooltip\":{},\"name\":\"直接访问\",\"type\":\"bar\",\"barWidth\":\"60%\",\"data\":[");
//        for (int i=0;i<wiplist.size();i++){
//             sb.append(wiplist.get(i).get("value")+",");
//
//        }
//        sb.delete(sb.length()-1,sb.length());
//        sb.append("],");
//        sb.append("\"itemStyle\":{");
//        sb.append("\"normal\":{\"color\":\"#87cefa\"}");
//        sb.append("}}");
//        sb.append("]");
//        sb.append("}");
        return sbAll.toString();
    }

    @Override
    public String selectSummaryOut() {
        List<Map<String, Object>> summaryout = shipmentsMapper.selectsummaryout();
        StringBuffer sb=new StringBuffer();
        sb.append("{\"title\" : {},\"legend\": {\"textStyle\" : { \"color\" : \"#ffffff\" }, \"data\":[\"月成本\",\"月产出\"]},\"toolbox\": {\"show\" : true,");
        sb.append("\"feature\" : {\"dataView\" : {\"show\": true, \"readOnly\": false},\"magicType\": {\"show\": true, ");
        sb.append("\"type\": [\"line\", \"bar\"]},\"restore\" : {\"show\": true},\"saveAsImag\" : {\"show\": true}}},\"calculable\" : true,");
        sb.append("\"xAxis\" : [{\"type\" : \"category\",\"data\" : [");

        for (int i=0;i<summaryout.size();i++){
            sb.append("\"");
            sb.append(summaryout.get(i).get("month"));
            sb.append("\",");
        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("], ");
        sb.append(ChartUtil.setAxisLine("#87cefa"));
        sb.append(ChartUtil.setAxisLabel("0","40","13","#fff"));
        sb.append(" }],");
        sb.append("\"yAxis\" : [{\"type\" : \"value\"," );
        sb.append(ChartUtil.setAxisLine("#87cefa"));
        sb.append(ChartUtil.setAxisLabel(null,null,null,"#fff"));
        sb.append("  }], \"series\" : [{\"name\":\"月成本\",\"type\":\"bar\",\"data\":[");
        for (int i=0;i<summaryout.size();i++){
            sb.append("\"");
            sb.append(summaryout.get(i).get("summary"));
            sb.append("\",");
        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("]},{\"name\":\"月产出\",\"type\":\"bar\",\"data\":[");
        for (int i=0;i<summaryout.size();i++){
            sb.append("\"");
            sb.append(summaryout.get(i).get("outsummary"));
            sb.append("\",");
        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("]}]}");

      return   sb.toString();

    }

    @Override
    public String weeklyngInfo() {
        StringBuffer sb=new StringBuffer();
        List<Map<String, Object>> list = shipmentsMapper.selectWeeklyNg();
        sb.append("[[");
        for (int i=0; i<list.size();i++){
            sb.append("[");
            Double ngCnt = (Double) list.get(i).get("ng_cnt");

            sb.append(i+",");
            sb.append(ngCnt);
            sb.append("],");
        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("],[");
        for (int i=0; i<list.size();i++){
             String ngType = (String)list.get(i).get("ng_type");
             sb.append("\""+ngType+"\",");

        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("]]");
        return sb.toString();
    }

    @Override
    public String alarmInfo() {
        StringBuffer sb=new StringBuffer();
        List<Map<String, Object>> list = shipmentsMapper.alarmInfo();
        sb.append("[[");
        for (int i=0; i<list.size();i++){
            sb.append("[");
            Double alarmCnt = (Double)list.get(i).get("alarm_cnt");
            sb.append(i+",");
            sb.append(alarmCnt);
            sb.append("],");
        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("],[");
        for (int i=0; i<list.size();i++){
            String eqp = (String)list.get(i).get("eqp");
            sb.append("\""+eqp+"\",");

        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("]]");
        return sb.toString();
    }

    @Override
    public String selectFormalSchooling() {
        StringBuffer sb=new StringBuffer();
        List<Map<String, Object>> list = shipmentsMapper.selectFormalSchooling();
        sb.append("{");
        sb.append("\"tooltip\": {\"trigger\": \"item\",\"formatter\": \"{a} <br/>{b}:({d}%)\"},");
        sb.append("\"legend\": {\"textStyle\" : { \"color\" : \"#ffffff\" },\"data\":[");
        for(int i=0;i<list.size();i++){
         String education= (String)list.get(i).get("education");
         sb.append("\""+education+"\",");
        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("]},");
        sb.append("\"series\": [{\"name\":\"学历占比\",\"type\":\"pie\",\"radius\": [\"50%\", \"70%\"],");
        sb.append("\"label\": {\"normal\": {\"fontSize\":13},\"emphasis\": {\"show\": true,\"textStyle\": {\"fontSize\": 30,\"fontWeight\": \"bold\"}}},\"data\":[");
        for(int i=0;i<list.size();i++){
            Long count= (Long) list.get(i).get("count");
            String education= (String)list.get(i).get("education");
            sb.append("{\"value\":"+count+",\"name\":\""+education+"\"},");
        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("]}]");
        sb.append("}");
        return sb.toString();
    }


    @Override
    public String selectEmpDetail() {

        StringBuffer sb=new StringBuffer();
        List<Map<String, Object>> list = shipmentsMapper.selectEmpDetail();
        sb.append("{");
        sb.append("\"tooltip\": {\"trigger\": \"item\",\"formatter\": \"{a} <br/>{b}:({d}%)\"},");
        sb.append("\"legend\": { \"textStyle\" : { \"color\" : \"#ffffff\" }, \"data\":[");
        sb.append("]},");
        sb.append("\"series\": [{\"name\":\"司龄占比\",\"type\":\"pie\",\"radius\": [\"50%\", \"70%\"],\"label\": {\"normal\": {\"formatter\":\"{b}年\",\"fontSize\":13},\"emphasis\": {\"show\": true,\"textStyle\": {\"fontSize\": 30,\"fontWeight\": \"bold\"}}},\"data\":[");
        for(int i=0;i<list.size();i++){
            Long count= (Long) list.get(i).get("count");
            Double age= (Double) list.get(i).get("age");
            sb.append("{\"value\":"+count.intValue()+",\"name\":"+age.intValue()+"},");
        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("]}]");
        sb.append("}");
        return sb.toString();

    }

    @Override
    public Map<String, String> getAllCompanyOut() {

        HashMap<String, String> allCompany = new HashMap<>();
        String pList = selectAllCompany();
         allCompany.put("pList",pList);
        String australiaJson = selectEurope("Australia");
        String europeJson = selectEurope("Europe");
        String americaJson = selectEurope("America");
        allCompany.put("australia",australiaJson);
        allCompany.put("europe",europeJson);
        allCompany.put("america",americaJson);
        String allCrystal= selectSinglecrystal("全部");
        String singleCrystal= selectSinglecrystal("单晶");
        allCompany.put("allCrystal",allCrystal);
        allCompany.put("singleCrystal",singleCrystal);


        return allCompany;
    }

    @Override
    public String tripBudget() {

        List<Map<String, Object>> list = shipmentsMapper.selectTripBudget();
        StringBuffer sbTravel=new StringBuffer();
        sbTravel.append("[");
        for(int i=0;i<list.size();i++){
            String businessType = (String)list.get(i).get("business_type");
            String type = (String)list.get(i).get("type");
            String level = (String)list.get(i).get("level");
            String payment = (String)list.get(i).get("payment");
            sbTravel.append("[[\'"+businessType+"\',"+payment+"],"+"[\'"+type+"\',"+level+"]],");
          }
        sbTravel.delete(sbTravel.length()-1,sbTravel.length());
        sbTravel.append("]");
        return  sbTravel.toString();
    }

    @Override
    public String selectAggregateAmount() {

        StringBuffer materielData= StringBeginUtil.StringBeginWithRarentheses();
        StringBuffer janData=StringBeginUtil.StringBeginWithRarentheses();
        StringBuffer febData=StringBeginUtil.StringBeginWithRarentheses();
        StringBuffer marData=StringBeginUtil.StringBeginWithRarentheses();
        StringBuffer aprData=StringBeginUtil.StringBeginWithRarentheses();
        StringBuffer totallData=StringBeginUtil.StringBeginWithRarentheses();
        StringBuffer sb=new StringBuffer();
         List<Map<String, Object>> listAggre = shipmentsMapper.selectAggregateAmount();
        List<Map<String, Object>> listByMonth = shipmentsMapper.selectAggregatebyMonth();
        for (int i=0;i<listByMonth.size();i++){
        if (i==0){
            String janMonth = (String)listByMonth.get(i).get("sumbymonth");
            janData.append(janMonth+"]");
        }
        else if (i==1){
            String febMonth = (String)listByMonth.get(i).get("sumbymonth");
            febData.append(febMonth+"]");
        }
        else if (i==2){
            String marMonth = (String)listByMonth.get(i).get("sumbymonth");
            marData.append(marMonth+"]");
        }

       else if (i==3){
        String aprMonth = (String)listByMonth.get(i).get("sumbymonth");
         aprData.append(aprMonth+"]");}
        }
        for (int i=0; i<listAggre.size();i++){
            String type = (String)listAggre.get(i).get("type");
            Double money = (Double)listAggre.get(i).get("money");
            totallData.append(""+money+",");
            materielData.append("\""+type+"\",");
        }
        totallData.delete(totallData.length()-1,totallData.length());
        materielData.delete(materielData.length()-1,materielData.length());
        totallData.append("]");
        materielData.append("]");
        sb.append("{");
        sb.append("\"title\": {},\"tooltip\": {\"trigger\": \"axis\",\"axisPointer\": {\"type\": \"shadow\"}},  ");
        sb.append("\"legend\": {\"textStyle\" : { \"color\" : \"#ffffff\" },\"data\": [\"TOTAL\", \"JAN\",\"FEB\",\"MAR\",\"APR\"]},");
        sb.append("\"grid\": {\"left\": \"3%\",\"right\":\"4%\",\"bottom\":\"3%\",\"containLabel\": true},");
        sb.append("\"yAxis\": {\"type\":\"value\",\"boundaryGap\": [0, 0.01],");
        sb.append(ChartUtil.setAxisLine("#87cefa"));
        sb.append(ChartUtil.setAxisLabel(null,null,null,"#fff"));
        sb.append("},");
        sb.append(" \"xAxis\": {\"axisLabel\": {\"interval\":0,\"rotate\":0 },\"type\":\"category\",\"data\": "+materielData+" ,");
        sb.append(ChartUtil.setAxisLine("#87cefa"));
        sb.append(ChartUtil.setAxisLabel("0","40","13","#fff"));
        sb.append("},");
        sb.append("\"series\": [{\"name\": \"TOTAL\",\"type\":\"bar\",\"data\": "+totallData+"},");
        sb.append("{\"name\":\"JAN\",\"type\":\"bar\",\"data\": "+janData+" },");
        sb.append("{\"name\":\"FEB\",\"type\":\"bar\",\"data\":"+febData+" },");
        sb.append("{\"name\":\"MAR\",\"type\":\"bar\",\"data\": "+marData+" },");
        sb.append("{\"name\":\"APR\",\"type\":\"bar\",\"data\": "+aprData+" }");
        sb.append("]}");
        return sb.toString();
    }

    @Override
    public String selectBomPrice() {

       List<Map<String, Object>> list = shipmentsMapper.selectBomPrice();
        StringBuffer sb=new StringBuffer();
        StringBuffer janData=StringBeginUtil.StringBeginWithRarentheses();
        StringBuffer febData=StringBeginUtil.StringBeginWithRarentheses();
        StringBuffer marData=StringBeginUtil.StringBeginWithRarentheses();
        StringBuffer aprData=StringBeginUtil.StringBeginWithRarentheses();
        StringBuffer averageData=StringBeginUtil.StringBeginWithRarentheses();
         StringBuffer  materielBom=new StringBuffer();

        for (int i=0;i<list.size();i++){
            String materiel = (String) list.get(i).get("short_name");
            materielBom.append("\""+materiel+"\",");
            Double jan = (Double) list.get(i).get("JAN");
            janData.append(jan+",");
            Double reb = (Double) list.get(i).get("FEB");
            febData.append(reb+",");
            Double mar = (Double) list.get(i).get("MAR");
            marData.append(mar+",");
            Double apr = (Double) list.get(i).get("APR");
            aprData.append(apr+",");
            Double average = (Double) list.get(i).get("average");
            averageData.append(average+",");

        }
        materielBom = StringbreakUtil.breakString(materielBom);
        janData = StringbreakUtil.breakString(janData);
        febData = StringbreakUtil.breakString(febData);
        marData = StringbreakUtil.breakString(marData);
        aprData = StringbreakUtil.breakString(aprData);
        averageData=StringbreakUtil.breakString(averageData);
        sb.append("{");
        sb.append("\"title\": {},\"tooltip\": {\"trigger\": \"axis\",\"axisPointer\": {\"type\": \"shadow\"}},    ");
        sb.append("\"legend\": {\"textStyle\" : { \"color\" : \"#ffffff\" },\"data\": [\"AVERAGE\", \"JAN\",\"FEB\",\"MAR\",\"APR\"]},");
        sb.append(" \"grid\": {\"left\": \"3%\",\"right\":\"4%\",\"bottom\":\"3%\",\"containLabel\": true},");
        sb.append("\"yAxis\": {\"type\":\"value\",\"boundaryGap\": [0, 0.01],");
        sb.append(ChartUtil.setAxisLine("#87cefa"));
        sb.append(ChartUtil.setAxisLabel(null,null,null,"#fff"));
        sb.append(" },");
        sb.append("\"xAxis\": {\"axisLabel\": {\"interval\":0,\"rotate\":0 },\"type\":\"category\",\"data\":[ "+materielBom+",");
        sb.append(ChartUtil.setAxisLine("#87cefa"));
        sb.append(ChartUtil.setAxisLabel(null,"40","12","#fff"));
        sb.append("},");
        sb.append(" \"series\": [{\"name\": \"AVERAGE\",\"type\":\"bar\",\"data\": "+averageData+"},");
        sb.append("{\"name\":\"JAN\",\"type\":\"bar\",\"data\": "+janData+"},");
        sb.append("{\"name\":\"FEB\",\"type\":\"bar\",\"data\":"+febData+"},");
        sb.append("{\"name\":\"MAR\",\"type\":\"bar\",\"data\": "+marData+"},");
        sb.append("{\"name\":\"APR\",\"type\":\"bar\",\"data\": "+aprData+"}]");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String selectfinishproduct() {
        StringBuffer sb=new StringBuffer();
        sb.append("{");
        sb.append("\"title\" : {},\"tooltip\": {},\"toolbox\": {\"show\": true,");
        sb.append("\"feature\": {\"dataView\": {\"show\": true, \"readOnly\": false},");
        sb.append("\"magicType\" : {\"show\": true, \"type\": [\"line\", \"bar\"]},");
        sb.append("\"restore\": {\"show\": true},\"saveAsImage\": {\"show\": true}}},");
        sb.append("\"xAxis\":[{\"type\": \"category\",");
        sb.append("\"data\":[");
        List<Map<String, Object>> finishProduct = shipmentsMapper.selectfinishproduct();
          for (int i=0;i<finishProduct.size();i++){
              String day = (String)finishProduct.get(i).get("day");
               sb.append("\""+day+"\",");
          }
        sb.delete(sb.length()-1,sb.length());
        sb.append("],");
        sb.append(ChartUtil.setAxisLine("#87cefa"));
        sb.append(ChartUtil.setAxisLabel(null,"40","12","#fff"));
        sb.append("}],");
        sb.append("\"yAxis\":[{\"name\":\"成品率\",\"type\": \"value\",\"scale\": true,");
        sb.append(ChartUtil.setAxisLine("#87cefa"));
        sb.append(ChartUtil.setAxisLabel(null,null,null,"#fff"));
        sb.append("}],");
        sb.append("\"series\": [{\"name\": \"月成本\",\"type\": \"line\",\"data\":[");
        for (int i=0;i<finishProduct.size();i++){
            Double rate = (Double)finishProduct.get(i).get("rate");
            sb.append("\""+rate+"\",");
        }
        sb.delete(sb.length()-1,sb.length());
        sb.append("]}]");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String selectMarketData() {
        List<Map<String, Object>> list = shipmentsMapper.selectMarketData();
         StringBuffer sbCompany=new StringBuffer();
         StringBuffer sbMount=new StringBuffer();
        StringBuffer sbName=new StringBuffer();
        StringBuffer sbMarket=new StringBuffer();
        sbMarket.append("{");
        sbName.append("\"datax\":[");
        sbCompany.append("\"data2\":[");
        sbMount.append("\"data3\":[");
            for (int i=0;i<list.size()/10;i++) {
                sbCompany.append("[");
                sbMount.append("[");
                for (int j=0; j<10;j++){
                    if (j==0){
                        String name = (String)list.get(i * 10 + j).get("name");
                        sbName.append("\""+name+"\",");
                    }
                    String company = (String)list.get(i * 10 + j).get("company");
                    Double mount = (Double)list.get(i * 10 + j).get("mount");
                    sbCompany.append("\""+company+"\",");
                    sbMount.append(mount+",");
                 }
                sbCompany.delete(sbCompany.length()-1,sbCompany.length());
                sbMount.delete(sbMount.length()-1,sbMount.length());
                sbCompany.append("],");
                sbMount.append("],");

            }
         sbName= StringbreakUtil.breakString(sbName);
        sbMount= StringbreakUtil.breakString(sbMount);
        sbCompany= StringbreakUtil.breakString(sbCompany);
        sbMarket.append(sbName+",");
        sbMarket.append(sbCompany+",");
        sbMarket.append(sbMount+"}");

        return sbMarket.toString();
    }

    @Override
    public String selectIndustryInformationPrice() {

        List<Map<String, Object>> list = shipmentsMapper.selectIndustryInformationPrice();
        StringBuffer sbIndustry=new StringBuffer();
        StringBuffer sbIndustryInformation=new StringBuffer();
        StringBuffer sbInformation=new StringBuffer();
        StringBuffer sbPrice=new StringBuffer();
        sbIndustry.append("{");
        sbIndustryInformation.append("\""+"datax"+"\":[");
        sbInformation.append("\""+"data2"+"\":[");
        sbPrice.append("\""+"data3"+"\":[");
        for (int i=0;i<list.size();i++){
            String item = (String)list.get(i).get("item");
            Double price = (Double)list.get(i).get("price");
            sbIndustryInformation.append("\""+item+"\",");
            sbInformation.append("[\""+item+"\"],");
            sbPrice.append(price+",");
        }
        sbIndustryInformation= StringbreakUtil.breakString(sbIndustryInformation);
        sbPrice= StringbreakUtil.breakString(sbPrice);
        sbInformation= StringbreakUtil.breakString(sbInformation);
        sbIndustry.append(sbIndustryInformation+",");
        sbIndustry.append(sbInformation+",");
        sbIndustry.append(sbPrice+"}");
        return sbIndustry.toString();
    }

    @Override
    public String selectWorkingOutput() {

        List<Map<String, Object>> list = shipmentsMapper.selectWorkingOutput();
        StringBuffer sbWorking=new StringBuffer();
        StringBuffer sbOutput=new StringBuffer();
        StringBuffer sbTime=new StringBuffer();
        sbWorking.append("{");
        sbOutput.append("\""+"data"+"\":[");
        sbTime.append("\""+"data1"+"\":[");
        for (int i=0;i<list.size();i++){
            Double output = (Double)list.get(i).get("output");
            String time = (String) list.get(i).get("time");
            sbTime.append("\""+time+"\",");
            sbOutput.append(output+",");
        }
        sbOutput=StringbreakUtil.breakString(sbOutput);
        sbTime=StringbreakUtil.breakString(sbTime);
        sbWorking.append(sbOutput+",");
        sbWorking.append(sbTime+"}");

        return sbWorking.toString();
    }
}
