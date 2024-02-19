package com.rabbit.result.enums;

public enum AppHttpCodeEnum {
    SUCCESS(200,"操作成功"),
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户已存在"),
    USERNAME_NOT_NULL(508,"用户名不能为空"),
    NICKNAME_NOT_NULL(509,"昵称不能为空"),
    PASSWORD_NOT_NULL(510,"密码不能为空"),
    EMAIL_NOT_NULL(511,"邮箱不能为空"),
    PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503,"邮箱已存在"),
    REQUIRE_USERNAME(504,"必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    CONTENT_NOT_NULL(506,"内容不能为空"),
    FILE_TYPE_NULL(507,"文件类型错误"),
    SENSITIVE_CONTENT(520,"存在敏感词"),
    CNVDSerialNumber_NOT_NULL(202401, "CNVD编号不能为空"),
    CNVDSummary_NOT_NULL(202402, "CNVD描述不能为空"),
    CNVDLevel_NOT_NULL(202403, "CNVD级别不能为空"),
    CNVDLink_NOT_NULL(202404, "CNVD链接不能为空"),
    CNVDPath_NOT_NULL(202405, "CNVD补丁不能为空");



    int code;
    String msg;
    AppHttpCodeEnum(int code,String errorMessage){
        this.code=code;
        this.msg=errorMessage;
    }

    public int getCode(){return code;}
    public String getMsg(){return msg;}
}
