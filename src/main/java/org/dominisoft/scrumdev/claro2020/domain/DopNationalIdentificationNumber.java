package org.dominisoft.scrumdev.claro2020.domain;

import java.util.List;

/**
 * "Cedula" validator.
 */
public final class DopNationalIdentificationNumber {

    private final List<String> BANNED = List.of("12345678901");
    private final String value;

    /**
     * Validates the given value ensuring only valid data can be used to create instances of this class.
     *
     * @param document National Identification Number
     */

    public DopNationalIdentificationNumber(final String document) {
        if (document == null || document.isBlank()) {
            throw new IllegalArgumentException("El Documento es requerido");
        }
//        BANNED.
        value = document;
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
