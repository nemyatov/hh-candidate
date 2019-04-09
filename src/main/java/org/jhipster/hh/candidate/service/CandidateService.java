package org.jhipster.hh.candidate.service;

import org.jhipster.hh.candidate.service.dto.CandidateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * Service Interface for managing Candidate.
 */
public interface CandidateService {

    /**
     * Save a candidate.
     *
     * @param candidateDTO the entity to save
     * @return the persisted entity
     */
    CandidateDTO save(CandidateDTO candidateDTO);

    /**
     * Get all the candidates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CandidateDTO> findAll(Pageable pageable);


    /**
     * Get the "id" candidate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CandidateDTO> findOne(Long id);

    /**
     * Delete the "id" candidate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Parse Resume on hh.ru
     * @param url - URL
     * @return CandidateDTO
     */
    CandidateDTO parseResume(String url) throws IOException;




}
