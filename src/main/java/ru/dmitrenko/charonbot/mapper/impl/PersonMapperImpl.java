package ru.dmitrenko.charonbot.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dmitrenko.charonbot.mapper.Mapper;
import ru.dmitrenko.charonbot.model.domain.PartyStatus;
import ru.dmitrenko.charonbot.model.domain.Person;
import ru.dmitrenko.charonbot.model.domain.PersonStatus;
import ru.dmitrenko.charonbot.model.request.PersonRequest;
import ru.dmitrenko.charonbot.model.view.PersonView;

@Service
@RequiredArgsConstructor
public class PersonMapperImpl implements Mapper<Person, PersonRequest, PersonView> {

	private final TaskMapperImpl taskMapper;

	@Override
	public Person toEntity(PersonRequest request) {
		return new Person()
			.setSteamId(request.getSteamId())
			.setName(request.getName())
			.setDescription(request.getDescription())
			.setNickname(request.getNickname())
			.setPersonStatus(PersonStatus.fromValue(request.getPersonStatus()))
			.setPartyStatus(PartyStatus.fromValue(request.getPartyStatus()))
			.setPrintLink(request.getPrintLink());
	}

	@Override
	public Person merge(Person entity, PersonRequest request) {
		return entity
			.setSteamId(request.getSteamId() == null ? entity.getSteamId() : request.getSteamId())
			.setName(request.getName() == null ? entity.getName(): request.getName())
			.setDescription(request.getDescription() == null ? entity.getDescription() : request.getDescription())
			.setNickname(request.getNickname() == null ? entity.getNickname() : request.getNickname())
			.setPersonStatus(request.getPersonStatus() == null ? entity.getPersonStatus() : PersonStatus.fromValue(request.getPersonStatus()))
			.setPartyStatus(request.getPartyStatus() == null ? entity.getPartyStatus() : PartyStatus.fromValue(request.getPartyStatus()))
			.setPrintLink(request.getPrintLink() == null ? entity.getPrintLink() : request.getPrintLink());
	}

	@Override
	public PersonView toView(Person entity) {
		return new PersonView()
			.setSteamId(entity.getSteamId())
			.setName(entity.getName())
			.setDescription(entity.getDescription())
			.setNickname(entity.getNickname())
			.setPersonStatus(entity.getPersonStatus().getValue())
			.setPartyStatus(entity.getPartyStatus().getValue())
			.setPrintLink(entity.getPrintLink())
			.setCashAccount(entity.getCashAccount())
			.setTasks(taskMapper.toViews(entity.getTasks()))
			.setCreated(entity.getCreated());
	}
}
