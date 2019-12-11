/**
 * @description Clase ComicObjetoDTO que contiene la informacion de un comic
 * 
 * @author Eric Varilla
 */
export class ComicObjetoDTO {
    constructor(
        public id: number,
        public nombre: String,
        public editorial: String,
        public tematica: String,
        public numeroPaginas: number,
        public precio: number,
        public autores: String,
        public aColor: boolean,
        public fechaVenta: Date,
        public estado: String

    ) {}
}