package org.dominisoft.scrumdev.claro2020.domain;

import org.dominisoft.scrumdev.claro2020.domain.exceptions.DocumentInvalidException;

/**
 * "Cedula" validator.
 */
public final class DopNationalIdentificationNumber {

    private final String value;

    /**
     * Validates the given value ensuring only valid data can be used to create instances of this class.
     *
     * @param document National Identification Number
     */

    public DopNationalIdentificationNumber(final String document) {
        validateIfDocEmpty(document);
        validateIfInvalid("01234567891".equals(document), "El documento es inválido");
        validateIfInvalid(!document.matches("[0-9]+"), "El documento no debe contener solo numeros");
        validateIfInvalid(document.length() != 11, "El documento debe tener solo 11 caracteres");
        value = document;
    }

    private void validateIfInvalid(boolean val, String s) {
        if (val) {
            throw new DocumentInvalidException(s);
        }
    }

    private void validateIfDocEmpty(String document) {
        if (document == null || document.isBlank()) {
            throw new IllegalArgumentException("El Documento es requerido");
        }
    }

    /**
     * Returns wrapped value.
     *
     * @return wrapped value.
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
