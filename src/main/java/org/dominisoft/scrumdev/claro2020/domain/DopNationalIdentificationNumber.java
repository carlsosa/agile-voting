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
        validateIfInvalid(!validateDocument(document), "Debe ser un documento válido");
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

    public static boolean validateDocument(String document) {
        int suma = 0;
        final char[] peso = {'1', '2', '1', '2', '1', '2', '1', '2', '1', '2'};
        if ((document == null) || (document.length() != 11)) {
            return false;
        }
        try {
            Long.parseLong(document);
        } catch (Exception e) {
            return false;
        }
        for (int i = 0; i < 10; i++) {
            int a = Character.getNumericValue(document.charAt(i));
            int b = Character.getNumericValue(peso[i]);
            char[] multiplier = Integer.toString(a * b).toCharArray();
            if (multiplier.length > 1) {
                a = Character.getNumericValue(multiplier[0]);
                b = Character.getNumericValue(multiplier[1]);
            } else {
                a = 0;
                b = Character.getNumericValue(multiplier[0]);
            }
            suma = suma + a + b;
        }
        int digit = (10 - (suma % 10)) % 10;
        return digit == Character.getNumericValue(document.charAt(10));
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
