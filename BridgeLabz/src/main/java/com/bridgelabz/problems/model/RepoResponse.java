package com.bridgelabz.problems.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
@Builder
public class RepoResponse {

    private String message;
    private int status;
    private Object responseBody;
}
