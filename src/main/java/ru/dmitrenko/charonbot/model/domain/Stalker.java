package ru.dmitrenko.charonbot.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.dmitrenko.charonbot.util.PostgreSQLEnumType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity(name = "stalker")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Table(name = "stalker")
public class Stalker extends BaseEntity {

	@Column(name = "steam_id", nullable = false)
	private String steamId;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "personal_account")
	private BigDecimal personalAccount;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "stalker_status", nullable = false)
	@Type(type = "pgsql_enum")
	private StalkerStatus status;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "print_link", nullable = false)
	private String printLink;
}
