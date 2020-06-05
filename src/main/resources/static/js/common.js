var hostname = window.location.hostname;//
var port = window.location.port;//
function GetQueryString(paraPart,name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = paraPart.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

function toPage(page){
    window.location.href = "http://"+hostname+":"+port+"/"+page;
}

/**
 * 产生随机整数，包含下限值，但不包括上限值
 * @param {Number} lower 下限
 * @param {Number} upper 上限
 * @return {Number} 返回在下限到上限之间的一个随机整数
 */
function random(lower, upper) {
    return Math.floor(Math.random() * (upper - lower)) + lower;
}

//动态设置数据
function dynamicSetData(inId,maxValue){
    var curValue = $("#"+inId).text();
    curValue = Number(curValue) + Number(random(1,10)/3);
    curValue = Math.floor(curValue * 100) / 100
    if(curValue > maxValue){
        curValue = 2029.7;
    }
    $("#"+inId).text(curValue);
}
window.setInterval(function(){
    //dynamicSetData("batteryOut",517106);
    dynamicSetData("componentOut",2829);
},5000);


/**
 * 跳转页面带参数
 * @param page 页面名称
 * @param params 参数字符串
 */
function toPageWithParmas (page, params) {
    window.location.href = "http://" + hostname + ":" + port + "/" + page + "?" +params;
}

/**
 * 获取页面传值参数
 * @param name 想要获取的参数名
 * @returns {string|null} 返回对应的参数值
 */
function getUrlParam(name) {
    var reg = new RegExp("(\\?|\&)" + name + "=([^&]*)(\&|$)", "i");
    var r = window.location.href.match(reg);
    if (r != null) return unescape(r[2]); return null;
}

/**
 * 格式化数据，保留两位小数
 * @param val
 */
function fomateNumber(val) {
    if (val !== "" || val!== null) {
        var num = new Number(val);
        return num.toFixed(2)
    } else {
        return "";
    }

}