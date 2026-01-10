package com.fitness.activityservice.mapper;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    Activity mapToActivity(ActivityRequest activityRequest);

    ActivityResponse mapToActivityResponse(Activity activity);
}
