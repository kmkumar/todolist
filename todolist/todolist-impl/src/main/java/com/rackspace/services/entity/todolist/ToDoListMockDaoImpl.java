package com.rackspace.services.entity.todolist;

import org.springframework.stereotype.Component;

import com.rackspace.schema.service.todolist.v1.ToDoList;
import com.rackspace.schema.service.todolist.v1.ToDoLists;
import com.rackspace.services.util.ToDoListCache;

@Component("toDoListMockDaoImpl")
public class ToDoListMockDaoImpl implements ToDoListDao {

	public ToDoLists getToDoList(String userId) {
		return ToDoListCache.getToDoList(userId);
	}

	public ToDoLists getToDoList() {
		return ToDoListCache.getToDoList();
	}

	public void addToDoList(ToDoList todolist) {
		ToDoListCache.addToDoList(todolist);
	}
}
