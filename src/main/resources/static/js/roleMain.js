require.config({
    paths: {
        jquery: 'plugins/jquery/jquery.min',
        message: 'plugins/message/message.min',
        page: 'plugins/page/page'
    }
});

var roleMain={}

require(['jquery', 'manage/js/requestData','common', 'message', 'page', 'initUser'],
    function ($, RequestData,commom,message,page,initUser) {

        $(function () {
             getRoleList();
        });

        roleMain.renderTableList = function (datalist) {
               let tablehtml = "";

               for (let i=0; i<datalist.length; i++){
                   let des = "";
                   if (datalist[i].description != undefined){

                       des = datalist[i].description;
                   }
                   tablehtml +="    <ul>\n" +
                       "            <li class=\"user-ul role-one\">" +
                       datalist[i].roleCode +
                       "</li>\n" +
                       "            <li class=\"user-ul role-two\">" +
                       datalist[i].roleName +
                       "</li>\n" +
                       "            <li class=\"user-ul role-three\">" +
                       des +
                       "</li>\n" +
                       "            <li class=\"user-ul role-four\"><img src=\"../images/delete.png\" class=\"alarm-img\" onclick=\"roleMain.deleteRole(" +
                       datalist[i].id
                       +
                       ")\">\n</li>\n" +
                       "\n" +
                       "        </ul>";
                   document.getElementById('role-list-ul').innerHTML = tablehtml;

               }
        }

        roleMain.deleteRole = function (id) {
         var resdata = RequestData.deleteRole(id);
         resdata.then(function (res) {
             let result = JSON.parse(res);
             if (result.success==="true"){
                 $.message({
                     message:"删除成功",
                     type:'success'
                 });

                 // 刷新数据
                 getRoleList();
             } else {
                 $.message({
                     message:result.message,
                     type:'error'
                 });
             }
         })
        }

        function getRoleList() {
            $(".pagediv").empty();
            var userInfo = RequestData.getRoleList();
            userInfo.then(function (resdata) {
                let res = JSON.parse(resdata);
                let pageNum = pageCount(res.length,10);
                var filterData = handlePagePagination(1,10,res);
                roleMain.renderTableList(filterData);
                //翻页
                $(".pagediv").createPage({
                    pageNum: pageNum,
                    current: 1,
                    backfun: function(e) {
                        var userInfo = RequestData.getRoleList("");
                        userInfo.then(function (resdata) {
                            let res = JSON.parse(resdata);
                            var newArray = handlePagePagination(e.current, 10, res);
                            roleMain.renderTableList(newArray);
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