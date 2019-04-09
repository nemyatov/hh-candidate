

package org.jhipster.hh.candidate.web.rest;

import org.jhipster.hh.candidate.service.CandidateService;
import org.jhipster.hh.candidate.service.dto.CandidateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * REST controller for managing Candidate.
 */
@RestController
@RequestMapping("/api/hh")
public class HHResource {

    private final Logger log = LoggerFactory.getLogger(HHResource.class);

    private final CandidateService candidateService;

    public HHResource(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    /**
     * GET  /candidates/:url : get the "url" candidate.
     *
     * @param url the url of the candidate in hh.ru to parse
     * @return the ResponseEntity with status 200 (OK) and with body the candidateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/candidates/{url}")
    public ResponseEntity<CandidateDTO> parseResume(@PathVariable String url, HttpServletRequest request) throws IOException {
        url = "https://hh.ru/resume/" + url;
        log.debug("REST request to parse Resume : {}", url);
        return ResponseEntity.ok().body(candidateService.parseResume(url));
    }

}



