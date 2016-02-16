package it.fff.business.common.bo;

public class EmailInfoBO implements BusinessObject {

	private String email;
	private boolean isExisting;
	private boolean isValidAccount;
	private boolean isVerifiedAccount;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isExisting() {
		return isExisting;
	}
	public void setExisting(boolean isExisting) {
		this.isExisting = isExisting;
	}
	public boolean isValidAccount() {
		return isValidAccount;
	}
	public void setValidAccount(boolean isValidAccount) {
		this.isValidAccount = isValidAccount;
	}
	public boolean isVerifiedAccount() {
		return isVerifiedAccount;
	}
	public void setVerifiedAccount(boolean isVerifiedAccount) {
		this.isVerifiedAccount = isVerifiedAccount;
	}
	
}
