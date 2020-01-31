package org.dominisoft.scrumdev.claro2020.domain.votes;

import org.dominisoft.scrumdev.claro2020.domain.DopNationalIdentificationNumber;

import java.util.List;

/**
 * @author jose an rodriguez
 */
public class InMemoryVotingService implements VotingService {
    List<String> DOCUMENTS_THAT_VOTED = List.of("07200140809");

    @Override
    public boolean hasVoted(DopNationalIdentificationNumber number) {
        return DOCUMENTS_THAT_VOTED.contains(number.getValue());
    }
}
