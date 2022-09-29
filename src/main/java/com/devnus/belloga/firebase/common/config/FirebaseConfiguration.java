package com.devnus.belloga.firebase.common.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Configuration
public class FirebaseConfiguration {
    private FirebaseApp firebaseApp;

    @PostConstruct
    public FirebaseApp initializeFCM() throws IOException {
        Resource resource = new ClassPathResource("secrets/belloga-firebase-adminsdk.json");

        List<FirebaseApp> apps = FirebaseApp.getApps();
        //FirebaseApp name [DEFAULT] already exists! 해결
        if(apps != null && !apps.isEmpty()) {
            for(FirebaseApp app : apps){
                if(app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
                    firebaseApp = app;
                }
            }
        } else {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(resource.getInputStream()))
                    .build();
            firebaseApp = FirebaseApp.initializeApp(options);
        }
        return firebaseApp;
    }
}
