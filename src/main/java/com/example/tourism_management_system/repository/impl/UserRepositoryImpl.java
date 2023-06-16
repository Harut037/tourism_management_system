package com.example.tourism_management_system.repository.impl;

import com.example.tourism_management_system.model.entities.UserEntity;
import com.example.tourism_management_system.model.pojos.EditInfo;
import com.example.tourism_management_system.model.pojos.User;
import com.example.tourism_management_system.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional ( readOnly = false )
public class UserRepositoryImpl extends SimpleJpaRepository <UserEntity, Long> implements UserRepository {
    
    @PersistenceContext
    private final EntityManager entityManager;
    
    @Autowired
    public UserRepositoryImpl (final EntityManager entityManager) {
        super(UserEntity.class, entityManager);
        this.entityManager = entityManager;
    }
    
//    @Override
//    public Optional <UserEntity> findByCardNumber (final String cardNumber) {
//        String jpql = "SELECT u FROM UserEntity u inner join CardEntity p WHERE p.cardNumber = :cardNumber";
//        return entityManager
//                .createQuery(jpql, UserEntity.class)
//                .setParameter("cardNumber", cardNumber)
//                .getResultList()
//                .stream()
//                .findFirst();
//    }
    
    @Override
    public Optional <UserEntity> findByEmail (final String email) {
        String jpql = "SELECT u FROM UserEntity u  WHERE u.email = :email";
        return entityManager
                .createQuery(jpql, UserEntity.class)
                .setParameter("email", email)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    @Override
    public Optional <UserEntity> findByPhoneNumber (final String phoneNumber) {
        String jpql = "SELECT u FROM UserEntity u  WHERE u.phoneNumber = :phoneNumber";
        return entityManager
                .createQuery(jpql, UserEntity.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList()
                .stream()
                .findFirst();
    }
    
    @Override
    public String signIn (final String email, final String password) {
        Optional <UserEntity> user = this.findByEmail(email);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(new BCryptPasswordEncoder().encode(password))) {
                return "Success";
            }
            return "Invalid Password";
        }
        return "User Not Found By This Email";
    }
    
    @Override
    public String forgotPassword (final String email) {
        Optional <UserEntity> user = this.findByEmail(email);
        if (user.isPresent()) {
            //TODO Mail Sender
            return "Email Sent";
        }
        return "User Not Found By This Email";
    }
    
    @Override
    public String resetPassword (final String email, final String password) {
        Optional <UserEntity> op = this.findByEmail(email);
        if (op.isPresent()) {
            entityManager
                    .createQuery("" +
                            " UPDATE UserEntity u SET" +
                            " u.password = :newPassword" +
                            " WHERE u.email = :email")
                    .setParameter("newPassword", password)
                    .setParameter("email", email)
                    .executeUpdate();
            return "Successfully Updated";
        }
        return "Not Found Such User By This Email";
    }
    
    public String update (final EditInfo editInfo) {
        Optional <UserEntity> op = this.findByEmail(editInfo.getEmail());
        if (op.isPresent()) {
                entityManager
                        .createQuery("" +
                                " UPDATE UserEntity u SET" +
                                " u.firstName = :newFirstName," +
                                " u.lastName = :newLastName," +
                                " u.birthDate = : newBirthDate" +
                                " WHERE u.email = :email")
                        .setParameter("newFirstName", editInfo.getFirstName())
                        .setParameter("newLastName", editInfo.getLastName())
                        .setParameter("newBirthDate", editInfo.getBirthDate())
                        .setParameter("email", op.get().getEmail())
                        .executeUpdate();
            return "Successfully Updated";
        }
        return "Not Successful";
    }
    
    @Override
    public String updateEmail (String email, String newEmail) {
        Optional <UserEntity> op = this.findByEmail(email);
        if (op.isPresent()) {
            entityManager
                    .createQuery("" +
                            " UPDATE UserEntity u SET" +
                            " u.email = :newEmail" +
                            " WHERE u.email = :email")
                    .setParameter("newEmail", newEmail)
                    .setParameter("email", email)
                    .executeUpdate();
            return "Successfully Updated";
        }
        return "Not Found Such User By This Email";
    }
}