package com.longi.dap.service;

import com.longi.dap.entity.*;
import com.longi.dap.vo.XNJCAPIParamVO;

/**
 *
 * @Author sunke5
 * @Description
 * @Date 17:00 2020-5-6
 * @Param
 * @return
 **/
public interface IXNLCService {

    XNJCResultBaseBean<CombinerBoxBean> getBoxList();

    XNJCResultBaseBean<GroupStringBean> getGroupList(String bid);

    XNJCResultBaseBean<ElementStringBean> getElementList(String gid);

    XNJCResultBaseBean<CodeInfoBean> getElementLast(String code);

    XNJCResultBaseBean<ElementAlarmBean> getElementAlarmList(XNJCAPIParamVO paramVO);

    XNJCResultBaseBean<CodeInfoBean> getElementHistoryList(XNJCAPIParamVO paramVO);
}
