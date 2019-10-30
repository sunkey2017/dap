define(['echarts','bmap','common'],function (echarts,BMap,Common){

    var initMapChart = function (optionData,divId){
        var mapChart = echarts.init(document.getElementById(divId), null, {});

        var data = [
            {name: '浙江组件', value: 2565.0000 },
            {name: '泰州组件', value: 3792.0000 },
            {name: '银川组件', value: 402.0000 },
            {name: '西安组件', value: 220.0000 },
            {name: '古晋组件', value: 900.0000 },
            {name: '大同组件', value: 376.0000 },
            {name: '泰州电池', value: 2822.0000 },
            {name: '合肥电池', value: 712.0000 },
            {name: '古晋电池', value: 728.0000 },
            {name: '银川隆基', value: 12538.1900 },
            {name: '宁夏隆基', value: 4254.7300 },
            {name: '保山隆基', value: 5801.0800 },
            {name: '丽江隆基', value: 3867.3900 },
            {name: '西安硅片', value: 5461.1400 },
            {name: '无锡硅片', value: 6806.2100 },
            {name: '银川三厂硅片', value: 7226.0000 },
            {name: '楚雄硅片', value: 6331.3600 },
            {name: '古晋硅片', value: 1051.9300 }
        ];
        var geoCoordMap = {
            '浙江组件':[118.8800,28.9700],
            '泰州组件':[119.9000,32.4900],
            '银川组件':[106.2000,38.7000],
            '西安组件':[108.9000,34.3900],
            '古晋组件':[101.4200,3.0800],
            '大同组件':[113.3000,40.1200],
            '泰州电池':[119.9000,32.4900],
            '合肥电池':[117.2700,31.8600],
            '古晋电池':[101.4200,3.0800],
            '银川隆基':[106.100,38.4550],
            '宁夏隆基':[105.9000,37.9900],
            '保山隆基':[99.1000,25.0800],
            '丽江隆基':[100.2500,26.8600],
            '西安硅片':[108.9400,34.1700],
            '无锡硅片':[120.2900,31.5900],
            '银川三厂硅片':[106.100,38.4560],
            '楚雄硅片':[100.3500,24.3000],
            '古晋硅片':[101.4200,3.0800]
        };

        var convertData = function (data) {
            var res = [];
            for (var i = 0; i < data.length; i++) {
                var geoCoord = geoCoordMap[data[i].name];
                if (geoCoord) {
                    res.push({
                        name: data[i].name,
                        value: geoCoord.concat(data[i].value)
                    });
                }
            }
            return res;
        };

        mapChart.setOption({
            tooltip : {
                trigger: 'item',
                formatter:function(params, ticket, callback){
                    return params.value[2]
                }
            },
            bmap: {
                center: [110.396435,23.585408],
                zoom: 4,
                roam: true,
            },
             series : [
                 {
                     name: '产能(100mw)',
                     type: 'scatter',
                     coordinateSystem: 'bmap',
                     data: convertData(data),
                     symbolSize: function (val) {
                         return Math.pow(val[2],0.25)*2;
                     },
                     label: {
                         normal: {
                             formatter: '{b}',
                             position: 'right',
                             show: false
                         },
                         emphasis: {
                             show: true
                         }
                     },
                     itemStyle: {
                         normal: {
                             color: 'Firebrick'
                         }
                     }
                 },
             ]
         });

        mapChart.on('click', function (params) {
            //console.log(params.value);
            if("西安组件" == params.data.name){
                toPage('production');
            }

        });

        var bmap = mapChart.getModel().getComponent('bmap').getBMap()
       // bmap.addControl(BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_SATELLITE_MAP ]}));
        bmap.setMapStyle({style:'midnight'})

    };

    return {
        initMapChart: initMapChart
    };

});