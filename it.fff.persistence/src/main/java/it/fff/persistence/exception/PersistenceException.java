package it.fff.persistence.exception;

import it.fff.business.common.exception.ApplicationException;

/**
 * Created by lpelosi on 30/10/16.
 */
public class PersistenceException extends ApplicationException {

    public PersistenceException(String message){super(message);}

    public PersistenceException(Exception innerException){super(innerException);}

    public PersistenceException(String message, Exception innerException){
        super(message,innerException);
    }

    public static void manageException(ApplicationException e, String errorCode) throws PersistenceException{
        PersistenceException ex = new PersistenceException(e.getMessage(), e);
        ex.addErrorCodes(e.getErrorCodes());
        ex.addErrorCode(errorCode);
        throw ex;
    }
}
