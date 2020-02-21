/* 
 * Copyright (C) 2020 mon_mode   0mon.mode@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.japo.java.libraries;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author CicloM
 */
public class UtilesValidacion {

  //Se valida como un nombre, ahora sí que va.
  public static final String ER_NOMBRE = "^[a-zA-Z0-9 ]*$";
  //Precio real con dos decimales.
  public static final String ER_PRECIO = "[0-9]+([\\.,][0-9]{1,2})?";
  public static final String ER_IDS = "[+]?[0-9][0-9]*";

  public static final boolean validar(String dato, String er) {
    boolean isOk = false;

    //Proceso de validacion
    try {
      //Compila la expresion regular
      Pattern patron = Pattern.compile(er);

      // Generar el motor de busqueda
      Matcher detector = patron.matcher(dato);

      isOk = detector.matches();

    } catch (Exception e) {
      System.out.println("ERROR: Validacion erronea.");
    }

    return isOk;
  }

//Booleans del RegExp
  public static boolean validarPrecio(String precio) {
    return validar(precio, ER_PRECIO);
  }

  public static final boolean validarNombre(String nombre) {
    return validar(nombre, ER_NOMBRE);
  }
  
  public static final boolean validaId(String id){
    return validar(id, ER_IDS);
  }

  //Boolean para la validación en el menú...
  public static final boolean precioOk(double precio) {
    boolean isOk;
    isOk = UtilesValidacion.validarPrecio(Double.toString(precio));
    if (!isOk) {
      System.out.printf("%nPrecio introducido incorrecto.%n"
              + "Recuerda el formato con dos decimales (##.##)%n");
    }
    return isOk;
  }

  //Boolean para la validación en el menú...
  public static final boolean nombreOk(String nombre) {
    boolean isOk;
    isOk = UtilesValidacion.validarNombre(nombre);
    if (!isOk) {
      System.out.printf("%nNombre introducido incorrecto.%n%n");
    }
    return isOk;
  }

  public static final String formatPrecio(double precio) {
    DecimalFormat df = new DecimalFormat("#.##");
    return df.format(precio);
  }
}
