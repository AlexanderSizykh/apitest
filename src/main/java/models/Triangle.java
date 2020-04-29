package models;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.UUID;


@JsonAutoDetect
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Triangle {
    public String separator;
    public String input;
    public UUID id;
    public int firstSide;
    public int secondSide;
    public int thirdSide;


}
