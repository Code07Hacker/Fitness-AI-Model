package com.fitness.aiservice.Listener;

import com.fitness.aiservice.model.Activity;
import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.repo.RecommendationRepository;
import com.fitness.aiservice.service.ActivityAIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityMessageListener {

    private final ActivityAIService activityAIService;

    private final RecommendationRepository recommendationRepository;

    @KafkaListener(topics = "${app.kafka.topic.name}",groupId = "my-consumer-group")
    public void processActivity(Activity activity){
        log.info("Received Activity for processing : {}",activity.getUserId());
        Recommendation recommendation = activityAIService.generateRecommendation(activity);
        recommendationRepository.save(recommendation);
    }

}
