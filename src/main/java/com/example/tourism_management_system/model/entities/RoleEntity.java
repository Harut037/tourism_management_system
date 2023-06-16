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
    @Column(nullable = false)
    private Boolean userRole;
    @Column(nullable = false)
    private Boolean adminRole;
//    @Column(nullable = false)
//    private Boolean tourAdministratorRole;
//    @Column(nullable = false)
//    private Boolean supportRole;
    @Column(nullable = false)
    private Boolean flag = true;
    
    @Override
    public String toString () {
        StringBuilder stringBuilder = new StringBuilder();
        int t = 0;
        if (this.adminRole) {
            stringBuilder.append(Role.ADMIN);
            t++;
        }
//        if (this.tourAdministratorRole){
//            if (t > 0)
//                stringBuilder.append(",");
//            stringBuilder.append(Role.TOUR_ADMINISTRATOR);
//            t++;
//        }
//        if (this.supportRole){
//            if (t > 0)
//                stringBuilder.append(",");
//            stringBuilder.append(Role.SUPPORT);
//            t++;
//        }
        if (this.userRole){
            if (t > 0)
                stringBuilder.append(",");
            stringBuilder.append(Role.USER);
        }
        return stringBuilder.toString();
    }
}