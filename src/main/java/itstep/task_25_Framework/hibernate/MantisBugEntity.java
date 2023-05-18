package itstep.task_25_Framework.hibernate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "mantis_bug_table")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MantisBugEntity {

        @Id
        @Column(name = "id")
        private Long id;

        @Column(name = "project_id")
        private Long projectId;

        @Column(name = "reporter_id")
        private Long reporterId;

        @Column(name = "handler_id")
        private Long handlerId;

        @Column(name = "duplicate_id")
        private Long duplicateId;

        @Column(name = "priority")
        private Integer priority;

        @Column(name = "severity")
        private Integer severity;

        @Column(name = "reproducibility")
        private Integer reproducibility;

        @Column(name = "status")
        private Integer status;

        @Column(name = "resolution")
        private Integer resolution;

        @Column(name = "projection")
        private Integer projection;

        @Column(name = "eta")
        private Integer eta;

        @Column(name = "bug_text_id")
        private Integer bugTextId;

        @Column(name = "os")
        private String os;

        @Column(name = "os_build")
        private String osBuild;

        @Column(name = "platform")
        private String platform;

        @Column(name = "version")
        private String version;

        @Column(name = "fixed_in_version")
        private String fixedInVersion;

        @Column(name = "build")
        private String build;

        @Column(name = "profile_id")
        private Integer profileId;

        @Column(name = "view_state")
        private Integer viewState;

        @Column(name = "summary")
        private String summary;

        @Column(name = "sponsorship_total")
        private Integer sponsorshipTotal;

        @Column(name = "sticky")
        private Integer sticky;

        @Column(name = "target_version")
        private String targetVersion;

        @Column(name = "category_id")
        private Integer categoryId;

        @Column(name = "date_submitted")
        private Long dateSubmitted;

        @Column(name = "due_date")
        private Long dueDate;

        @Column(name = "last_updated")
        private Long lastUpdated;
}