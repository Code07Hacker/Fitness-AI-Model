package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.mapper.ActivityMapper;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repo.ActivityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    private final ActivityMapper activityMapper;

    private final UserValidationService userValidationService;

    private final KafkaTemplate<String,Activity> kafkaTemplate;

    @Value("${app.kafka.topic.name}")
    private String topicName;

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {
        boolean isValidUser = userValidationService.validateUser(activityRequest.getUserId());
        if(!isValidUser){
            throw new RuntimeException("Invalid User : " + activityRequest.getUserId());
        }
        Activity activity = activityRepository.save(activityMapper.mapToActivity(activityRequest));

        try {
            kafkaTemplate.send(topicName , activity.getUserId() , activity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return activityMapper.mapToActivityResponse(activity);
    }
}
