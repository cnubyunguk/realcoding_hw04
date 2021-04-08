package org.cnu.realcoding.homework0420.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Already have the given fields name, ownerName, ownerPhoneNumber.")
public class DogDuplicateException extends RuntimeException{
}