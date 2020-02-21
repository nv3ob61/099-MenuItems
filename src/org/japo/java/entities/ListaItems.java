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
package org.japo.java.entities;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author CicloM
 */
public class ListaItems {

  private ArrayList<Item> lista = new ArrayList<>();

  public ListaItems() {
    lista = new ArrayList<>();
  }

  //Método para añadir un vehículo
  public boolean addItem(Item i) {
    boolean isOk = false;
    if (i != null) {
      isOk = lista.add(i);
    }
    return isOk;
  }

  //método para borrar un vehículo
  public boolean delItem(Item i) {
    boolean del = false;
    if (i != null) {
      del = lista.remove(i);
    }
    return del;
  }

    //Método que busca vehículo por la matrícula.
  public Item buscaItem(String nombre) {
    Item i;
    Item enc = null;
    boolean exit = false;
    Iterator<Item> it = lista.iterator();

    while (exit == false && it.hasNext()) {
      i = it.next();
      if (i.getNombre().equals(nombre)) {
        exit = true;
        enc = i;
      }
    }
    return enc;
  }
  
    
  public void mostrarItems(){
    lista.forEach((i) -> {
      System.out.println();
      i.muestraInfoItem();
    });
  }
  
    public int numItems() {
    return lista.size();
  }
}
