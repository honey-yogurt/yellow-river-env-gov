package cn.gov.yrcc.infrastructure.exception;

import cn.gov.yrcc.shared.api.IErrorCode;

/**
 *  断言处理类，用于抛出各种API异常
 * Created by yogurt on 2023/4/10.
 */
public class Asserts {
	public static void fail(String message) {
		throw new ApiException(message);
	}

	public static void fail(IErrorCode errorCode) {
		throw new ApiException(errorCode);
	}
}
