package com.poli.policlass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poli.policlass.model.entity.Profile;
import com.poli.policlass.model.entity.User;

@Service
public class AuthorityService {



	@Autowired
	private UserService userService;

	public boolean temAutoridade(Long userID, String perfil) {
		User usuarioLogado = userService.buscarPorID(userID);
		if (usuarioLogado != null) {
			for (Profile profile : usuarioLogado.getProfiles()) {
				if (profile.getDescription().equalsIgnoreCase(perfil)) {
					return true;
				}
			}
		}
		return false;
	}
}
