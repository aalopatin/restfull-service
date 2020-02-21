package ru.watchlist.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.watchlist.domain.enums.Role;

import javax.persistence.*;
import java.util.Set;

import static ru.watchlist.config.Constants.ID_GENERATOR;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usr")
public class User {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = ID_GENERATOR)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;

	@Column(unique = true)
	private String email;

	private boolean active;
	private String activationCode;
	
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "usr_role", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;

	public boolean isAdmin() { return roles.contains(Role.ADMIN); }

	public boolean hasAuthority(Role role) {return roles.contains(role); }

}
