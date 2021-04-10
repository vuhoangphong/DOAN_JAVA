package web.badminton.modal;

public class ResponseMOMO {
	private String requestId;
	private int errorCode;
	private String message;
	private String localMessage;
	private String requestType;
	private String payUrl;
	private String qrCodeUrl;
	private String deeplink;
	private String deeplinkWebInApp;
	private String signature;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLocalMessage() {
		return localMessage;
	}
	public void setLocalMessage(String localMessage) {
		this.localMessage = localMessage;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getPayUrl() {
		return payUrl;
	}
	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	public String getDeeplink() {
		return deeplink;
	}
	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}
	public String getDeeplinkWebInApp() {
		return deeplinkWebInApp;
	}
	public void setDeeplinkWebInApp(String deeplinkWebInApp) {
		this.deeplinkWebInApp = deeplinkWebInApp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getErrorCode() {
		return errorCode;
	}
	
	
	
}
