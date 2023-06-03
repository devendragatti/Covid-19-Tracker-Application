package in.stackroute.covidapplication.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Covid {
    @Id
    private String country;
    @JsonProperty("infected")
    private String infected;

    @JsonProperty("recovered")
    private String recovered;

    @JsonProperty("deceased")
    private String deceased;
}
