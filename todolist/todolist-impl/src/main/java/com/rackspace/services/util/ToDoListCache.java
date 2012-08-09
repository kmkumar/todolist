package com.rackspace.services.util;

import com.rackspace.schema.service.todolist.v1.ToDoList;
import com.rackspace.schema.service.todolist.v1.ToDoLists;
import com.rackspace.services.util.exception.BaseException;

public class ToDoListCache {

	static {
		toDoLists = new ToDoLists();
	}

	private static ToDoLists toDoLists;

	public static ToDoLists getToDoList() {
		return toDoLists;
	}

	public static ToDoLists getToDoList(String userId) {
		ToDoList toDoListLocal = null;
		for (ToDoList toDoList : toDoLists.getToDoList()) {
			if (toDoList.getUserId().equals(userId)) {
				toDoListLocal = toDoList;
				break;
			}
		}
		if (toDoListLocal == null) {
			return new ToDoLists();
		} else {
			ToDoLists toDoListsx = new ToDoLists();
			toDoListsx.getToDoList().add(toDoListLocal);
			return toDoListsx;
		}
	}

	public static void addToDoList(ToDoList toDoListParm) {

		// check if already exists.
		String title = toDoListParm.getTitle();
		String userId = toDoListParm.getUserId();

		for (ToDoList toDoList : toDoLists.getToDoList()) {
			if (toDoList.getUserId().equals(userId)
					&& toDoList.getTitle().equals(title)) {
				BaseException be = new BaseException(
						"ToDoList already exists with this name", null,
						"100200");

				throw be;
			}
		}
		toDoLists.getToDoList().add(toDoListParm);
	}
}
