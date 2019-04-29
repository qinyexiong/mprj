package main.common.base.web.exception;

/**
 * 
 * @描述: 业务异常基类，所有业务异常都必须继承于此异常 . <br/>
 * @作者: qinyx . <br/>
 * @创建时间: 2014-10-14,上午12:31:57 . <br/>
 * @版本号: V1.0 . <br/>
 */
public class UeException extends RuntimeException {

	private static final long serialVersionUID = -5875371379845226068L;

	/**
	 * 数据库操作,insert返回0
	 */
	public static final UeException DB_INSERT_RESULT_0 = new UeException(100001, "数据库操作,insert返回0");

	/**
	 * 数据库操作,update返回0
	 */
	public static final UeException DB_UPDATE_RESULT_0 = new UeException(100002, "数据库操作,update返回0");

	/**
	 * 数据库操作,selectOne返回null
	 */
	public static final UeException DB_SELECTONE_IS_NULL = new UeException(100003, "数据库操作,selectOne返回null");

	/**
	 * 数据库操作,list返回null
	 */
	public static final UeException DB_LIST_IS_NULL = new UeException(100004, "数据库操作,list返回null");

	/**
	 * Token 验证不通过
	 */
	public static final UeException TOKEN_IS_ILLICIT = new UeException(100004, "Token 验证非法");
	/**
	 * 会话超时　获取session时，如果是空，throws 下面这个异常 拦截器会拦截爆会话超时页面
	 */
	public static final UeException SESSION_IS_OUT_TIME = new UeException(100006, "会话超时");

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected int code;

	public UeException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public UeException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public UeException newInstance(String msgFormat, Object... args) {
		return new UeException(this.code, msgFormat, args);
	}

	public UeException(String message, Throwable cause) {
		super(message, cause);
	}

	public UeException(Throwable cause) {
		super(cause);
	}

	public UeException(String message) {
		super(message);
	}
}
