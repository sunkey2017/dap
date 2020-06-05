package com.longi.dap.tookits;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @version 1.0
 * @CalssName CommonUtil
 * @Author sunke5
 * @Date 2020-5-9 14:12
 */
public class CommonUtil {

    private static final Logger log = Logger.getLogger (CommonUtil.class);

    /**
     * 获取异常详细信息，知道出了什么错，错在哪个类的第几行 .
     * @param ex
     * @return
     */
    public static String getExceptionDetail(Exception ex) {
        String ret = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream ();
            PrintStream pout = new PrintStream (out);
            ex.printStackTrace (pout);
            ret = new String (out.toByteArray ());
            pout.close ();
            out.close ();
        } catch (Exception e) {
            log.error (e.getStackTrace()+"");
        }
        return ret;
    }
}
