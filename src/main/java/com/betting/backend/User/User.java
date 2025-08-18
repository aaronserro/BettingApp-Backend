// src/main/java/com/betting/backend/User/User.java
package com.betting.backend.User;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity(name = "User")
@Table(
  name = "USERS",
  indexes = {
    @Index(name = "idx_users_guest_id", columnList = "GUEST_ID", unique = true)
  }
)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  // Stable anonymous identity for no-auth mode
  @Column(name = "GUEST_ID", length = 64, nullable = false, unique = true)
  private String guestId;

  @Column(name = "USERNAME", length = 255, nullable = true, unique = false)
  private String username;

  @Column(name = "BIO", length = 255, nullable = true, unique = false)
  private String bio;

  @Column(name = "HEARD_FROM", length = 255)
  private String heardFrom;

  @Column(name = "NICHE", length = 255)
  private String niche;

  @Column(name = "UNIVERSITY", length = 255)
  private String university;

  @Column(name = "CREATED_AT", updatable = false)
  private Instant createdAt;

  @Column(name = "UPDATED_AT")
  private Instant updatedAt;

  public User() {
    // defaults are set in @PrePersist
  }

  public User(String guestId, String username, String bio,
              String heardFrom, String niche, String university) {
    this.guestId = guestId;
    this.username = username;
    this.bio = bio;
    this.heardFrom = heardFrom;
    this.niche = niche;
    this.university = university;
  }

  @PrePersist
  protected void onCreate() {
    if (this.guestId == null || this.guestId.isBlank()) {
      this.guestId = UUID.randomUUID().toString();
    }
    this.createdAt = Instant.now();
    this.updatedAt = this.createdAt;
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = Instant.now();
  }

  // Getters
  public Long getId() { return id; }
  public String getGuestId() { return guestId; }
  public String getUsername() { return username; }
  public String getBio() { return bio; }
  public String getHeardFrom() { return heardFrom; }
  public String getNiche() { return niche; }
  public String getUniversity() { return university; }
  public Instant getCreatedAt() { return createdAt; }
  public Instant getUpdatedAt() { return updatedAt; }

  // Setters
  public void setId(Long id) { this.id = id; }
  public void setGuestId(String guestId) { this.guestId = guestId; }
  public void setUsername(String username) { this.username = username; }
  public void setBio(String bio) { this.bio = bio; }
  public void setHeardFrom(String heardFrom) { this.heardFrom = heardFrom; }
  public void setNiche(String niche) { this.niche = niche; }
  public void setUniversity(String university) { this.university = university; }
}
