import { Component, OnInit } from '@angular/core';

import {ComicObjetoDTO} from '../../dto/comic.objeto.dto'

/**
 * @description Componente nombre-participante, el cual contiene el monbre y ciudad de origen
 * 
 * @author Eric Javier Varilla Lambertinez
 */
@Component({
    selector:'nombre-participante',
    templateUrl:'./nombre-participante-component.html'
})
export class  NombreParticipanteComponent implements OnInit{


    /**
     * Array del modelo Comic que contiene todos los atritubos para llenar el array
     */
    public Arraycomic: Array<ComicObjetoDTO>;
 
    /*
     * variable que contentra el nombre que se mostrara arriba de la imagen del niño
     */
    public nombre: string;

    /*
     * variable que contentra la ciudad que se mostrara arriba de la imagen del niño 
     */
    public ciudad:string;

   public deleteDato:number;

   public nombreComic:String;
   public NombreComicBorrado:boolean;
    
    // Constructor
    constructor(){

    }

    /**
     * @descripcion Inicializacion de la varible nombre y ciudad 
     */
    ngOnInit(): void {
        this.nombre="Erick Javier Varilla Lambertinez";
        this.ciudad="Monteria";

       this.NombreComicBorrado=false;
        this.Arraycomic=[
            new ComicObjetoDTO(1,'Batman','Normal','Terror',600,290.00,'Diego Fernando',true,new Date('10-10-2019'),'ACTIVO'),
            new ComicObjetoDTO(2,'SuperMan','Normal','Accion',700,390.00,'Juan Pablo',true,new Date('01-02-2019'),'ACTIVO'),
            new ComicObjetoDTO(3,'Pantera Negra','Normal','Suspenso',600,290.00,'Diego Fernando',true,new Date('03-03-2019'),'ACTIVO'),
            new ComicObjetoDTO(4,'Flash','Normal','Adulto',600,290.00,'Diego Fernando',true,new Date('04-04-2019'),'ACTIVO'),
            new ComicObjetoDTO(5,'Acuaman','Normal','TerInfantilror',600,290.00,'Diego Fernando',true,new Date('08-10-2019'),'ACTIVO')
            ];// Fin   this.Arraycomic
 
    }// Fin ngOnInit


    /**
     * Metodo para elimiar un elemento del Arraycomic
     */
    deleteArraycomic(dato:number){
        this.Arraycomic.splice(dato,1);
    }

    /**
     * Eliminar un Dato del array por el id introducido
     */
    deleteIdComic(){
        
       let contadorIte=0;
           this.Arraycomic.forEach((dato,index)=>{
            let id = this.Arraycomic[index].id;
            console.log(this.Arraycomic[index].id);
            console.log(this.deleteDato);
            let numero= Number(this.deleteDato);
           
            if(numero > this.Arraycomic.length || numero < 0){
                alert('El ID introducido No se encuentra registrado en la base de datos... '+numero);
                this.NombreComicBorrado=false;
            }else{
                contadorIte++;
                // let indVC=this.Arraycomic.findIndex(val=>val.id === numero);
                // this.Arraycomic.splice(indVC,1);
                 if(id===numero){
                    contadorIte=0;
                    this.NombreComicBorrado=true;
                 // alert('Este ID comic Eliminado'+this.Arraycomic[index].nombre);
                 this.nombreComic=this.Arraycomic[index].nombre;
                     this.Arraycomic.splice(index,1);
                    
                 }else{
                     if(contadorIte==this.Arraycomic.length){
                        alert('El ID introducido No se encuentra registrado en la base de datos... '+numero);
                this.NombreComicBorrado=false;
                     }
                 }
            }
           
      });
    }

}// Fin Class NombreParticipanteComponent
