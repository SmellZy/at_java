package itstep.course_tasks.task_6_7.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="phone")
@NoArgsConstructor
public class Phone implements Serializable {

    public Phone(String number) {
        this.number = number;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String number;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Data_task6_id")
    private Data_task6 data_task6;
}
