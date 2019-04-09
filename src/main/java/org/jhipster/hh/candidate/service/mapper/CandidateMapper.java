package org.jhipster.hh.candidate.service.mapper;

import org.jhipster.hh.candidate.domain.*;
import org.jhipster.hh.candidate.service.dto.CandidateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Candidate and its DTO CandidateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CandidateMapper extends EntityMapper<CandidateDTO, Candidate> {



    default Candidate fromId(Long id) {
        if (id == null) {
            return null;
        }
        Candidate candidate = new Candidate();
        candidate.setId(id);
        return candidate;
    }
}
