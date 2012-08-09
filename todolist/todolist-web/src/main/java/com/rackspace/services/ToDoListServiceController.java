package com.rackspace.services;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rackspace.schema.service.status.v1.Status;
import com.rackspace.schema.service.todolist.v1.Task;
import com.rackspace.schema.service.todolist.v1.ToDoList;
import com.rackspace.schema.service.todolist.v1.ToDoLists;
import com.rackspace.services.util.DateUtils;
import com.rackspace.services.util.exception.BaseException;

/**
 * Controller Implementation for todolist
 * 
 */

@Controller
public class ToDoListServiceController {

	private static final XLogger LOGGER = XLoggerFactory
			.getXLogger(ToDoListServiceController.class.getName());

	@Autowired(required = true)
	ToDoListService toDoListService;

	/**
	 * Get the todolist for a user
	 */

	@RequestMapping(value = "user/{userId}/todolists", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> handleGetToDoLists(

	@PathVariable("userId") String userId, HttpServletRequest servletRequest) {

		LOGGER.entry();

		ToDoLists toDoLists = toDoListService.getToDoList(userId);

		LOGGER.exit();

		if (toDoLists == null || toDoLists.getToDoList().size() == 0) {
			Status staus = new Status();
			staus.setStatusMsg("No Data Found");
			staus.setErrorCode("100100");
			staus.setErrorMsg("The user id is not found");
			return new ResponseEntity<Object>(staus, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(toDoLists, HttpStatus.OK);
		}

	}

	/**
	 * Get all the todolist
	 */

	@RequestMapping(value = "todolists", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> handleGetToDoLists(

	HttpServletRequest servletRequest) {

		LOGGER.entry();

		ToDoLists toDoLists = toDoListService.getToDoList();

		LOGGER.exit();

		return new ResponseEntity<Object>(toDoLists, HttpStatus.OK);

	}

	/**
	 * Add a new todolist
	 */

	@RequestMapping(value = "user/{userId}/todolistadd", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> handleAddToDoList(
			@RequestBody ToDoList toDoList,
			@PathVariable("userId") String userId,
			HttpServletRequest servletRequest) {

		LOGGER.entry();

		LOGGER.debug(">>> in ToDoList for userid - {}", userId);
		Status status = new Status();
		boolean addFailed = false;
		status.setStatusMsg("ToDoList Added");

		toDoList.setUserId(userId);

		if (toDoList.getTitle() == null || toDoList.getToDoListId() == null) {
			status.setStatusMsg("Invalid Data");
			status.setErrorCode("100300");
			status.setErrorMsg("Invalid Data");
			addFailed = true;
		} else {
			try {
				toDoListService.addToDoList(toDoList);
			} catch (BaseException be) {
				addFailed = true;
				status.setStatusMsg("ToDoList already exists with this name");
				status.setErrorCode(be.getErrCode());
				status.setErrorMsg(be.getErrMsg());
			}
		}

		LOGGER.exit();

		if (addFailed) {
			return new ResponseEntity<Object>(status, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(status, HttpStatus.CREATED);
		}

	}

	/**
	 * Add one test data
	 */

	@RequestMapping(value = "addtestdata", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> handleAddTestData(

	HttpServletRequest servletRequest) {

		tempadd();

		ToDoLists toDoLists = toDoListService.getToDoList();

		return new ResponseEntity<Object>(toDoLists, HttpStatus.OK);

	}

	private void tempadd() {
		ToDoList toDoList = new ToDoList();
		toDoList.setTitle("TodoList-1");
		toDoList.setUserId("user1");
		toDoList.setToDoListId(1L);

		Task task = new Task();
		task.setTitle("task1");
		task.setTaskId(1L);
		task.setStatus("O");
		task.setToDoListId(1L);
		task.setDueDate(DateUtils.getGDate("20120815"));
		toDoList.getTask().add(task);

		try {
			toDoListService.addToDoList(toDoList);
		} catch (BaseException be) {
			be.printStackTrace();
		}

	}
}
