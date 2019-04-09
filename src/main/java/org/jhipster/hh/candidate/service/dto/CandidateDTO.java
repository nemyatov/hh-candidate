package org.jhipster.hh.candidate.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Candidate entity.
 */
public class CandidateDTO implements Serializable {

    private Long id;

    @NotNull
    private String url;

    @NotNull
    private String email;

    @NotNull
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CandidateDTO candidateDTO = (CandidateDTO) o;
        if (candidateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CandidateDTO{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", email='" + getEmail() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
