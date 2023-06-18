package br.com.kenzie.learningSpring.service;

import br.com.kenzie.learningSpring.dto.CreditDepositDto;
import br.com.kenzie.learningSpring.dto.UserDto;
import br.com.kenzie.learningSpring.exception.AppException;
import br.com.kenzie.learningSpring.model.User;
import br.com.kenzie.learningSpring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private void checkEmailAndCpf(UserDto userData) {
        if(userRepository.existsUserByCpf(userData.cpf())){
            throw new AppException("cpfAlreadyInUse", HttpStatus.CONFLICT);

        }
        if(userRepository.existsUserByEmail(userData.email())){
            throw new AppException("emailAlreadyInUse", HttpStatus.CONFLICT);

        }
    }

    public User createUser(final UserDto userData){
        final User newUser = new User(userData.name(), userData.cpf(),userData.email(),
                userData.password(),userData.type());
        checkEmailAndCpf(userData);


        return userRepository.save(newUser);
    }



    public List<User> readUser(){
        return userRepository.findAll();
    }

    public User retriveUser (final long id)  {
        return userRepository.findById(id).orElseThrow(
                () -> new AppException("userNotFound", HttpStatus.NOT_FOUND));
    }
    public User updatedUser (UserDto userData, long id)  {
        final User user = userRepository.findById(id).orElseThrow(
                () -> new AppException("userNotFound", HttpStatus.NOT_FOUND));
        checkEmailAndCpf(userData);

        user.setName(userData.name());
        user.setCpf(userData.cpf());
        user.setEmail(userData.email());
        user.setType(userData.type());

        return userRepository.save(user);

    }
    public void deleteUser(final long id) throws  AppException {

        final User foundUser = userRepository.findById(id).orElseThrow(
                () ->  new AppException("userNotFound", HttpStatus.NOT_FOUND));
        System.out.println(foundUser.getName());
        userRepository.deleteById(foundUser.getId());

    }
    public User createDeposit(final CreditDepositDto depositData, final long id)  {
        final User foundUser = userRepository.findById(id).orElseThrow(
                () ->  new AppException("userNotFound", HttpStatus.NOT_FOUND));

         final float currentBalance = foundUser.getBalance();

         foundUser.setBalance(currentBalance  + depositData.value());
        return userRepository.save(foundUser);
    }


}
