package com.cybertek.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cybertek.data.DataGenerator;
import com.cybertek.dto.TaskDTO;
import com.cybertek.dto.UserDTO;
import com.cybertek.entity.Project;
import com.cybertek.entity.Task;
import com.cybertek.entity.User;
import com.cybertek.implementation.TaskServiceImpl;
import com.cybertek.util.Status;

@Service
public class TaskService implements TaskServiceImpl {
	
	@Override
	public List<Task> getListOfTask() {
		return DataGenerator.getTasks();
	}
	
	@Override
	public List<Task> getListOfTaskByUser(User user) {
		return DataGenerator.getTasks().stream().filter(x -> x.getAssignedEmployee().equals(user)).collect(Collectors.toList());

	}

	@Override
	public Task getTaskById(Long id) {
		// TODO Auto-generated method stub
		return DataGenerator.getTasks().stream().filter(x -> x.getId() == id).findFirst().get();
	}

	@Override
	public List<TaskDTO> getListOfTaskDTO() {
		return DataGenerator.converterTaskDTO(getListOfTask());
	}

	@Override
	public List<Task> getListOfTask(Project projectCode) {
		// TODO Auto-generated method stub
		return DataGenerator.getTasks().stream().filter(x -> x.getAssignedProject().getProjectCode().equals(projectCode.getProjectCode()))
				.collect(Collectors.toList());
	}

	@Override
	public TaskDTO getTaskDTOById(Long id) {
		return getListOfTaskDTO().stream().filter(x -> x.getId() == id).findFirst().get();

	}

	@Override
	public List<TaskDTO> updateTaskDTO(TaskDTO taskDTO) {

		List<TaskDTO> tasks = DataGenerator.converterTaskDTO(getListOfTask()).stream()
				.filter(x -> x.getId() != taskDTO.getId()).collect(Collectors.toList());

		TaskDTO task = getTaskDTOById(taskDTO.getId());
		task.setTaskDetail(taskDTO.getTaskDetail());
		task.setTaskDetail(taskDTO.getTaskSubject());
		task.setAssignedEmployee(taskDTO.getAssignedEmployee());

		tasks.add(task);

		return tasks;
	}

	@Override
	public List<TaskDTO> deleteTask(Long id) {
		return getListOfTaskDTO().stream().filter(x -> x.getId() != id).collect(Collectors.toList());
	}

	@Override
	public List<TaskDTO> getEmployeeTasks(UserDTO user) {
		return getListOfTaskDTO().stream().filter(x -> x.getAssignedEmployee().equals(user) && x.getTaskStatus() != Status.COMPLETED)
				.collect(Collectors.toList());
	}

	@Override
	public List<TaskDTO> updateTaskDTOForEmployee(UserDTO user, TaskDTO taskDTO) {
		// TODO Auto-generated method stub
		List<TaskDTO> tasks = getListOfTaskDTO().stream().filter(x -> x.getId() != taskDTO.getId()
				&& x.getAssignedEmployee().equals(DataGenerator.activeUser) && x.getTaskStatus() != Status.COMPLETED)
				.collect(Collectors.toList());

		TaskDTO task = getTaskDTOById(taskDTO.getId());

		task.setTaskStatus(taskDTO.getTaskStatus());

		tasks.add(task);

		return tasks;
	}

	@Override
	public List<TaskDTO> archiveList(UserDTO user) {
		return getListOfTaskDTO().stream()
				.filter(x -> x.getAssignedEmployee().equals(DataGenerator.activeUser) && x.getTaskStatus() == Status.COMPLETED)
				.collect(Collectors.toList());
	}



	@Override
	public List<Task> getListOfTask(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getListOfTask(User manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getListOfTask(User manager, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task insert(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task update(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Task task) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	

}
