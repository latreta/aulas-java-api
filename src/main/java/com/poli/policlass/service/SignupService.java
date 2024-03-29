package com.poli.policlass.service;

import com.poli.policlass.model.entity.Profile;
import com.poli.policlass.model.entity.User;
import com.poli.policlass.model.entity.UserProfile;
import com.poli.policlass.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SignupService {
    @Autowired
    private UserService userService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ActivationTokenService activationTokenService;

    @Transactional
    public User cadastrar(User usuario){
        User salvo = userService.cadastrarUsuario(usuario);
        Profile perfilAluno = Profile.perfilAluno();
        UserProfile userProfile = new UserProfile(usuario, perfilAluno);
        profileRepository.save(userProfile);
        activationTokenService.generateActivationToken(salvo);
        return salvo;
    }
}
