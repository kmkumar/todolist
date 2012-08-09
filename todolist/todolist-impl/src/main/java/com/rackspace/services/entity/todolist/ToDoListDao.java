package com.rackspace.services.entity.todolist;

import com.rackspace.schema.service.todolist.v1.ToDoList;
import com.rackspace.schema.service.todolist.v1.ToDoLists;

public interface ToDoListDao {

	ToDoLists getToDoList(String userId);

	ToDoLists getToDoList();

	void addToDoList(ToDoList todolist);

}
