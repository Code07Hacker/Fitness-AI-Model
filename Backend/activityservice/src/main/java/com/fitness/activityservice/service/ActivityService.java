package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.mapper.ActivityMapper;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repo.ActivityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;

    private final ActivityMapper activityMapper;

    private final UserValidationService userValidationService;

    public ActivityResponse trackActivity(ActivityRequest activityRequest) {
        boolean isValidUser = userValidationService.validateUser(activityRequest.getUserId());
        if(!isValidUser){
            throw new RuntimeException("Invalid User : " + activityRequest.getUserId());
        }
        Activity activity = activityRepository.save(activityMapper.mapToActivity(activityRequest));
        return activityMapper.mapToActivityResponse(activity);
    }
}
