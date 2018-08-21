package com.tpadsz.after.entity;

public enum ResultDict {

	SUCCESS("000", "成功"),
	ACCOUNT_ALREADY_REGISTERED("100", "手机号码已经被注册"), QQNUMBER_ALREADY_REGISTERED("118", "QQ已经被绑定"), ACCOUNT_NOT_CORRECT("101", "登陆账号不正确"),
	VALIDATION_CODE_NOT_CORRECT("102", "验证码不正确"), REMOTE_NOT_FOUND("103", "第三方账号不兼容"),
	ACCOUNT_NOT_EXISTED("104", "账号不存在"), ACCOUNT_NOT_ACTIVATE("105", "账号没有激活"), PASSWORD_NOT_CORRECT("111", "登陆账号密码不正确"),
	MASTER_NESTED_LOOP("112", "填写的邀请码与自身邀请码或者自身的徒弟邀请码冲突."), ALIPAY_ALREADY_EXIST("113", "支付宝账号已存在"), ALIPAY_TIMES_LIMIT("114", "支付宝更新次数超限"),
	TOKEN_NOT_CORRECT("106", "token失效"), TOKEN_NOT_SUBMIT("107", "token没有提交"), TOKEN_REPLACED("108", "账号在其他设备登陆。"), TOKEN_UNLOCKED_UNEFFECTIVE("109", "token失效,没有绑定手机"), TOKEN_LOCKED_UNEFFECTIVE("110", "token失效,绑定手机"),
	SYSTEM_ERROR("200", "系统错误"), TIMEOUT("201", "系统超时"),UNEXPECTED_ERROR("202","未知错误"),
	PARAMS_BLANK("301", "参数不能够为空"), PARAMS_NOT_PARSED("302", "参数解析错误"), ID_NOT_FOUND("303", "ID不存在"), FILE_NOT_TRANSMITTED("304", "上传文件不存在"),
	AUTHORITY_NOT_ALLOWED("400", "用户权限不够"), COINS_NOT_ENOUGH("401", "积分不够"), GOODS_NOT_ENOUGH("402", "产品不够"),
	TASK_NOT_EXIST("500", "任务不存在"), TASK_REPEATED("501", "任务重复提交."), CHECK_REPEATED("502", "check重复提交"), TASK_VALIDATOR_ERROR("503", "任务校验失败。"), TASK_DEALER_ERROR("504", "任务处理失败。"), TASK_DATA_SERIAL_BROADCAST_NOT_NOW("505", "深度任务提交错误."),
	TASK_EVERYDAY_REPEATED("506", "任务超出每日提交限制"), TASK_EVERYHOUR_REPEATED("507", "任务超出每小时提交限制"), TASK_SUBMIT_REPEATED("509", "任务提交间隔过短"), SIGN_REDPACK_NOT_REACHED("510", "签到红包不能领取"), SIGN_REDPACK_ACCRPYED("511", "签到红包已领取"),
	TASK_CANNOT_SUBMIT_YET("512", "任务尚不能提交"), BLACK_LIST("513", "黑名单"),
	NOT_REALUSER("113", "刷量用户不予注册"), TTLOCKER_USER("114", "此用户为天天锁屏用户"), NOT_DEVICE_USER("601", "一机多号"),
	DEVID_TASKEVERYDAY_LIMITE("602", "同一个devId每日提交任务次数达到上限"), COINS_OR_STOCK_NOT_ENOUGH("403", "积分或库存不足"),PROVINCE_NOT_POSTAGE("123","省份没有快递"),NOT_PERMISSION_ENTER("125","无权限进入");


	ResultDict(String code, String value) {
		this.value = value;
		this.code = code;
	}

	private String value;
	private String code;

	public String getValue() {
		return value;
	}

	public String getCode() {
		return code;
	}

}
