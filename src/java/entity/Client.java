/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
public class Client implements Serializable {    
// implements Serializable означит, что объект (массив) можно разбить на байты и уместить в небольшом контейнере.
// обратный процесс назыв. маршализация - из байтов собирается объект 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;
        private String firstname;                
        private String lastname;
        private String phone;
        private Double cash;
        
         public Client() {
        }
        public Client(String firstname, String lastname, String phone, Double cash) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.cash = cash;
    }
        
      public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getCash() {
        return cash;
    } 
    public void setCash(Double cash) {
        this.cash = cash;
    }
    @Override
    public String toString() {
    return "Client{" 
                    + "firstname = " + firstname 
                    + ", lastname = " + lastname 
                    + ", phone = " + phone 
                    + ", cash = " + cash
                    + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.firstname);
        hash = 73 * hash + Objects.hashCode(this.lastname);
        hash = 73 * hash + Objects.hashCode(this.phone);
        hash = 73 * hash + Objects.hashCode(this.cash);
        return hash;
    }

       
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.cash, other.cash)) {
            return false;
        }
        return true;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
