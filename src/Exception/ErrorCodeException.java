package Exception;

public class ErrorCodeException extends RuntimeException {
	
	private String errorCode;
	
	private Object[] args;//动态填充数组对象
	
	public ErrorCodeException(String errorCode) {
		this(errorCode, null);
	}
	
	public ErrorCodeException(String errorCode, Object args0) {
		this(errorCode, new Object[]{args0});
	}
	
	public ErrorCodeException(String errorCode, Object[] args) {
		this.errorCode = errorCode;
		this.args = args;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getArgs() {
		return args;
	}
}
