package com.poli.policlass.service;

import com.poli.policlass.model.entity.Profile;
import com.poli.policlass.model.entity.User;
import com.poli.policlass.model.entity.UserProfile;
import com.poli.policlass.repository.ProfileRepository;
import com.poli.policlass.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SignupService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Transactional
    public User cadastrar(User usuario){
        User salvo = userRepository.save(usuario);
        Profile perfilAluno = Profile.perfilAluno();
        UserProfile userProfile = new UserProfile(usuario, perfilAluno);
        profileRepository.save(userProfile);
        return salvo;
    }
}
