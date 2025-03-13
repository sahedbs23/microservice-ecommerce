package com.kids.collection.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ValidationErrorResponse {
    private String message;
    private Map<String, String> errorDetails;
}
