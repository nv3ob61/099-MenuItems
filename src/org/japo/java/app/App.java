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
  public static final String MSG_ID = "Introduzca ID del artículo: ";
  public static final String MSG_PRECIO = "Introduzca precio artículo: ";
  public static final String MSG_NEW_PRICE = "Introduzca el nuevo precio: ";

  public final void launchApp() {
    Item item;
    ListaItems lista = new ListaItems();
    boolean isOk;
    boolean ok;
    //
    char opcion;
    String nombre;
    String id;
    double precio;

    //Añadimos items de prueba, varios decimales. 
    //Actual DecimalFormat en la validación.
    Item iTest1 = new Item();
    Item iTest2 = new Item("Cebollas", 4.40);
    Item iTest3 = new Item("Naranjas", 9.99);
    Item iTest4 = new Item("Arbóndigas", 12.33);
    //Añadimos los 3 artículos a la lista mediante el método addItem(Item);
    lista.addItem(iTest1);
    lista.addItem(iTest2);
    lista.addItem(iTest3);
    lista.addItem(iTest4);

//Empieza menú principal. FALTA VALIDAR NOMBRESSS!
    do {
      muestraBanner();
      opcion = UtilesEntrada.leerCaracter(MSG_OP, MSG_ERR);
      opcion = Character.toLowerCase(opcion);

      switch (opcion) {
//A - Añadir articulo
        case 'a':
          isOk = false;
          addItemsBanner();
          nombre = UtilesEntrada.leerTexto(MSG_ART);
          if (UtilesValidacion.nombreOk(nombre)) {
            do {
              System.out.println("---");
              precio = UtilesEntrada.leerReal(MSG_PRECIO, MSG_ERR);
              isOk = UtilesValidacion.precioOk(precio);
            } while (!isOk);

            item = new Item(nombre, precio);
            ok = lista.addItem(item);
            if (ok) {
              System.out.printf("%nArtículo añadido%n");
              System.out.println("---");
            } else {
              System.out.printf("%nERROR: Operación NO completada.%n");
              System.out.println("---");
            }
          } else {
            //si falla sale el mensajde desde UtilesValidación....
//            System.out.printf("%nERROR: Dato introducido incorrecto.%n");
          }
          break;
//B - Baja artículo por ID
        case 'b':
          isOk = false;
          delItemsBanner();
          id = UtilesEntrada.leerTexto(MSG_ID);
          if (UtilesValidacion.validaId(id)) {
            item = lista.buscaItemId(id);
            if (item != null) {
              lista.delItem(item);
              System.out.printf("%nITEM dado de baja correctamente%n%n");
            } else {
              System.out.printf("%nERROR: Artículo no encontrado%n%n");
            }
          } else {
            System.out.println("MENSAJE DE TEST: SI FALLA LA INTRO "
                    + "DEL MENU B, SALE DIRECTAMENTE DE MOMENTO....");
          }
          break;
//C - Consulta articulo        
        case 'c':
          isOk = false;
          consultaBanner();
          nombre = UtilesEntrada.leerTexto(MSG_ART);
          if (UtilesValidacion.nombreOk(nombre)) {
            //Buscar el String
            item = lista.buscaItem(nombre);
            if (item != null) {
              item.muestraInfoItem();
            } else {
              System.out.printf("%nElemento no encontrado en la lista.%n%n");
            }
          }
          break;
//D - Consulta articulo por ID      
        case 'd':
          isOk = false;
          consultaIdBanner();
          id = UtilesEntrada.leerTexto(MSG_ID);
          if (UtilesValidacion.validaId(id)) {
            //Buscar el String
            item = lista.buscaItemId(id);
            if (item != null) {
              item.muestraInfoItem();
            } else {
              System.out.printf("%nElemento no encontrado en la lista.%n%n");
            }
          } else {
            System.out.println("MENSAJE DE TEST: SI FALLA LA INTRO "
                    + "DEL MENU D, SALE DIRECTAMENTE DE MOMENTO....");
          }
          break;
//M - Modificación atributis por nombre, primera ocurrencia.
        case 'm':
          isOk = false;
          cambiosBanner();
          do {
            //validamos nombre
            nombre = UtilesEntrada.leerTexto(MSG_ART);
            if (!UtilesValidacion.validar(nombre, UtilesValidacion.ER_NOMBRE)) {
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
              precio = UtilesEntrada.leerReal(MSG_NEW_PRICE, MSG_ERR);
              isOk = UtilesValidacion.precioOk(precio);
            } while (!isOk);
            item.setPrecio(precio);
            System.out.printf("%nAtributo PRECIO cambiado con éxito%n%n");
          } else {
            System.out.printf("%nERROR: Operación no completada%n%n");
          }
          break;
//N - Modificación atributis por id.
        case 'n':
          isOk = false;
          cambiosIdBanner();
          id = UtilesEntrada.leerTexto(MSG_ID);
          if (UtilesValidacion.validaId(id)) {
            item = lista.buscaItemId(id);

            if (item != null) {
              //reset isOk
              do {
                System.out.println();
                item.muestraInfoItem();
                precio = UtilesEntrada.leerReal(MSG_NEW_PRICE, MSG_ERR);
                isOk = UtilesValidacion.precioOk(precio);
              } while (!isOk);
              item.setPrecio(precio);
              System.out.printf("%nAtributo PRECIO cambiado con éxito%n%n");
            } else {
              System.out.printf("%nERROR: Item no está en la lista%n%n");
            }
          }
          else {
            System.out.println("ERROR: Formato introducido erróneo.");
          }
          break;
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
    System.out.println("SISTEMA DE ARTÍCULOS");
    System.out.println();
    System.out.println(" Menú principal");
    System.out.println("----------------");
    System.out.println("A.- Alta artículos");
    System.out.println("B.- Baja artículos (ID)");
    System.out.println("C.- Consulta primer artículo por nombre");
    System.out.println("D.- Consulta artículo por ID");
    System.out.println("M.- Modificación artículos por nombre");
    System.out.println("N.- Modificación artículos por ID");
    System.out.println("L.- Listado");
    System.out.println();
    System.out.println("---");
    System.out.println("S.- Salir");
  }

  public void addItemsBanner() {
    System.out.println("AÑADIR ARTÍCULOS");
    System.out.println("-----------------");
  }

  public void delItemsBanner() {
    System.out.println("BAJA DE ARTÍCULOS");
    System.out.println("-----------------");
  }

  public void consultaBanner() {
    System.out.println("CONSULTA DE ARTÍCULOS POR NOMBRE");
    System.out.println("--------------------------------");
  }

  public void consultaIdBanner() {
    System.out.println("CONSULTA DE ARTÍCULOS POR ID");
    System.out.println("----------------------------");
  }

  public void cambiosBanner() {
    System.out.println("CAMBIO DE ATRIBUTOS POR NOMBRE");
    System.out.println("------------------------------");
    System.out.println("OJO: Solo cambia el precio!");
  }

  public void cambiosIdBanner() {
    System.out.println("CAMBIO DE ATRIBUTOS POR ID");
    System.out.println("--------------------------");
    System.out.println("OJO: Solo cambia el precio!");
  }
}
