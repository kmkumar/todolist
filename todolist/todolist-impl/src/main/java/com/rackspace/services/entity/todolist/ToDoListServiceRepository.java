package com.rackspace.services.entity.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.rackspace.schema.service.todolist.v1.ToDoList;
import com.rackspace.schema.service.todolist.v1.ToDoLists;

@Component("toDoListServiceRepository")
public class ToDoListServiceRepository {

	@Autowired(required = true)
	@Qualifier("toDoListMockDaoImpl")
	ToDoListDao dao;

	public ToDoLists getToDoList(String userId) {
		return dao.getToDoList(userId);
	}

	public ToDoLists getToDoList() {
		return dao.getToDoList();
	}

	public void addToDoList(ToDoList todolist) {
		dao.addToDoList(todolist);
	}

}
