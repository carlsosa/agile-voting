package org.dominisoft.scrumdev.claro2020.domain;

import org.dominisoft.scrumdev.claro2020.domain.exceptions.DocumentInvalidException;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public final class DopNationalIdentificationNumberTests {

    public static class ConstructorMessageTests {

      /*
       * Cedula con letra -> Cedula es inválida
       * [12345678901] -> Cedula Invalida
       */

      /*
       * Cédula banned -> Throw DocumentInvalidException
       * Cédula que ya ha votado -> DocumentAlreadyVotedException
       * Cédula Valida -> Informacion del votante
       */

        @Test(expected = IllegalArgumentException.class)
        public void rejects_null_document() {
            new DopNationalIdentificationNumber(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void rejects_empty_document() {
            new DopNationalIdentificationNumber("  ");
        }

        @Test(expected = DocumentInvalidException.class)
        public void rejects_document_in_black_list() {
            new DopNationalIdentificationNumber("01234567891");
        }
    }
}
