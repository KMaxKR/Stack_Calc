package strc.data.calculator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "response_db")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "response")
    private String response;

    @OneToOne(mappedBy = "exResponse")
    private int expression_id;
}
