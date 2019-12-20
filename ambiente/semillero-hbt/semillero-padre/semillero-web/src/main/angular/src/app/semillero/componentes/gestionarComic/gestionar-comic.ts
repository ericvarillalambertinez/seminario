import {
    ComicDTO
} from '../../dto/comic.dto';
import {
    Component,
    OnInit
} from '@angular/core';
import {
    FormGroup,
    FormBuilder,
    Validators
} from '@angular/forms';
import {
    Router
} from '@angular/router';
import { EjemploService } from '../../services/ejemplo.service';
import { ok } from 'assert';

/**
 * @description Componenete gestionar comic, el cual contiene la logica CRUD
 * 
 * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
 */
@Component({
    selector: 'gestionar-comic',
    templateUrl: './gestionar-comic.html',
    styleUrls: ['./gestionar-comic.css']
})
export class GestionarComicComponent implements OnInit {

    /**
     * Atributo que contiene los controles del formulario
     */
    public gestionarComicForm: FormGroup;

    /**
     * Atributo que contendra la informacion del comic
     */
    public comic: ComicDTO;

    /**
     * Atributo que contendra la lista de comics creados
     */
    public listaComics: Array < ComicDTO > ;

    public idComic: number = 0;

    /**
     * Atributo que indica si se envio a validar el formulario
     */
    public submitted: boolean;

    /**
     * Atributo que indica que boton hay habilitar para crear o editar un comic
     */
    public banderaBoton: boolean;

     /**
     * Atributo que indica cuando se borre un comic
     */
    public banderaMensajeBorrar: boolean;

    /**
     * Almacena el nombre del comic para mostrarlo en el mensaje de borrado
     */
    public nombreComicBorrado: String;

    /**
     * Atributo que contendra el id del comic que se desea editar
     */
    public idComicEditar:string;

    public mensajeExito: String;
    public banderaMensaje:boolean;
    /**
     * @description Este es el constructor del componente GestionarComicComponent
     * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
     */
    constructor(private fb: FormBuilder,
        private router: Router,
        private ejemploServicio: EjemploService) {
        this.gestionarComicForm = this.fb.group({
            nombre: [null, Validators.required],
            editorial: [null],
            tematica: [null],
            coleccion: [null],
            numeroPaginas: [null],
            precio: [null],
            autores: [null],
            color: [null]
        });
    }

    /**
     * @description Evento angular que se ejecuta al invocar el componente
     * @author Diego Fernando Alvarez Silva <dalvarez@heinsohn.com.co>
     */
    ngOnInit(): void {
        console.log("Ingreso al al evento oninit");
        this.comic = new ComicDTO();
        this.listaComics = new Array < ComicDTO > ();
        this.consultarComic();
    }


/**
 * @description Metodo para listar los comic desde la base de datos por medio del servicio rest 
 */
    private consultarComic(){
        this.ejemploServicio.consultarComics().subscribe(listaComics =>{
            this.listaComics = listaComics;
        },Error=>{
            console.log("Se a producido un error al listar los comic" + Error);
        });
    }

    /**
     * @description Metodo que permite validar el formulario y crear  un comic
     */
    public crearActualizarComic(): void {
        this.banderaMensajeBorrar=false;
        this.submitted = true;
        if (this.gestionarComicForm.invalid) {
            return;
        }
    
        this.comic = new ComicDTO();
        
        this.comic.nombre = this.gestionarComicForm.controls.nombre.value;
        this.comic.editorial = this.gestionarComicForm.controls.editorial.value;
        this.comic.tematicaEnum = this.gestionarComicForm.controls.tematica.value;
        this.comic.coleccion = this.gestionarComicForm.controls.coleccion.value;
        this.comic.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
        this.comic.precio = this.gestionarComicForm.controls.precio.value;
        this.comic.autores = this.gestionarComicForm.controls.autores.value;
        this.comic.color = this.gestionarComicForm.controls.color.value;
        this.comic.cantidad=3;
        this.comic.estado="ACTIVO"
        this.ejemploServicio.crearComic(this.comic).subscribe(crearComic=>{
            this.mensajeExito= crearComic.mensajeEjecucion;
            this.banderaMensaje= true;
            this.limpiarFormulario();

            this.consultarComic();
        },error=>{
            console.log("Error al crear el comic ");
        });
        
      
    }

