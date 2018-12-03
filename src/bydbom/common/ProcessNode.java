package bydbom.common;

public class ProcessNode {
	private String stepName;
	private String stepMultiple;
	private String stepMultiple_type;
	private String stepDispatch;
	private String actionName;
	private String argRoleCode;
	
	public void setStepName(String stepName) {
		this.stepName=stepName;
	}
	
	public String getStepName() {
		return this.stepName;
	}
	
	public void setStepMultiple(String stepMultiple) {
		this.stepMultiple=stepMultiple;
	}
	
	public String getStepMultiple() {
		return this.stepMultiple;
	}
	
	public void setStepMultiple_type(String stepMultiple_type) {
		this.stepMultiple_type=stepMultiple_type;
	}
	
	public String getStepMultiple_type() {
		return this.stepMultiple_type;
	}
	
	public void setStepDispatch(String stepDispatch) {
		this.stepDispatch=stepDispatch;
	}
	
	public String getStepDispatch() {
		return this.stepDispatch;
	}
	
	public void setActionName(String actionName) {
		this.actionName=actionName;
	}
	
	public String getActionName() {
		return this.actionName;
	}
	
	public void setArgRoleCode(String argRoleCode) {
		this.argRoleCode=argRoleCode;
	}
	
	public String getArgRoleCode() {
		return this.argRoleCode;
	}
	
	public boolean equals(ProcessNode step) {
		return this.stepName.equals(step.getStepName()) && this.stepMultiple.equals(step.getStepMultiple()) &&
				this.stepMultiple_type.equals(step.getStepMultiple_type()) && 
				this.stepDispatch.equals(step.getStepDispatch()) &&
				this.actionName.equals(step.getActionName()) &&
				this.argRoleCode.equals(step.getArgRoleCode());
		
	}
	
	public String toString() {
		return this.stepName + " " + this.stepMultiple + " " + this.stepMultiple_type +
				" " + this.stepDispatch + " " + this.actionName + " " + this.argRoleCode;
	}

}
