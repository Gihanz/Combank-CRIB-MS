package com.nable.crib.comn.util;

public class ErrorDescription {

    private ErrorDescription() {
        throw new IllegalStateException("Error Description class");
    }

    public static final String INPUT_PARAMETERS_ARE_NOT_VALID = "Input Parameters Are Not Valid";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
   
}
