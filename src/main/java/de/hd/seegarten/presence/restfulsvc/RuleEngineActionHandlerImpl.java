/**
 * (c) 2014 SAP AG or an SAP affiliate company.  All rights reserved.
 *
 * No part of this publication may be reproduced or transmitted in any form or for any purpose
 * without the express permission of SAP AG.  The information contained herein may be changed
 * without prior notice.
 */

package de.hd.seegarten.presence.restfulsvc;

import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hd.seegarten.presence.dao.CampaignEmDAO;
import de.hd.seegarten.presence.dto.RuleEngineAction;
import de.hd.seegarten.presence.restfulsvc.api.RuleEngineActionHandler;

public class RuleEngineActionHandlerImpl implements RuleEngineActionHandler {
	CampaignEmDAO campaignDao;
	private static final Logger logger = LoggerFactory.getLogger(RuleEngineActionHandlerImpl.class);

	public RuleEngineActionHandlerImpl(CampaignEmDAO campaignDao) {
		super();
		this.campaignDao = campaignDao;
	}

	@Override
	public Response handleAction(List<RuleEngineAction> campaignInfo) {
		try {

			logger.debug(campaignInfo.toString());
			List<String> failed = campaignDao.addCampaign(campaignInfo);
			if (failed.isEmpty())
				return Response.status(201).build();
			GenericEntity<List<String>> entity = new GenericEntity<List<String>>(failed) {
			};
			return Response.status(202).entity(entity).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@Override
	public RuleEngineAction getAction(String ucid) {
		List<RuleEngineAction> result = campaignDao.getCampaign(ucid);
		return result.size() > 0 ? result.get(0) : null;
	}

	@Override
	public Response updateAction(List<RuleEngineAction> campaignInfo) {
		logger.debug(campaignInfo.toString());
		try {
			List<String> failed = campaignDao.updateCampaign(campaignInfo);
			if (failed.isEmpty())
				return Response.status(201).build();
			GenericEntity<List<String>> entity = new GenericEntity<List<String>>(failed) {
			};
			return Response.status(202).entity(entity).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}
	}

	@Override
	public List<RuleEngineAction> getActions(List<String> ucids) {
		return campaignDao.getCampaigns(ucids);
	}
}
