package com.example.backendmd6.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "enterpriseTable")
public class ProfileEnterprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nameCompany;
    private String description;
    @Column(nullable = false)
    private String image;
    private String addressCompany;
    private int numberOfEmployees;
    private String phoneNumbers;
    private String linkWebsites;
    private String linkFacebook;
    private String linkGoogleMaps;
    @ManyToOne
    @JoinColumn(name = "statusEnterprise_id")
    private StatusEnterprise statusEnterpriseId;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String confirmPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "enterprise_role",
            joinColumns = {@JoinColumn(name = "enterprise_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;
    private boolean enabled = true;

    public ProfileEnterprise(String nameCompany, String description, String image, String addressCompany, int numberOfEmployees, String phoneNumbers, String linkWebsites, String linkFacebook, String linkGoogleMaps, StatusEnterprise statusEnterpriseId, String email, String password, String confirmPassword, Set<Role> roles) {
        this.nameCompany = nameCompany;
        this.description = description;
        this.image = image;
        this.addressCompany = addressCompany;
        this.numberOfEmployees = numberOfEmployees;
        this.phoneNumbers = phoneNumbers;
        this.linkWebsites = linkWebsites;
        this.linkFacebook = linkFacebook;
        this.linkGoogleMaps = linkGoogleMaps;
        this.statusEnterpriseId = statusEnterpriseId;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.roles = roles;
    }
}
