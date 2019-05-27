package com.ggiit.easyblog.common.util.response;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 相应流工具
 *
 * @author gao
 * @date 2019.5.27
 */
@Slf4j
public class ResponseUtil {
    /**
     * 使用response输出JSON
     *
     * @param response
     * @param resultMap
     */
    public static void out(HttpServletResponse response, Object object) {

        ServletOutputStream out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            out = response.getOutputStream();
            out.write(JSONUtil.toJsonStr(object).getBytes());
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
