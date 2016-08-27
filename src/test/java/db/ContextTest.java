package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import jacklow.model.Vehiculo;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {});
	}
	
	@Test
	public void getDelVehiculo() {
		EntityManager entityManager = 
				PerThreadEntityManagers.
				getEntityManager();
		
		Vehiculo vehiculo = entityManager.find(Vehiculo.class, 1);
		assertEquals(1, vehiculo.getId());
		assertEquals("unaVtu", vehiculo.getVtu());
		assertEquals("unaPatente", vehiculo.getPatente());
		
		Vehiculo otroVehiculo = entityManager
				.find(Vehiculo.class, 1);
		Assert.assertTrue(vehiculo == 
				otroVehiculo);
		
		
	}

}
