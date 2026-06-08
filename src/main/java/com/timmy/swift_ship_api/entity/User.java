package com.timmy.swift_ship_api.entity;
import com.timmy.swift_ship_api.entity.enums.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;
    @Column()
    private Long walletBalance;
    @Column
    private Boolean isActive;
    @Column
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_date")
    LocalDateTime updatedDate;
}
