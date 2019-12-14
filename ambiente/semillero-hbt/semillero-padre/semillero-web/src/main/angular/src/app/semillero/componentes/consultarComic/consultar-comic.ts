import {
    OnInit,
    Component
} from '@angular/core';
import {
    ComicDTO
} from 'src/app/app.module';
import {
    FormGroup,
    FormBuilder,
    Validators
} from '@angular/forms';
import {
    Router,
    ActivatedRoute
} from '@angular/router';



/**
 * @description Component consultar-comic el cual mostrar en una vista la informacion de un comic pero no permitira 
 * editarla
 * @author Eric Varilla
 */
@Component({
    selector: 'consultar-comic',
    templateUrl: './consultar-comic.html'
})
export class ConsultarComicComponent implements OnInit {

    /**
     * Atributo que contiene los controles del formulario
     */
    public gestionarComicForm: FormGroup;



    constructor(private router: Router, private activatedRoute: ActivatedRoute, private fb: FormBuilder, ) {
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

    ngOnInit(): void {

        let comic = this.activatedRoute.snapshot.params;
        this.consultarComic(comic);
    }


    /**
     * Metodo que permite consultar un comic de la tabla y sus detalles e inhabilitar el formulario
     * @param posicion en la lista del comic seleccionado
     */
    public consultarComic(comic: any): void {
        this.gestionarComicForm.controls.nombre.setValue(comic.nombre);
        this.gestionarComicForm.controls.editorial.setValue(comic.editorial);
        this.gestionarComicForm.controls.tematica.setValue(comic.tematica);
        this.gestionarComicForm.controls.coleccion.setValue(comic.coleccion);
        this.gestionarComicForm.controls.numeroPaginas.setValue(comic.numeroPaginas);
        this.gestionarComicForm.controls.precio.setValue(comic.precio);
        this.gestionarComicForm.controls.autores.setValue(comic.autores);
        this.gestionarComicForm.controls.color.setValue(comic.color);
        this.gestionarComicForm.controls.nombre.disable();
        this.gestionarComicForm.controls.editorial.disable();
        this.gestionarComicForm.controls.tematica.disable();
        this.gestionarComicForm.controls.coleccion.disable();
        this.gestionarComicForm.controls.numeroPaginas.disable();
        this.gestionarComicForm.controls.precio.disable();
        this.gestionarComicForm.controls.autores.disable();
        this.gestionarComicForm.controls.color.disable();
        //        this.gestionarComicForm.controls.color.enable(); para habilitar el campo


    }



}