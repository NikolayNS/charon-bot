package ru.dmitrenko.charonbot.service.domain;

import ru.dmitrenko.charonbot.model.domain.PartyStatus;
import ru.dmitrenko.charonbot.model.domain.PersonStatus;
import ru.dmitrenko.charonbot.model.request.PersonRequest;
import ru.dmitrenko.charonbot.model.view.PersonView;
import ru.dmitrenko.charonbot.util.CashAccountOperation;

import java.util.List;

public interface PersonDomainService {

	PersonView add(PersonRequest request);

	PersonView update(PersonRequest request);

	Long updateCashAccount(Long steamId, Long amount, CashAccountOperation operation);

	PersonView getBySteamId(Long steamId);

	List<PersonView> getAllByNickname(String nickname);

	List<PersonView> getAllByPersonStatus(PersonStatus personStatus);

	List<PersonView> getAllByPartyStatus(PartyStatus partyStatus);

	Long getPersonCashAccount(Long steamId);
}
