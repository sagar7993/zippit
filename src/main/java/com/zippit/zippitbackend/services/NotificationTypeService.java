package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.entities.NotificationType;
import com.zippit.zippitbackend.repositories.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akash.mercer on 09-Aug-17.
 */

@Service
public class NotificationTypeService {

    @Autowired
    private NotificationTypeRepository notificationTypeRepository;

    public Integer getCount() {
        return notificationTypeRepository.getCount();
    }

    public NotificationType findByType(Integer type) {
        return notificationTypeRepository.findByType(type);
    }
}
