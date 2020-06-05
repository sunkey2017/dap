require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min',
        message: 'plugins/message/message.min',
        page: 'plugins/page/page'
    }
});

var userMain={}

require(['jquery', 'manage/js/requestData','common', 'message', 'page', 'initUser'],
    function ($, RequestData,commom,message,page,initUser) {

        $(function () {
             getUserList();
        });
        userMain.searchUser = function () {
            let searchWord = $("#searchUser").val();
            var userInfo = RequestData.getUsers(searchWord);
            userInfo.then(function (resdata) {
                let res = JSON.parse(resdata);
                // 清除page重新渲染
                $(".pagediv").empty();
                let pageNum = pageCount(res.length,10);
                //翻页
                $(".pagediv").createPage({
                    pageNum: pageNum,
                    current: 1,
                    backfun: function(e) {
                        let searchWord = $("#searchUser").val();
                        var userInfo = RequestData.getUsers(searchWord);
                        userInfo.then(function (resdata) {
                            var data =JSON.parse(resdata)
                            var newArray = handlePagePagination(e.current, 10, data);
                            userMain.renderTableList(newArray);
                        });
                    }
                });
                var filterData = handlePagePagination(1,10,res);
                userMain.renderTableList(filterData);
            })
        }

        userMain.renderTableList = function (datalist) {
               let tablehtml = "";
               for (let i=0; i<datalist.length; i++){

                   let sex = "";
                   if (datalist[i].sex === "1"){
                       sex="男";
                   } else if (datalist[i].sex === "0") {
                       sex="女";
                   }

                   tablehtml +="    <ul>\n" +
                       "            <li class=\"user-ul user-one\"><span onclick=\"toPage('personal')\" style=\"color:#3d7eff; font-weight: 600; cursor: pointer\">" +
                       datalist[i].userName +
                       "</span></li>\n" +
                       "            <li class=\"user-ul user-two\">" +
                       datalist[i].realName +
                       "</li>\n" +
                       "            <li class=\"user-ul user-three\">" +
                       datalist[i].email +
                       "</li>\n" +
                       "            <li class=\"user-ul user-four\">" +
                       datalist[i].mobile +
                       "</li>\n" +
                       "            <li class=\"user-ul user-five\">" +
                       sex +
                       "</li>\n" +
                       "            <li class=\"user-ul user-six\">" +
                       datalist[i].age +
                       "</li>\n" +
                       "            <li class=\"user-ul user-seven\"><img src=\"../images/delete.png\" class=\"alarm-img\" onclick=\"userMain.deleteUser(" +
                       datalist[i].id
                       +
                       ")\">                <img src=\"../images/update.png\" style='height: 20px; width: 20px; border-radius: 0px;' class=\"alarm-img\" onclick=\"toPageWithParmas('updateUser', 'userName=" +
                       datalist[i].userName +
                       "')\">\n</li>\n" +
                       "\n" +
                       "        </ul>";

                   document.getElementById('user-list-ul').innerHTML = tablehtml;

               }
        }

        userMain.deleteUser = function (id) {
         var resdata = RequestData.deleteUser(id);
         resdata.then(function (res) {
             let result = JSON.parse(res);
             if (result.success==="true"){
                 $.message({
                     message:"删除成功",
                     type:'success'
                 });

                 // 刷新数据
                 getUserList();
             } else {
                 $.message({
                     message:result.message,
                     type:'error'
                 });
             }
         })
        }

        function getUserList() {
            $(".pagediv").empty();
            var userInfo = RequestData.getUsers("");
            userInfo.then(function (resdata) {
                let res = JSON.parse(resdata);
                let pageNum = pageCount(res.length,10);
                var filterData = handlePagePagination(1,10,res);
                userMain.renderTableList(filterData);
                //翻页
                $(".pagediv").createPage({
                    pageNum: pageNum,
                    current: 1,
                    backfun: function(e) {
                        var userInfo = RequestData.getUsers("");
                        userInfo.then(function (resdata) {
                            let res = JSON.parse(resdata);
                            var newArray = handlePagePagination(e.current, 10, res);
                            userMain.renderTableList(newArray);
                        })
                    }
                });

            })
        }

        /**
         * 计算页数
         * @param totalnum 总数
         * @param limit 条数
         * @returns {number} 返回页数
         */
        function pageCount (totalnum,limit){
            return totalnum > 0 ? ((totalnum < limit) ? 1 : ((totalnum % limit) ? (parseInt(totalnum / limit) + 1) : (totalnum / limit))) : 0;
        }

        function handlePagePagination (pageNo, pageSize, array) {
            var offset = (pageNo - 1) * pageSize;
            var newArray = (offset + pageSize >= array.length) ? array.slice(offset, array.length) : array.slice(offset, offset + pageSize);
            return newArray;
        }
    });