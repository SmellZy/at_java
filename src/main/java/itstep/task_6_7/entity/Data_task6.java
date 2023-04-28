package itstep.task_6_7.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="data")
@NoArgsConstructor
public class Data_task6 implements Serializable {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "data_social_media", joinColumns = @JoinColumn(name = "data_id"), inverseJoinColumns = @JoinColumn(name = "social_media_id"))
    private List<SocialMedia> socialMedia;


    @OneToMany(mappedBy = "data", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Device> devices;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String time;

    @Column
    private String person;

    @OneToOne(mappedBy = "data", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Phone phone;

    public Data_task6(String time, String person) {
        this.time = time;
        this.person = person;
    }
}
