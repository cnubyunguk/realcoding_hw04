package org.cnu.realcoding.homework0420.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LOCKED, reason = "Attempt to update medical record")
public class AttemptToChangeMedicalRecord extends RuntimeException{
}