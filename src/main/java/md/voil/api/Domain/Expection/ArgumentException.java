package md.voil.api.Domain.Expection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArgumentException {
    private String field;
    private String message;
}
