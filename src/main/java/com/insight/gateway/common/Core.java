package com.insight.gateway.common;

import com.insight.gateway.common.client.AuthClient;
import com.insight.utils.Json;
import com.insight.utils.pojo.LoginInfo;
import com.insight.utils.pojo.Reply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 宣炳刚
 * @date 2020/6/26
 * @remark
 */
@Component
public class Core {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AuthClient client;

    /**
     * 构造方法
     *
     * @param client AuthClient
     */
    public Core(AuthClient client) {
        this.client = client;
    }

    /**
     * 获取用户权限集合
     *
     * @return 权限集合
     */
    public List<String> getPermits(LoginInfo info) {
        try {
            Reply reply = client.getPermits(Json.toBase64(info));
            if (reply.getSuccess()) {
                logger.info(reply.getData().toString());
                return Json.cloneList(reply.getData(), String.class);
            } else {
                logger.warn(reply.getMessage());
                return null;
            }
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
            return null;
        }
    }
}
