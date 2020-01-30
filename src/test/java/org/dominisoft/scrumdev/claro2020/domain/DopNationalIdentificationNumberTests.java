package org.dominisoft.scrumdev.claro2020.domain;

import org.dominisoft.scrumdev.claro2020.domain.exceptions.DocumentAlreadyVotedException;
import org.dominisoft.scrumdev.claro2020.domain.exceptions.DocumentInvalidException;
import org.dominisoft.scrumdev.claro2020.domain.votes.InMemoryVotingService;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertFalse;

@RunWith(Enclosed.class)
public final class DopNationalIdentificationNumberTests {

    public static class ConstructorMessageTests {

        @Test(expected = IllegalArgumentException.class)
        public void rejects_null_document() {
            new DopNationalIdentificationNumber(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void rejects_empty_document() {
            new DopNationalIdentificationNumber("  ");
        }

        @Test(expected = DocumentInvalidException.class)
        public void rejects_document_with_invalid_characters() {
            new DopNationalIdentificationNumber("0123456789a");
        }

        @Test(expected = DocumentInvalidException.class)
        public void rejects_document_with_invalid_length() {
            new DopNationalIdentificationNumber("0123456789aaaa");
        }

        @Test(expected = DocumentInvalidException.class)
        public void rejects_document_in_black_list() {
            new DopNationalIdentificationNumber("01234567891");
        }

        @Test(expected = DocumentAlreadyVotedException.class)
        public void rejects_document_that_voted() {
            DopNationalIdentificationNumber doc = new DopNationalIdentificationNumber("00101044821");
            if (new InMemoryVotingService().hasVoted(doc)) {
                throw new DocumentAlreadyVotedException();
            }
        }

        @Test
        public void accept_document_that_has_not_voted() {
            DopNationalIdentificationNumber doc = new DopNationalIdentificationNumber("07200140809");
            assertFalse(new InMemoryVotingService().hasVoted(doc));
        }
    }
}
