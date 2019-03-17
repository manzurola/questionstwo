//package com.prodigy.api.users;
//
//import com.prodigy.api.common.EntityFactory;
//import org.apache.commons.validator.routines.EmailValidator;
//
//public class UserFactoryImpl extends EntityFactory implements UserFactory {
//
//    @Override
//    public User create(String email) {
//        if (!EmailValidator.getInstance().isValid(email)) {
//            throw new RuntimeException("Invalid email " + email);
//        }
//        return new User(nextId(), email);
//    }
//}
