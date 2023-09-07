package com.safar.service.serviceImpl;

import com.safar.entity.Users;
import com.safar.entity.Wallet;
import com.safar.entity.WalletStatus;
import com.safar.exceptions.UsersException;
import com.safar.repository.UserRepository;
import com.safar.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Users registerCustomer(Users users) {
        if (users == null)
            throw new UsersException("Invalid users Details");
        Wallet wallet = new Wallet();
        wallet.setBalance(0.0f);
        wallet.setStatus(WalletStatus.Active);
        wallet.setUser(users);
        users.setWallet(wallet);
        return userRepository.save(users);
    }

    @Override
    public Users getUserDetailsByEmail(String email) throws UsersException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsersException("Users Not found with Email: " + email));
    }

    @Override
    public List<Users> getAllUsersDetails() throws UsersException {
        List<Users> usersList = userRepository.findAll();
        if (usersList.isEmpty())
            throw new UsersException("No Users found");
        return usersList;
    }

    @Override
    public Users updateUserDetailsByEmail(String email, Users users) throws UsersException {
        Users users1 = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsersException("Users Not found with Email: " + email));
        users1.setUsername(users.getUsername());
        users1.setPhone(users.getPhone());
        users1.setAddress(users.getAddress());
        return userRepository.save(users1);
    }

    @Override
    public Users deleteUserEmail(String email) throws UsersException {
        Users users = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsersException("Users Not found with Email: " + email));
        log.info(users.toString());
        userRepository.delete(users);
        return users;
    }

    @Override
    public List<Users> getAllUsersDetailsByRole(String role) throws UsersException {
        List<Users> usersList = userRepository.findAllByRole("ROLE_" + role.toUpperCase());
        if (usersList.isEmpty())
            throw new UsersException("No Users found");
        return usersList;
    }
}
