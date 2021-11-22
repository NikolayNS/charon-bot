package ru.dmitrenko.charonbot.service.domain.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitrenko.charonbot.mapper.impl.TaskMapperImpl;
import ru.dmitrenko.charonbot.model.domain.Person;
import ru.dmitrenko.charonbot.model.domain.TaskStatus;
import ru.dmitrenko.charonbot.model.request.TaskRequest;
import ru.dmitrenko.charonbot.model.view.TaskView;
import ru.dmitrenko.charonbot.repository.PersonRepository;
import ru.dmitrenko.charonbot.repository.TaskRepository;
import ru.dmitrenko.charonbot.service.domain.TaskDomainService;
import ru.dmitrenko.charonbot.util.Pair;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskDomainServiceImpl implements TaskDomainService {

	private final TaskRepository taskRepository;
	private final PersonRepository personRepository;

	private final TaskMapperImpl taskMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public TaskView add(TaskRequest request) {
		var task = taskMapper.toEntity(new Pair<TaskRequest, Person>()
			.setLeft(request)
			.setRight(personRepository.findBySteamId(request.getPersonSteamId())
				.orElseThrow(() -> new EntityNotFoundException(String.format("Person with steamId [%s] not found ", request.getPersonSteamId())))));

		task = taskRepository.saveAndFlush(task);

		return taskMapper.toView(task);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public TaskView update(UUID id, TaskRequest request) {
		var task = taskRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(String.format("Task with id [%s] not found ", request.getPersonSteamId())));

		task = taskMapper.merge(task, new Pair<TaskRequest, Person>()
			.setLeft(request)
			.setRight(personRepository.findBySteamId(request.getPersonSteamId())
				.orElseThrow(() -> new EntityNotFoundException(String.format("Person with steamId [%s] not found ", request.getPersonSteamId())))));

		task = taskRepository.saveAndFlush(task);

		return taskMapper.toView(task);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TaskView> getAllActiveTasks() {
		return taskMapper.toViews(taskRepository.findAllByTaskStatusOrTaskStatus(TaskStatus.IN_PROGRESS, TaskStatus.PROLONG));
	}
}
