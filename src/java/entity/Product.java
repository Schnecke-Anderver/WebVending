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
public class Product implements Serializable { // implements Serializable - список продуктов будет сохраняться в отдельном файле 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private Double price;
    private Integer quantity;
    
     public Product(){    //конструктор
    }
    
    public Product(String name, Double price, Integer quantity) { // сделали конструктор через Insert code - Konstruktor
        this.name = name;
        this.price = price;
        this.setQuantity(quantity); //это часть проверки, сразу сверяемся с количеством товара
    }   
    
       public String getName() { // создали конструкцию getter-setter через Insert code
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }
        
    public void setQuantity(Integer quantity) {
        if(quantity <= 0){ // proverka на правильность введенного количества товара. 
            return;       // Пригодиться, когда буду писать операцию добавления не товара целиком, а только его количества.
        }
        this.quantity = quantity;
    }  

    
    @Override  //метод переопределения родительского метода в тот, что нам нужен.
    public String toString() {
        return "Product{" 
                + "name = " + name
                +  "  price = " + price 
                +  "  quantity = " + quantity 
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.price);
        hash = 41 * hash + Objects.hashCode(this.quantity);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.quantity)) {
            return false;
        }
        return true;
    }
    /*
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/
}   

