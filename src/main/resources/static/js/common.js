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

