package com.example.tourism_management_system.model.entities;

import com.example.tourism_management_system.model.enums.Role;
import com.example.tourism_management_system.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode ( callSuper = true )
@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
public class RoleEntity extends BaseEntity {
    
    @Column(name = "user_role", nullable = false)
    private Boolean userRole;
    @Column(name = "tour_administrator_role", nullable = false)
    private Boolean tourAdministratorRole;
    
    public RoleEntity () {
        this.setStatus(Status.ACTIVE);
    }
    
    @Override
    public String toString () {
        StringBuilder stringBuilder = new StringBuilder();
        int t = 0;
        if (this.tourAdministratorRole) {
            stringBuilder.append(Role.TOUR_ADMINISTRATOR);
            t++;
        }
        if (this.userRole){
            if (t > 0)
                stringBuilder.append(",");
            stringBuilder.append(Role.USER);
        }
        return stringBuilder.toString();
    }
}