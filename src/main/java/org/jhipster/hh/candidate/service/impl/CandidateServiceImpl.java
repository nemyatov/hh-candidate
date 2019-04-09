package org.jhipster.hh.candidate.service.impl;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jhipster.hh.candidate.service.CandidateService;
import org.jhipster.hh.candidate.domain.Candidate;
import org.jhipster.hh.candidate.repository.CandidateRepository;
import org.jhipster.hh.candidate.service.dto.CandidateDTO;
import org.jhipster.hh.candidate.service.mapper.CandidateMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing Candidate.
 */
@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {

    private final Logger log = LoggerFactory.getLogger(CandidateServiceImpl.class);

    private final CandidateRepository candidateRepository;

    private final CandidateMapper candidateMapper;

    public CandidateServiceImpl(CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
        this.candidateRepository = candidateRepository;
        this.candidateMapper = candidateMapper;
    }

    /**
     * Save a candidate.
     *
     * @param candidateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CandidateDTO save(CandidateDTO candidateDTO) {
        log.debug("Request to save Candidate : {}", candidateDTO);
        Candidate candidate = candidateMapper.toEntity(candidateDTO);
        candidate = candidateRepository.save(candidate);
        return candidateMapper.toDto(candidate);
    }

    /**
     * Get all the candidates.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CandidateDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Candidates");
        return candidateRepository.findAll(pageable)
            .map(candidateMapper::toDto);
    }


    /**
     * Get one candidate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CandidateDTO> findOne(Long id) {
        log.debug("Request to get Candidate : {}", id);
        return candidateRepository.findById(id)
            .map(candidateMapper::toDto);
    }

    /**
     * Delete the candidate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Candidate : {}", id);
        candidateRepository.deleteById(id);
    }


    @Override
    public CandidateDTO parseResume(String url) throws IOException {
        CandidateDTO candidate = new CandidateDTO();
        candidate.setId(null);
        candidate.setUrl(url);
        candidate.setEmail("Информация не найдена");
        candidate.setName("Информация не найдена");

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        HttpResponse response = client.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null)
            result.append(line);

        Document doc = Jsoup.parse(result.toString());
        Elements links = doc.select("a[itemprop=email]");
        Elements names = doc.select("h1[data-qa=resume-personal-name]");
        Elements hidden = doc.select("div:contains(Электронная почта скрыта соискателем)");

        if (hidden.size() == 0) {
            List<String> hrefs = links.eachAttr("href");
            hrefs.stream().findFirst().ifPresent(mailto -> candidate.setEmail(mailto.substring(7)));
            names.stream().findFirst().ifPresent(name -> candidate.setName(name.text()));
        } else {
            System.out.println("Электронная почта скрыта соискателем");
        }

        return candidate;
    }


}
