package org.upgrad.upstac.auth.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upgrad.upstac.exception.AppException;
import org.upgrad.upstac.users.User;
import org.upgrad.upstac.users.UserService;
import org.upgrad.upstac.users.models.AccountStatus;
import org.upgrad.upstac.users.roles.UserRole;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class RegisterService {

    @Autowired
    private UserService userService;


    private static final Logger log = LoggerFactory.getLogger(RegisterService.class);


    public User addUser(RegisterRequest registerRequest) {
/*      User should be validated before registration.
                the username , email and phone number should be unique (i.e should throw AppException if the RegisterRequest has the same username or email or phone number)
                    hint:
                        userService.findByUserName
                        userService.findByEmail
                        userService.findByPhoneNumber

         A new User Object should be created with same details as registerRequest
                password should be encrypted with help of   userService.toEncrypted
                roles should be set with help of  userService.getRoleFor(UserRole.USER)
                status should be set to AccountStatus.APPROVED

        And finally
            Call userService.saveInDatabase to save the new user and return the saved user
*/
        if(userService.findByUserName(registerRequest.getUserName()) != null) {
            throw new AppException("User Id already exists: "+registerRequest.getUserName());
        }
        if(userService.findByEmail(registerRequest.getEmail()) != null) {
            throw new AppException("Email Id already exists: "+registerRequest.getEmail());
        }
        if(userService.findByPhoneNumber(registerRequest.getPhoneNumber()) != null) {
            throw new AppException("Phone number already exists: "+registerRequest.getPhoneNumber());
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        User user = new User();

        user.setUserName(registerRequest.getUserName());
        user.setPassword(userService.toEncrypted(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setLastName(registerRequest.getLastName());
        user.setAddress(registerRequest.getAddress());
        user.setPinCode(registerRequest.getPinCode());
        user.setDateOfBirth(LocalDate.parse(registerRequest.getDateOfBirth(),dtf));
        user.setGender(registerRequest.getGender());
        user.setRoles(userService.getRoleFor(UserRole.USER));
        user.setStatus(AccountStatus.APPROVED);

        User updatedUser = userService.saveInDatabase(user);

        return user;
    }

    public User addDoctor(RegisterRequest registerRequest) {


/*      Doctor should be validated before registration.
                the username , email and phone number should be unique (i.e should throw AppException if the RegisterRequest has the same username or email or phone number)
                    hint:
                        userService.findByUserName
                        userService.findByEmail
                        userService.findByPhoneNumber

         A new User Object should be created with same details as registerRequest
                password should be encrypted with help of   userService.toEncrypted
                roles should be set with help of  userService.getRoleFor(UserRole.DOCTOR)
                status should be set to AccountStatus.INITIATED

        And finally
            Call userService.saveInDatabase to save the newly registered doctor and return the saved value
*/
        User user = null;
        if(userService.findByUserName(registerRequest.getUserName()) == null &&
                userService.findByEmail(registerRequest.getEmail()) == null &&
                userService.findByPhoneNumber(registerRequest.getPhoneNumber()) == null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            user = new User();
            user.setUserName(registerRequest.getUserName());
            user.setPassword(userService.toEncrypted(registerRequest.getPassword()));
            user.setFirstName(registerRequest.getFirstName());
            user.setEmail(registerRequest.getEmail());
            user.setPhoneNumber(registerRequest.getPhoneNumber());
            user.setLastName(registerRequest.getLastName());
            user.setAddress(registerRequest.getAddress());
            user.setPinCode(registerRequest.getPinCode());
            user.setDateOfBirth(LocalDate.parse(registerRequest.getDateOfBirth(),dtf));
            user.setGender(registerRequest.getGender());
            user.setRoles(userService.getRoleFor(UserRole.DOCTOR));
            user.setStatus(AccountStatus.INITIATED);
            user = userService.saveInDatabase(user);
            System.out.println("User Saved");
            return user;
        } else {
            throw new AppException("User ID or Email or Phone Number Already Exists");
        }

    }


    public User addTester(RegisterRequest registerRequest) {


/*      Tester should be validated before registration.
                the username , email and phone number should be unique (i.e should throw AppException if the RegisterRequest has the same username or email or phone number)
                    hint:
                        userService.findByUserName
                        userService.findByEmail
                        userService.findByPhoneNumber

         A new User Object should be created with same details as registerRequest
                password should be encrypted with help of   userService.toEncrypted
                roles should be set with help of  userService.getRoleFor(UserRole.TESTER)
                status should be set to AccountStatus.INITIATED

        And finally
            Call userService.saveInDatabase to save newly registered tester and return the saved value
*/
        User user = null;
        if(userService.findByUserName(registerRequest.getUserName()) == null &&
                userService.findByEmail(registerRequest.getEmail()) == null &&
                userService.findByPhoneNumber(registerRequest.getPhoneNumber()) == null) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            user = new User();
            user.setUserName(registerRequest.getUserName());
            user.setPassword(userService.toEncrypted(registerRequest.getPassword()));
            user.setFirstName(registerRequest.getFirstName());
            user.setEmail(registerRequest.getEmail());
            user.setPhoneNumber(registerRequest.getPhoneNumber());
            user.setLastName(registerRequest.getLastName());
            user.setAddress(registerRequest.getAddress());
            user.setPinCode(registerRequest.getPinCode());
            user.setDateOfBirth(LocalDate.parse(registerRequest.getDateOfBirth(),dtf));
            user.setGender(registerRequest.getGender());
            user.setRoles(userService.getRoleFor(UserRole.TESTER));
            user.setStatus(AccountStatus.INITIATED);
            user = userService.saveInDatabase(user);
            System.out.println("User Saved");
            return user;
        } else {
            throw new AppException("User ID or Email or Phone Number Already Exists");
        }

    }


}
