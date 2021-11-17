package ru.dmitrenko.charonbot.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.dmitrenko.charonbot.util.PostgreSQLEnumType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "person")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString(callSuper = true, exclude = "tasks")
@EqualsAndHashCode(callSuper = true, exclude = "tasks")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Table(name = "person")
public class Person extends BaseEntity {

	@Column(name = "steam_id", nullable = false)
	private Long steamId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "description", nullable = false)
	private String description;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "person_status", nullable = false)
	@Type(type = "pgsql_enum")
	private PersonStatus personStatus;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "party_status", nullable = false)
	@Type(type = "pgsql_enum")
	private PartyStatus partyStatus;

	@Column(name = "print_link", nullable = false)
	private String printLink;

	@Column(name = "cash_account")
	private Long cashAccount;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "person")
	private Set<Task> tasks = new HashSet<>();
}
