package com.luckypandadas.common;

/**
 * @author wenfeng zhang @ 10/21/14
 */
public enum ResponseStatus {

    SUCCESS(200, "操作成功！"),
    ERROR(500, "服务器未知错误！"),
    UNAUTHORIZED(401, "没有登录！"),
    FORBIDDEN(403, "没有权限！"),
    NOT_FOUND(404, "资源不存在！"),
    SECURITY_CODE_ERROR(405, "验证码错误！"),
    LOGIN_ERROR(406, "账号或密码错误！"),
    PASSWORD_ERROR(407, "密码不一致！"),
    USER_EXIST(408, "已存在的用户！"),
    DATA_PARSE_ERROR(4001, "JSON解析错误！"),
    INVALID_DATA(4002, "数据校验错误，请检查输入！"),
    EMPTY_PARAM(4003, "参数为空！");

    public Integer VALUE;
    public String MESSAGE;

    ResponseStatus(Integer value, String message) {
        this.VALUE = value;
        this.MESSAGE = message;
    }

}
