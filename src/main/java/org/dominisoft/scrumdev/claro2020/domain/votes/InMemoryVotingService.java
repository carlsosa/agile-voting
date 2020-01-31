package org.dominisoft.scrumdev.claro2020.domain.votes;

import java.util.List;

import org.dominisoft.scrumdev.claro2020.domain.DopNationalIdentificationNumber;

/**
 * dsafdsaf.
 * 
 * @author jose an rodriguez
 */
public class InMemoryVotingService implements VotingService {
  List<String> documentThatVoted = List.of("07200140809");

  @Override
  public boolean hasVoted(DopNationalIdentificationNumber number) {
    return documentThatVoted.contains(number.getValue());
  }
}