    /**
     * Metodo que permite consultar un comic de la tabla y sus detalles se habilitan en el formulario
     * @param posicion en la lista del comic seleccionado
     */
    public editarComic(posicion: number): void {
        this.banderaMensajeBorrar=false;
        this.banderaBoton = true;
       
        let comic = this.listaComics[posicion];
        this.idComicEditar = comic.id;
        this.gestionarComicForm.controls.nombre.setValue(comic.nombre);
        this.gestionarComicForm.controls.editorial.setValue(comic.editorial);
        this.gestionarComicForm.controls.tematica.setValue(comic.tematicaEnum);
        this.gestionarComicForm.controls.coleccion.setValue(comic.coleccion);
        this.gestionarComicForm.controls.numeroPaginas.setValue(comic.numeroPaginas);
        this.gestionarComicForm.controls.precio.setValue(comic.precio);
        this.gestionarComicForm.controls.autores.setValue(comic.autores);
        this.gestionarComicForm.controls.color.setValue(comic.color);
        this.gestionarComicForm.controls.nombre.enable();
        this.gestionarComicForm.controls.editorial.enable();
        this.gestionarComicForm.controls.tematica.enable();
        this.gestionarComicForm.controls.coleccion.enable();
        this.gestionarComicForm.controls.numeroPaginas.enable();
        this.gestionarComicForm.controls.precio.enable();
        this.gestionarComicForm.controls.autores.enable();
        this.gestionarComicForm.controls.color.enable();
        //        this.gestionarComicForm.controls.color.enable(); para habilitar el campo

    }

    /**
     * @description Metodo que permite validar el formulario y  actulizar un comic
     */
    public actualizarComic(): void {
        this.banderaMensajeBorrar=false;
        this.submitted = true;
        if (this.gestionarComicForm.invalid) {
            return;
        }
        this.comic = new ComicDTO();
        this.comic.id = this.idComicEditar;
        this.comic.nombre = this.gestionarComicForm.controls.nombre.value;
        this.comic.editorial = this.gestionarComicForm.controls.editorial.value;
        this.comic.tematicaEnum = this.gestionarComicForm.controls.tematica.value;
        this.comic.coleccion = this.gestionarComicForm.controls.coleccion.value;
        this.comic.numeroPaginas = this.gestionarComicForm.controls.numeroPaginas.value;
        this.comic.precio = this.gestionarComicForm.controls.precio.value;
        this.comic.autores = this.gestionarComicForm.controls.autores.value;
        this.comic.color = this.gestionarComicForm.controls.color.value;
        this.comic.cantidad=3;
        this.comic.estado="ACTIVO"

        this.ejemploServicio.modificarComic(JSON.stringify(this.comic)).subscribe(resultado=>{
            console.log(ok);
        },error=>{
            console.log(error);
        });


        this.banderaBoton = false;
        this.limpiarFormulario();

    }


    /**
     * Metodo que al recivir la posicion donde se encuentra almacenada la informacion de array 
     * elimina los datos en dicha posicion 
     */
    public eliminarComic(comic : ComicDTO):void{
        console.log(comic);
       // var p =  parseInt(posicion,10);
        //console.log(p);
        this.banderaMensajeBorrar=true;
        this.ejemploServicio.eliminarComic(comic.id).subscribe(resultado=>{
            this.ejemploServicio.eliminarComic;
            console.log(this.ejemploServicio.eliminarComic);
            this.consultarComic();
        },error=>{
            console.log(error.text);
            console.log(error.error.text);
            this.consultarComic();
        });
        // this.nombreComicBorrado= this.listaComics[posicion].nombre
        // this.listaComics.splice(posicion,1);

    }

    private limpiarFormulario(): void {
        this.submitted = false;
        this.gestionarComicForm.controls.nombre.setValue(null);
        this.gestionarComicForm.controls.editorial.setValue(null);
        this.gestionarComicForm.controls.tematica.setValue(null);
        this.gestionarComicForm.controls.coleccion.setValue(null);
        this.gestionarComicForm.controls.numeroPaginas.setValue(null);
        this.gestionarComicForm.controls.precio.setValue(null);
        this.gestionarComicForm.controls.autores.setValue(null);
        this.gestionarComicForm.controls.color.setValue(null);
    }

    /**
     * @description Metodo que obtiene los controles y sus propiedades
     * @author Diego Fernando Alvarez Silva
     */
    get f() {
        return this.gestionarComicForm.controls;
    }
}