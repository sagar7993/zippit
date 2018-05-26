package com.zippit.zippitbackend.services;

import com.zippit.zippitbackend.beans.AppConfigBean;
import com.zippit.zippitbackend.entities.AppConfig;
import com.zippit.zippitbackend.repositories.AppConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by akash.mercer on 23-Jul-17.
 */

@Service
public class AppConfigService {
    private static final String TAG = "AppConfigService : ";

    @Autowired
    private AppConfigRepository appConfigRepository;

    private static Logger debugLogger = LoggerFactory.getLogger("debugLogs");

    private static Logger errorLogger = LoggerFactory.getLogger("errorLogs");

    public Integer getCount() {
        return appConfigRepository.getCount();
    }

    public AppConfigBean getAppConfig() {
        AppConfig retrievedAppConfig = appConfigRepository.findOne("1");

        return new AppConfigBean(retrievedAppConfig);
    }

}
