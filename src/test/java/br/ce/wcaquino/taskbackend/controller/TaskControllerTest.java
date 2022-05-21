package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {
	
	@Mock
	private TaskRepo repo;
	
	@InjectMocks
	private TaskController controller = new TaskController();
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	

	@Test
	public void doNotSaveTaskWithoutDescription() throws ValidationException {
		exception.expect(ValidationException.class);
		exception.expectMessage("Fill the task description");

		Task todo = new Task();
		todo.setDueDate(LocalDate.now());	
		controller.save(todo);
	}

	@Test
	public void doNotSaveTaskWithoutDate() throws ValidationException {
		exception.expect(ValidationException.class);
		exception.expectMessage("Fill the due date");

		Task todo = new Task();
		todo.setTask("Test");
		controller.save(todo);

	}

	@Test
	public void doNotSaveTaskWithDatePast() throws ValidationException {
		exception.expect(ValidationException.class);
		exception.expectMessage("Due date must not be in past");

		Task todo = new Task();
		todo.setTask("Test");
		todo.setDueDate(LocalDate.of(2021, 1, 1));
		controller.save(todo);
	}

	@Test
	public void saveTaskSuccessfully() throws ValidationException {
	
		Task todo = new Task();
		todo.setDueDate(LocalDate.now());
		todo.setTask("Test");
		controller.save(todo);
	}	

}
