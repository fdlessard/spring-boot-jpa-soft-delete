package io.fdlessard.codebites.softdelete;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.Nullable;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Table(name = "customer")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE customer SET deleted_at=now() WHERE id=? AND version = ?")
@Where(clause = "deleted_at IS NULL")
public class Customer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "version", columnDefinition = "int default 0")
  @JsonIgnore
  @Version
  private int version;

  private String lastName;

  private String firstName;

  private String company;

  @JsonIgnore
  @Nullable
  @Column(name = "deleted_at")
  private Instant deletedAt = null;

}
