package kr.or.ddit.vo;

import java.io.Serializable;

public class DataBasePropertyVO implements Serializable {
	// carmel 방식 변환
	private String propertyName;
	private String propertyValue;
	private String propertyDescription;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public String getPropertyDescription() {
		return propertyDescription;
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((propertyDescription == null) ? 0 : propertyDescription.hashCode());
		result = prime * result + ((propertyName == null) ? 0 : propertyName.hashCode());
		result = prime * result + ((propertyValue == null) ? 0 : propertyValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataBasePropertyVO other = (DataBasePropertyVO) obj;
		if (propertyDescription == null) {
			if (other.propertyDescription != null)
				return false;
		} else if (!propertyDescription.equals(other.propertyDescription))
			return false;
		if (propertyName == null) {
			if (other.propertyName != null)
				return false;
		} else if (!propertyName.equals(other.propertyName))
			return false;
		if (propertyValue == null) {
			if (other.propertyValue != null)
				return false;
		} else if (!propertyValue.equals(other.propertyValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataBasePropertyVO [propertyName=" + propertyName + ", propertyValue=" + propertyValue
				+ ", description=" + propertyDescription + "]";
	}

}
