define(['common', 'manage/js/requestData'], function (common, RequestData) {

    /**
     * 页面初始化方法，根据需要渲染不同dom
     */
    $(function () {
        // 获取请求参数
        var cityCode = getUrlParam("cityCode");
        // 设置2小时刷新一次
        window.setInterval(function () {
            var data = {}
            var initData = RequestData.getWeatherInfo(cityCode);
            initData.then(function (resultData) {
                data = JSON.parse(resultData)
                renderWeather(data);
            });
        }, 7200000);


        var initData = RequestData.getWeatherInfo(cityCode);
           initData.then(function (resultData) {
            let data = JSON.parse(resultData)
            renderWeather(data);
        });

    });

    /**
     * 动态设置P标签文字
     * @param elemId 标签Id
     * @param text 文字
     */
    function setText(elemId, text) {
        $(elemId).text(text);
    }

    /**
     * 渲染天气信息
     * @param data
     */
    function renderWeather(data) {
        let forecastList = [];
        if (data.forecastList !== undefined && data.forecastList.length > 0) {
            forecastList = data.forecastList;
        }
        let pm10 = data.pm10 !== undefined ? "PM10: "+data.pm10 : "";
        let pm25 = data.pm25 !== undefined ? "PM25: "+data.pm25 : "";
        let fl = forecastList[0].fl !== undefined ? "风力: "+forecastList[0].fl : "";
        let fx = forecastList[0].fx !== undefined ? "风向: "+forecastList[0].fx : "";

        setText("#pm10-25", pm10+"  "+pm25);
        setText("#fl-fx", fl+"  "+ fx);
        setText("#quality", data.quality !== undefined ? "空气质量: "+data.quality : "");
        setText("#shidu", data.shidu !== undefined ? "湿度: "+data.shidu : "");
        setText("#wendu", data.wendu !== undefined ? "温度: "+data.wendu+"℃" : "");
        setText("#type", forecastList[0].type !== undefined ? forecastList[0].type : "");
        let sunrise = forecastList[0].sunrise !== undefined ? forecastList[0].sunrise:"";
        let sunset = forecastList[0].sunset !== undefined ? forecastList[0].sunset : "";
        setText("#sunset-sunrise", "日出: "+sunrise+"  "+"日落: "+sunset);

        let todayImgurl = forecastList[0].type !== undefined ? "../images/weather/"+forecastList[0].type+".png" : "../images/weather.png";
        $("#weather-today").attr('src',todayImgurl);


        let weatherInfo = "";
        for (let i=1; i<forecastList.length; i++) {
            let imgurl = forecastList[i].type !== undefined ? "../images/weather/"+forecastList[i].type+".png" : "../images/weather.png";
            let lowarr = forecastList[i].low !== undefined ? forecastList[i].low : "";
            let heightarr = forecastList[i].high !== undefined ? forecastList[i].high : "";
            let low = lowarr.trim().split(/\s+/)[1];
            let height = heightarr.trim().split(/\s+/)[1];
            weatherInfo+="<div class=\"weather-card\">\n" +
                "                <p class=\"font10\">" +
                forecastList[i].week +
                "</p>\n" +
                "                <img src=\"" +
                imgurl +
                "\" class=\"weather-img-small\">\n" +
                "                <p class=\"font4\">" +
                low +"~"+height+
                "</p>\n" +
                "                <p class=\"font8\">" +
                forecastList[i].type +
                "</p>\n" +
                "            </div>";
        }
        document.getElementById('forecast-weather').innerHTML = weatherInfo;
    }


    return {};


});