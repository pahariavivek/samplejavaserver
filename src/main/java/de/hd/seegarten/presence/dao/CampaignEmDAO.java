/**
 * (c) 2014 SAP AG or an SAP affiliate company.  All rights reserved.
 *
 * No part of this publication may be reproduced or transmitted in any form or for any purpose
 * without the express permission of SAP AG.  The information contained herein may be changed
 * without prior notice.
 */

package de.hd.seegarten.presence.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hd.seegarten.presence.dto.RuleEngineAction;

public class CampaignEmDAO {
	private EntityManagerFactory emf;
	private static final Logger logger = LoggerFactory.getLogger(CampaignEmDAO.class);

	public CampaignEmDAO(EntityManagerFactory emf) {
		setEmf(emf);
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public List<String> addCampaign(List<RuleEngineAction> actions) {
		List<String> failed = new ArrayList<>();
		for (RuleEngineAction action : actions) {
			action.setLastUpdate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()));
			logger.debug("addCampaign {}", action.toString());
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				em.persist(action);
				tx.commit();
			} catch (Exception e) {
				logger.debug("addCampaign failed {}", e.getMessage());
				failed.add(action.getUCID());
			}
		}
		return failed;
	}

	public List<String> updateCampaign(List<RuleEngineAction> actions) {
		List<String> failed = new ArrayList<>();
		for (RuleEngineAction action : actions) {
			logger.debug("updateCampaign {}", action.toString());
			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			RuleEngineAction foundAction = em.find(RuleEngineAction.class, action.getUCID());
			if (foundAction == null)
				failed.add(action.getUCID());
			try {
				tx.begin();
				foundAction.merge(action);
				foundAction.setLastUpdate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(new Date()));
				em.merge(foundAction);
				tx.commit();
			} catch (Exception e) {
				logger.debug("updateCampaign failed {}", e.getMessage());
				failed.add(action.getUCID());
			}
		}
		return failed;
	}

	public List<RuleEngineAction> getCampaign(String ucid) {
		logger.debug("getCampaign {}", ucid);
		EntityManager em = emf.createEntityManager();
		return em.createNamedQuery("getUCID").setParameter("ucid", ucid).getResultList();
	}

	public List<RuleEngineAction> getCampaigns(List<String> ucids) {
		ArrayList<RuleEngineAction> newList = new ArrayList<RuleEngineAction>();
		logger.debug("getCampaigns");
		EntityManager em = emf.createEntityManager();
		for (String ucid : ucids) {
			try {
				newList.add(
						(RuleEngineAction) em.createNamedQuery("getUCID").setParameter("ucid", ucid).getSingleResult());
			} catch (Exception e) {

			}
		}
		return newList;
	}
}
