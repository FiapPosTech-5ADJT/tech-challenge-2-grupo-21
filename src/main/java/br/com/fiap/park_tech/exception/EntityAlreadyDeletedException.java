package br.com.fiap.park_tech.exception;

public class EntityAlreadyDeletedException extends RuntimeException {
    public EntityAlreadyDeletedException(String param) {
        super(String.format("Entity with identifier %s has already been deleted.", param));
    }
}
