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
package org.japo.java.app;

import org.japo.java.entities.Item;
import org.japo.java.entities.ListaItems;
import org.japo.java.libraries.UtilesEntrada;
import org.japo.java.libraries.UtilesValidacion;

/**
 *
 * @author mon_mo
 */
public final class App {

  public static final String MSG_OP = "Introduzca una opción: ";
  public static final String MSG_ERR = "ERROR: Valor introducido incorrecto";
  public static final String MSG_ART = "Introduzca nombre artículo: ";
  public static final String MSG_PRECIO = "Introduzca precio artículo: ";
  public static final String MSG_NEW_PRICE = "Introduzca el precio a modificar: ";

  public final void launchApp() {
    Item item;
    ListaItems lista = new ListaItems();
    boolean isOk;
    boolean ok;

    //
    char opcion;
    String nombre;
    double precio;

    //Añadimos items de prueba
    Item iTest1 = new Item();
    Item iTest2 = new Item("Cebollas", 4.40214);

    lista.addItem(iTest1);
    lista.addItem(iTest2);

    //Empieza menú principal
    do {
      muestraBanner();
      opcion = UtilesEntrada.leerCaracter(MSG_OP, MSG_ERR);
      opcion = Character.toLowerCase(opcion);

      switch (opcion) {
//A - Añadir articulo
        case 'a':
          isOk = false;
          System.out.println("AÑADIR ARTÍCULOS");
          System.out.println("-----------------");
          nombre = UtilesEntrada.leerTexto(MSG_ART);
          do {
            precio = UtilesEntrada.leerReal(MSG_PRECIO, MSG_ERR);
            isOk = true;
          } while (!isOk);

          item = new Item(nombre, precio);
          ok = lista.addItem(item);
          if (ok) {
            System.out.printf("%nArtículo añadido%n");
          } else {
            System.out.printf("%nERROR: Operación NO completada.%n");
          }
          break;
//B - Baja artículo
        case 'b':
          isOk = false;
          System.out.println("BAJA DE ARTÍCULOS");
          System.out.println("-----------------");

          nombre = UtilesEntrada.leerTexto(MSG_ART);
          item = lista.buscaItem(nombre);
          if (item != null) {
            lista.delItem(item);
            System.out.printf("%nITEM dado de baja correctamente%n%n");
          } else {
            System.out.printf("%nERROR: Artículo no encontrado%n");
          }
          break;
//C - Consulta articulo        
        case 'c':
          isOk = false;
          System.out.println("CONSULTA DE ARTÍCULOS");
          System.out.println("---------------------");
          nombre = UtilesEntrada.leerTexto(MSG_ART);
          //Buscar el String
          item = lista.buscaItem(nombre);
          if (item != null) {
            item.muestraInfoItem();
          } else {
            System.out.printf("%nElemento no encontrado en la lista.%n%n");
          }
          break;
//M - Modificación atributis
        case 'm':
          isOk = false;
          System.out.println("CAMBIO DE ATRIBUTOS");
          System.out.println("-------------------");
          System.out.println("OJO: Solo cambia el precio!");
          do {
            //validamos nombre
            nombre = UtilesEntrada.leerTexto(MSG_OP);
            if (!UtilesValidacion.validar(nombre, Item.ER_NOMBRE)) {
              System.out.println("ERROR: Formato de artículo incorrecto");
            } else {
              isOk = true;
            }
          } while (!isOk);

          item = lista.buscaItem(nombre);

          //si queremos que cambie también el nombre es tan fácil como 
          //poner la entrada dentro del if(item != null) que viene a continuación.
          if (item != null) {
            //reset isOk
            isOk = false;
            do {
              System.out.printf("El precio actual es %.2f €%n", item.getPrecio());
              precio = UtilesEntrada.leerEntero(MSG_NEW_PRICE, MSG_ERR);
              isOk = true;
            } while (!isOk);
            item.setPrecio(precio);
            System.out.println("");
            System.out.println("Atributo PRECIO cambiado con éxito");
            System.out.println("");
          } else {
            System.out.println("");
            System.out.println("ERROR: Operación no completada");
          }

//L - Listado    (Display de todos los Items)     
        case 'l':
          if (lista.numItems() > 0) {
            lista.mostrarItems();
          }
          break;
//S - salida
        case 's':
          System.out.println("FIN");
          break;
//DEF          
        default:
          System.out.println("ERROR: Opción introducida incorrecta");
      }
    } while (opcion != 's');

  }

  public void muestraBanner() {
    System.out.println();
    System.out.println("SISTEMA DE ARTÍCULOS");
    System.out.println();
    System.out.println(" Menú principal");
    System.out.println("----------------");
    System.out.println("A.- Alta artículos");
    System.out.println("B.- Baja artículos");
    System.out.println("C.- Consulta artículos");
    System.out.println("M.- Modificación artículos");
    System.out.println("L.- Listado");
    System.out.println();
    System.out.println("---");
    System.out.println("S.- Salir");
  }

}
