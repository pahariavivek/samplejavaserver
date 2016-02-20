/**
 * (c) 2014 SAP AG or an SAP affiliate company.  All rights reserved.
 *
 * No part of this publication may be reproduced or transmitted in any form or for any purpose
 * without the express permission of SAP AG.  The information contained herein may be changed
 * without prior notice.
 */

package de.hd.seegarten.presence.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "T_CAMPAIGN")
@NamedQuery(name = "getUCID", query = "SELECT c FROM RuleEngineAction c WHERE c.UCID = :ucid ")
@XmlRootElement(name = "RuleEngineAction")
@XmlAccessorType(XmlAccessType.FIELD)
public class RuleEngineAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3392463017105654081L;
	@XmlElement(required = true)
	@Id
	@Column(name = "UCID")
	String UCID;

	@XmlElement(defaultValue = "-1.0")
	@Column(name = "BATTERY_LEVEL")
	float batteryLevel;
	@XmlElement
	@Column(name = "CARRIER")
	String carrier;
	@XmlElement
	@Column(name = "CONNECTIVITY_TYPE")
	String connectivityType;
	@XmlElement(defaultValue = "91.0")
	@Column(name = "LATITUDE")
	float latitude;
	@XmlElement(defaultValue = "181.0")
	@Column(name = "LONGITUDE")
	float longitude;
	@XmlElement
	@Column(name = "TIME_ZONE")
	String timeZone;
	@XmlElement(defaultValue = "-1.0")
	@Column(name = "VELOCITY")
	float velocity;
	@XmlElement
	@Column(name = "LAST_UPDATE")
	String lastUpdate;
	@XmlElement
	@Column(name = "USER_NOTE")
	String userNote;
	@XmlElement
	@Column(name = "CARRIER_CELLULAR_DATA")
	String carrierCellularData;

	public String getUCID() {
		return UCID;
	}

	public void setUCID(String uCID) {
		UCID = uCID;
	}

	public float getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(float batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getConnectivityType() {
		return connectivityType;
	}

	public void setConnectivityType(String connectivityType) {
		this.connectivityType = connectivityType;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getUserNote() {
		return userNote;
	}

	public void setUserNote(String userNote) {
		this.userNote = userNote;
	}

	public String getCarrierCellularData() {
		return carrierCellularData;
	}

	public void setCarrierCellularData(String carrierCellularData) {
		this.carrierCellularData = carrierCellularData;
	}

	@Override
	public String toString() {
		return UCID + " " + batteryLevel + " " + carrier + " " + connectivityType + " " + lastUpdate + " " + latitude
				+ " " + longitude + " " + timeZone + " " + userNote + " " + velocity + " " + carrierCellularData;
	}

	public void merge(RuleEngineAction action) {
		float var = action.getBatteryLevel();
		if (Float.compare(var, 0) != 0) {
			this.setBatteryLevel(var);
		}
		var = action.getLatitude();
		if (Float.compare(var, 0) != 0) {
			this.setLatitude(var);
		}
		var = action.getLongitude();
		if (Float.compare(var, 0) != 0) {
			this.setLongitude(var);
		}
		var = action.getVelocity();
		if (Float.compare(var, 0) != 0) {
			this.setVelocity(var);
		}
		String svar = action.getCarrier();
		if (svar != null) {
			this.setCarrier(svar);
		}
		svar = action.getConnectivityType();
		if (svar != null) {
			this.setConnectivityType(svar);
		}
		svar = action.getTimeZone();
		if (svar != null) {
			this.setTimeZone(svar);
		}
		svar = action.getUserNote();
		if (svar != null) {
			this.setUserNote(svar);
		}
		svar = action.getCarrierCellularData();
		if (svar != null) {
			this.setCarrierCellularData(svar);
		}
	}
}
