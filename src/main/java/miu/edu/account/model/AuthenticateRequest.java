package miu.edu.account.model;

import java.io.Serial;
import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 4677768583005150707L;
    private String username;
    private String password;
}