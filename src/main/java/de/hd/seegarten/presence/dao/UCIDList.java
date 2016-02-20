/**
 * (c) 2014 SAP AG or an SAP affiliate company.  All rights reserved.
 *
 * No part of this publication may be reproduced or transmitted in any form or for any purpose
 * without the express permission of SAP AG.  The information contained herein may be changed
 * without prior notice.
 */

package de.hd.seegarten.presence.dao;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UCIDList {
	@XmlElementWrapper(name = "UCIDs")
	@XmlElement
	private ArrayList<String> ucids;

	public ArrayList<String> getUcids() {
		return ucids;
	}

	public void setUcids(ArrayList<String> ucids) {
		this.ucids = ucids;
	}
}
