import { Component, OnInit } from '@angular/core';

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

    /*
     * variable que contentra el nombre que se mostrara arriba de la imagen del niño
     */
    public nombre: string;

    /*
     * variable que contentra la ciudad que se mostrara arriba de la imagen del niño 
     */
    public ciudad:string;
    
    constructor(){

    }

    /**
     * @descripcion Inicializacion de la varible nombre y ciudad 
     */
    ngOnInit(): void {
        this.nombre="Erick Javier Varilla Lambertinez";
        this.ciudad="Monteria";
    }

}