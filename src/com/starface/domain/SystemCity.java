package com.starface.domain;

public class SystemCity {
	
    private Short classId;

    private Short classParentId;

    private String className;

    private Integer classType;

    public Short getClassId() {
        return classId;
    }

    public void setClassId(Short classId) {
        this.classId = classId;
    }

    public Short getClassParentId() {
        return classParentId;
    }

    public void setClassParentId(Short classParentId) {
        this.classParentId = classParentId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

	/**
	 * @return the classType
	 */
	public Integer getClassType() {
		return classType;
	}

	/**
	 * @param classType the classType to set
	 */
	public void setClassType(Integer classType) {
		this.classType = classType;
	}

}