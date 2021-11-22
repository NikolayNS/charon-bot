package ru.dmitrenko.charonbot.service.domain.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitrenko.charonbot.mapper.impl.PersonMapperImpl;
import ru.dmitrenko.charonbot.model.domain.PartyStatus;
import ru.dmitrenko.charonbot.model.domain.Person;
import ru.dmitrenko.charonbot.model.domain.PersonStatus;
import ru.dmitrenko.charonbot.model.request.PersonRequest;
import ru.dmitrenko.charonbot.model.view.PersonView;
import ru.dmitrenko.charonbot.repository.PersonRepository;
import ru.dmitrenko.charonbot.service.domain.PersonDomainService;
import ru.dmitrenko.charonbot.util.CashAccountOperation;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonDomainServiceImpl implements PersonDomainService {

	private final PersonRepository personRepository;

	private final PersonMapperImpl personMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public PersonView add(PersonRequest request) {
		if (personRepository.findBySteamId(request.getSteamId()).isPresent())
			throw new EntityExistsException(String.format("Person with steamId [%s] already exist", request.getSteamId()));

		var person = personMapper.toEntity(request);
		person = personRepository.saveAndFlush(person);

		return personMapper.toView(person);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public PersonView update(PersonRequest request) {
		var person = getPerson(request.getSteamId());

		person = personMapper.merge(person, request);
		person = personRepository.saveAndFlush(person);

		return personMapper.toView(person);
	}

	private Person getPerson(Long steamId) {
		return personRepository
			.findBySteamId(steamId)
			.orElseThrow(() -> new EntityNotFoundException(String.format("Person with steamId [%s] not found", steamId)));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long updateCashAccount(Long steamId, Long amount, CashAccountOperation operation) {
		var person = getPerson(steamId);
		var cashAccount = person.getCashAccount();

		cashAccount = switch(operation) {
			case PLUS -> cashAccount + amount;
			case MINUS -> cashAccount - amount;
			case MINUS_ALL -> 0L;
		};

		person.setCashAccount(cashAccount);
		personRepository.saveAndFlush(person);

		return person.getCashAccount();
	}

	@Override
	@Transactional(readOnly = true)
	public PersonView getBySteamId(Long steamId) {
		return personMapper.toView(getPerson(steamId));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonView> getAllByNickname(String nickname) {
		return personMapper.toViews(personRepository.findAllByNickname(nickname));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonView> getAllByPersonStatus(PersonStatus personStatus) {
		return personMapper.toViews(personRepository.findAllByPersonStatus(personStatus));
	}

	@Override
	@Transactional(readOnly = true)
	public List<PersonView> getAllByPartyStatus(PartyStatus partyStatus) {
		return personMapper.toViews(personRepository.findAllByPartyStatus(partyStatus));
	}

	@Override
	@Transactional(readOnly = true)
	public Long getPersonCashAccount(Long steamId) {
		return getPerson(steamId).getCashAccount();
	}
}
