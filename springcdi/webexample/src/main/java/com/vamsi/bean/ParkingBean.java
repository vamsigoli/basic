package com.vamsi.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.vamsi.spring.jpa.entities.ParkingSpace;
import com.vamsi.spring.jpa.types.ParkingLocation;

@Stateless
public class ParkingBean {
	
	@PersistenceContext(name="entities")
	EntityManager em;
	
	@Transactional(value=TxType.SUPPORTS)
	public List<ParkingSpace> findAvailableParking(ParkingLocation pl) {
		
	TypedQuery<ParkingSpace> query = em.createNamedQuery(
										"ParkingSpace.findAllavailableInLocation", ParkingSpace.class)
										.setParameter("location", pl);
	
	return query.getResultList();	
	}

}
