package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "is_user")
    private Boolean isUser;
    @Column(name = "is_admin")
    private Boolean isAdmin;
    @Column(name = "is_tour_administrator")
    private Boolean isTourAdministrator;
    @Column(name = "is_support")
    private Boolean isSupport;
    @Column(name = "flag")
    private Boolean flag = true;
    
    @Override
    public String toString () {
        StringBuilder stringBuilder = new StringBuilder();
        int t = 0;
        if (this.isAdmin) {
            stringBuilder.append(Role.ADMIN);
            t++;
        }
        if (this.isTourAdministrator){
            if (t > 0)
                stringBuilder.append(",");
            stringBuilder.append(Role.TOUR_ADMINISTRATOR);
        }
        if (this.isSupport){
            if (t > 0)
                stringBuilder.append(",");
            stringBuilder.append(Role.SUPPORT);
        }
        if (this.isUser){
            if (t > 0)
                stringBuilder.append(",");
            stringBuilder.append(Role.USER);
        }
        return stringBuilder.toString();
    }
}