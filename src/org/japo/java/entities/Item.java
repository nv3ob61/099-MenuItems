/*
 * Copyright (C) 2020 Jonsui
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.japo.java.entities;

import org.japo.java.libraries.UtilesValidacion;

/**
 *
 * @author Jonsui
 */
public class Item {
  
  public static final String DEF_NOMBRE = "Patatas";
  //pongo DEF_PRECIO a -1, valor indudable que no corresponde a 0 que sería
  //en el caso que vendiéramos algo como regalarlo.
  public static final double DEF_PRECIO = -1.00;
  public static final String ER_NOMBRE = ".*\\w.*";
  public static final String ER_PRECIO = "\\d[\\d.]*";
  
  private final int id;
  private String nombre;
  private double precio;
  private static int increment = 1;
  
  public Item() {
    id = increment;
    nombre = DEF_NOMBRE;
    precio = DEF_PRECIO;
    increment++;
  }

  //Eliminamos del constr. parametrizado el atributo ID para que no sea
  //posible crear nuevos Items añadiéndole a mano la ID. Pasa a ser final.
  //
  //Nos cargamos la posibilidad en los SETTER de setId(); para que no se pueda
  //modificar desde fuera ninguna ID de ningún artículo (aparentemente)
  public Item(String nombre, double precio) {
    this.id = increment;
    this.nombre = nombre;
    if (UtilesValidacion.validarPrecio(Double.toString(precio))) {
      this.precio = precio;
    } else {
      this.precio = DEF_PRECIO;
    }
    increment++;
  }
  
  public int getId() {
    return id;
  }
  
  public String getNombre() {
    return nombre;
  }
  
  public double getPrecio() {
    return precio;
  }
  
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public void setPrecio(double precio) {
    this.precio = precio;
  }
  
  @Override
  public String toString() {
    return String.format("INFO DEL ITEM%n"
            + "----------------%n"
            + "ID .........: %d%n"
            + "NOMBRE .....: %s%n"
            + "PRECIO .....: %.2f €%n"
            + "---", id, nombre, precio);
  }
  
  public void muestraInfoItem() {
    System.out.println(toString());
  }
  
}
