package com.example.demo.Service;

import com.example.demo.Entity.Follow;
import com.example.demo.Repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    public Follow loadFollow(long id){

        Follow follow=followRepository.findById(id)
                .orElseThrow(() -> new ObjectNotExistsException(id));

        return follow;

    }
}
