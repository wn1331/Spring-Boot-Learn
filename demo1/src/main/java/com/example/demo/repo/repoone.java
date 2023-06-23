package com.example.demo.repo;

import org.springframework.stereotype.Repository;

@Repository
public class repoone implements repo{

    @Override
    public void getHello() {
        System.out.println("Hello RepoOne!");
    }
}
