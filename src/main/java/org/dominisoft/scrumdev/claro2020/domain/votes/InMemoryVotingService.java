package org.dominisoft.scrumdev.claro2020.domain.votes;

import org.dominisoft.scrumdev.claro2020.domain.DopNationalIdentificationNumber;

/**
 * @author jose an rodriguez
 */
public class InMemoryVotingService implements VotingService {
    @Override
    public boolean hasVoted(DopNationalIdentificationNumber number) {
        return false;
    }
}
