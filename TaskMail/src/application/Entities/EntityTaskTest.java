package application.Entities;

/*import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EntityTaskTest {

	@Test
	final void testVoidConstuct() {
		EntityTask task = new EntityTask();
		task.setIdParent(0);
		task.setIdTask(0);
		task.setIdUrgency(0);
		task.setIsDone(0);
		task.setBode(null);
		task.setDateCreate(null);
		task.setDateEnd(null);
		task.setDateStrart(null);
		task.setDescription(null);
		task.setSupervisor(null);
		task.setTaskCol(null);
		task.setTitle(null);
		task.setUrgency(null);
		
		EntityTask taskTest = new EntityTask();
		Assert.assertEquals(task.getIdParent(),taskTest.getIdParent()); 
	}
	
	@Test
	final void testnotvoidConstruct() {
		Date date = new Date();
		
		EntityTask task = new EntityTask();
		task.setIdParent(1);
		task.setIdTask(1);
		task.setIdUrgency(1);
		task.setIsDone(1);
		task.setBode("Body");
		
		task.setDateCreate(date);
		task.setDateEnd(date);
		task.setDateStrart(date);
		
		task.setDescription("Description");
		task.setSupervisor("Supervisor");
		task.setTaskCol("TaskCol");
		task.setTitle("Title");
		task.setUrgency("Urgency");
		
		EntityTask taskTest = new EntityTask(1,"Title","Body",1,date,date,date,"Supervisor",1,"TaskCol",1,"Description");
		Assert.assertEquals(task.getDescription(),taskTest.getDescription());
		Assert.assertEquals(task.getBode(),taskTest.getBode());
		Assert.assertEquals(task.getSupervisor(),taskTest.getSupervisor());
		Assert.assertEquals(task.getTaskCol(),taskTest.getTaskCol());
		Assert.assertEquals(task.getTitle(),taskTest.getTitle());
		//Assert.assertEquals(task.getUrgency(),taskTest.getUrgency());
		Assert.assertEquals(task.getDateCreate(),taskTest.getDateCreate());
		Assert.assertEquals(task.getDateEnd(),taskTest.getDateEnd());
		Assert.assertEquals(task.getDateStrart(),taskTest.getDateStrart());
		Assert.assertEquals(task.getIdParent(),taskTest.getIdParent());
		Assert.assertEquals(task.getIdTask(),taskTest.getIdTask());
		Assert.assertEquals(task.getIdUrgency(),taskTest.getIdUrgency());
		Assert.assertEquals(task.getIsDone(),taskTest.getIsDone());
	}

}*/
