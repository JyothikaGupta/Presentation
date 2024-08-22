package com.practice.spring_splunk.dto;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum JobStatus {

    SUCCESS,FAILED;

    private String name;


}