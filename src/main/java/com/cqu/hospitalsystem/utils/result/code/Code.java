package com.cqu.hospitalsystem.utils.result.code;

public interface Code {
	// 成功
	int SUCCESS  = Errs.put(666, "操作成功");
	// 未知错误
	int ERROR	 = Errs.put(-1, "操作有误");
	// 未知错误
	int LOGIN_OUT	 = Errs.put(-2, "登录失效");

	//账号或密码错误，用于注册登录
	int ACCOUNT_ERROR = Errs.put(700, "账号或密码错误");
	//账号重复，用于员工注册
	int ACCOUNT_REPET = Errs.put(701, "账号重复");
	//信息查找/修改失败，请稍后再试
	int INFO_ERROR = Errs.put(702, "请求失败，请稍后再试");
	//输入错误旧密码（更改密码）
	int PASSWORD_ERROR = Errs.put(703, "请输入正确的旧密码");


}