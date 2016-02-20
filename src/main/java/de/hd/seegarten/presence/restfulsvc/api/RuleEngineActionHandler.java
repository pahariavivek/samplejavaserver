/**
 * (c) 2014 SAP AG or an SAP affiliate company.  All rights reserved.
 *
 * No part of this publication may be reproduced or transmitted in any form or for any purpose
 * without the express permission of SAP AG.  The information contained herein may be changed
 * without prior notice.
 */

package de.hd.seegarten.presence.restfulsvc.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import de.hd.seegarten.presence.dto.RuleEngineAction;

@Path("/presence")
public interface RuleEngineActionHandler {

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response handleAction(List<RuleEngineAction> CampaignInfo);

	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	public Response updateAction(List<RuleEngineAction> CampaignInfo);

	@GET
	@Produces("application/json")
	@Path("/{ucid}")
	public RuleEngineAction getAction(@PathParam("ucid") String ucid);

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/ucids")
	public List<RuleEngineAction> getActions(List<String> ucids);

}
