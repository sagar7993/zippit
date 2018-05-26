package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.entities.UserAddress;
import com.zippit.zippitbackend.repositories.UserAddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akash.mercer on 05-Jul-17.
 */

@Service
public class UserAddressService {
    private static final String TAG = "UserAddressService : ";

    @Autowired
    private UserAddressRepository userAddressRepository;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public UserAddress save(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }
}
