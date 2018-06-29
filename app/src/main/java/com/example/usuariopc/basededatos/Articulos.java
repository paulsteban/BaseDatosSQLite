package com.example.usuariopc.basededatos;
import java.io.Serializable;

public class Articulos implements Serializable {

    String codigo, descripcion, precio;
    Articulos tiendaPro[];

        public Articulos() {
        }

        public Articulos(String codigo, String descripcion, String precio) {
            this.codigo = codigo;
            this.descripcion = descripcion;
            this.precio = precio;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getPrecio() { return precio; }

        public void setPrecio(String precio) { this.precio = precio; }

        @Override
        public String toString() {
            return "Codigo :"+this.codigo + " Descripcion: "+this.descripcion +" Precio: "+this.precio ;
        }

  /*      public Articulos [] cargarProducto(){
            tiendaPro = new Articulos[] {
                    new Articulos("PortÃ¡til Asus Rog Strix","Asus","1500"),
                    new Producto("PortÃ¡til MSI GS63 7RD-096XES Stealth Pro","MSI","1100"),
                    new Producto("PortÃ¡til HP Pavilion 15-BC301NS","HP","850"),
                    new Producto("PortÃ¡til Lenovo Legion Y520-15IKBN","Lenovo", "800")

            };
            return tiendaPro;
        }*/


    }