package com.aluralatam.retoliteratura.literaturapersistente.principal;

import com.aluralatam.retoliteratura.literaturapersistente.modelos.Autor;
import com.aluralatam.retoliteratura.literaturapersistente.modelos.DatosLibros;
import com.aluralatam.retoliteratura.literaturapersistente.modelos.DatosListaLibros;
import com.aluralatam.retoliteratura.literaturapersistente.modelos.Libro;
import com.aluralatam.retoliteratura.literaturapersistente.repository.AutorRepositorio;
import com.aluralatam.retoliteratura.literaturapersistente.repository.LibroRepositorio;
import com.aluralatam.retoliteratura.literaturapersistente.servicios.ConvierteDatos;
import com.aluralatam.retoliteratura.literaturapersistente.servicios.Servicios;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private Servicios consumoApi=new Servicios();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convertirDatos=new ConvierteDatos();
    private List<Libro> libros;
    private List<Autor> autores;
    private LibroRepositorio repositorio;
    private AutorRepositorio autorRepositorio;

    public Principal(LibroRepositorio repository, AutorRepositorio repository2) {
        this.repositorio = repository;
        this.autorRepositorio=repository2;
    }

    public void MostrarMenu(){
//        var json=consumoApi.obtenerDatos(URL_BASE);
//        var datos=convertirDatos.obtenerDatos(json, DatosListaLibros.class);
//        System.out.println(datos);
//        System.out.println("Datos");
//
//        List<Libro> listaDeLibros= datos.libros().stream()
//                .map(Libro::new)
//                .toList();
//
//        listaDeLibros.stream()
//                .limit(10)
//                .forEach(System.out::println);
//
//
//        System.out.println("\n Top 10 libros mas descargados");
//        listaDeLibros.stream()
//                .sorted(Comparator.comparing(Libro::getDescargas).reversed())
//                .limit(10)
//                .map(l->l.getNombre().toUpperCase())
//                .forEach(System.out::println);
//
//        System.out.println("Ingrese un nombre de un libro a buscar: ");
//        var nombreLibro=teclado.nextLine();
//
//        json=consumoApi.obtenerDatos(URL_BASE+"?search="+nombreLibro.replace(" ","+"));
//        var datosBusqueda=convertirDatos.obtenerDatos(json,DatosListaLibros.class);
//
//        Optional<DatosLibros> libroBuscado = datosBusqueda.libros().stream()
//                .filter(l->l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
//                .findFirst();
//
//        if(libroBuscado.isPresent()){
//            System.out.println(" Libro encontrado");
//            System.out.println("Los datos son: " + libroBuscado.get());
//        } else {
//            System.out.println("Libro no encontrado");
//        }
//
//        //Estadisticas
//
//        DoubleSummaryStatistics estadisticas=listaDeLibros.stream()
//                .filter(d-> d.getDescargas()>0)
//                .collect(Collectors.summarizingDouble(Libro::getDescargas));
//        System.out.println("Cantidad media de descargas: " + estadisticas.getAverage());
//        System.out.println("Cantidad maxima de descargas:" + estadisticas.getMax());
//        System.out.println("Cantidad minima de descargas: " + estadisticas.getMin());
//        System.out.println("Cantidad de registros evaluados para calcular las estadisticas: " + estadisticas.getCount());

            var opcion = -1;
            while (opcion != 0) {
                try {

                var menu = """
                    1 - Buscar Libro por Titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    6 - Buscar Autor por Nombre
                    7 - Ver Estadisticas Generales de descargas
                    8 - Ver Top 10 libros más descargados
                    
                    
                    0 - Salir
                    """;
                System.out.println(menu);
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroWeb();
                        break;
                    case 2:
                        mostrarLibrosBuscados();
                        break;
                    case 3:
                        mostrarAutoresBuscados();
                        break;
                    case 4:
                        buscarAutoresPorTiempo();
                        break;
                    case 5:
                        mostrarLibrosIdioma();
                        break;
                        case 6:
                        buscarAutorPorNombre();
                        break;
                        case 7:
                        mostrarEstadisticasDescargas();
                        break;
                        case 8:
                        top10LibrosDescargados();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
                }catch (InputMismatchException e){
                    System.out.println("""
                            ------------------------------------
                            Ingreso de una opcion invalida
                            Cerrando la aplicacion....
                            ------------------------------------
                            """);
                    opcion=0;
                }
            }

        }

    private void top10LibrosDescargados() {
        libros = repositorio.findTop10ByOrderByDescargasDesc();
        String textoLibro="""
                        ------- LIBRO ----------
                        Titulo: %s
                        Autor: %s
                        Idioma: %s
                        Numero de Descargas: %d
                        ------------------------
                        """;
        if (!libros.isEmpty()){
            libros.forEach(l-> System.out.printf((textoLibro) + "%n",l.getNombre(),l.getAutores().getNombre(),l.getLenguajes(),l.getDescargas()));
        }else{
            System.out.println("""
                    ---------------------------------------------------------
                    No se encontro ningun Autor registrado 
                    ---------------------------------------------------------
                    """);
        }
    }

    private void mostrarEstadisticasDescargas() {
        libros = repositorio.findAll();
        String textoLibro="""
                        ------- Estadisticas Descargas ----------
                        Cantidad media de descargas: %.2f
                        Cantidad maxima de descargas: %.2f
                        Cantidad minima de descargas: %.2f
                        Cantidad de registros evaluados para calcular las estadisticas: %d
                        -----------------------------------------
                        """;
        if (!libros.isEmpty()){
            DoubleSummaryStatistics estadisticas=libros.stream()
                    .filter(d-> d.getDescargas()>0)
                    .collect(Collectors.summarizingDouble(Libro::getDescargas));
            System.out.printf((textoLibro) + "%n",estadisticas.getAverage(),estadisticas.getMax(),estadisticas.getMin(),estadisticas.getCount());
        }else{
            System.out.println("""
                    ---------------------------------------------------------
                    No se encontro ningun Libro registrado 
                    ---------------------------------------------------------
                    """);
        }


    }


    private void buscarLibroWeb() {
        Optional<DatosLibros> datos = getDatosLibros();
        List<Libro> libroBuscado=new ArrayList<>();
        if(datos.isPresent()){
            Autor autor = datos.get().autores().stream()
                .map(a->new Autor(a.nombre(),a.fechaNacimiento(),a.fechaMuerte()))
                .toList()
                .get(0);
            Libro libro = new Libro(datos.get());
            libroBuscado.add(libro);
            autor.setLibros(libroBuscado);

            Optional<Autor> consultaAutor=autorRepositorio.findBynombre(autor.getNombre());
            if (consultaAutor.isPresent()){
                libro.setAutores(consultaAutor.get());
                repositorio.save(libro);
            }else {
                autorRepositorio.save(autor);
            }

        }
    }

        private Optional<DatosLibros> getDatosLibros() {
            System.out.println("Escribe el nombre del libro que deseas buscar");
            var nombreLibro = teclado.nextLine();
            var json = consumoApi.obtenerDatos(URL_BASE+"?search="+ nombreLibro.replace(" ", "+"));
            System.out.println(json);

            var datosBusqueda=convertirDatos.obtenerDatos(json,DatosListaLibros.class);
            Optional<DatosLibros> libroBuscado = datosBusqueda.libros().stream()
                    .filter(l->l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                    .findFirst();

                if(libroBuscado.isPresent()){
                    if(repositorio.findBynombre(libroBuscado.get().titulo()).isPresent()){
                        System.out.println("""
                                -------------------------------------
                                Este Libro ya se encuentra almacenado
                                -------------------------------------
                                """);
                        return Optional.empty();
                    }else{

                        DatosLibros infolibro=libroBuscado.get();
                        var textoLibro=String.format("""
                                ------- LIBRO ----------
                                Titulo: %s
                                Autor: %s
                                Idioma: %s
                                Numero de Descargas: %d
                                ------------------------
                                """,infolibro.titulo(),infolibro.autores().get(0).nombre(),infolibro.lenguajes().get(0),infolibro.totalDescargas());
                        System.out.println(textoLibro);
                    }
                } else {
                    System.out.println("""
                            -------------------------
                            Libro no encontrado
                            -------------------------
                            """);
                }
                return libroBuscado;


        }



        private void mostrarLibrosBuscados() {
            libros = repositorio.findAll();
            String textoLibro="""
                        ------- LIBRO ----------
                        Titulo: %s
                        Autor: %s
                        Idioma: %s
                        Numero de Descargas: %d
                        ------------------------
                        """;
        if (!libros.isEmpty()){
            libros.forEach(l-> System.out.printf((textoLibro) + "%n",l.getNombre(),l.getAutores().getNombre(),l.getLenguajes(),l.getDescargas()));
        }else{
        System.out.println("""
                    ---------------------------------------------------------
                    No se encontro ningun Autor registrado 
                    ---------------------------------------------------------
                    """);
    }

        }

    private void mostrarAutoresBuscados() {
        autores=autorRepositorio.obtenerAutoresRegistrados();
    if (!autores.isEmpty()){
        autores.forEach(a -> {
                    List<String> librosAutor=a.getLibros().stream()
                            .map(Libro::getNombre)
                            .toList();
                    var textoAutor= String.format("""
                            Autor: %s
                            Fecha de Nacimiento: %d
                            Fecha de Fallecimiento: %d
                            Libros: %s
                            """,a.getNombre(),a.getFechaNacimiento(),a.getFechaMuerte(),librosAutor);
                    System.out.println(textoAutor);
                });
    }else{
        System.out.println("""
                    ---------------------------------------------------------
                    No se encontro ningun Autor registrado 
                    ---------------------------------------------------------
                    """);
    }

    }
    private void buscarAutoresPorTiempo() {
        System.out.println("Escribe el año del autor que deseas buscar");
        Integer fechaVivo = teclado.nextInt();
        autores=autorRepositorio.obtenerAutoresPorFechas(fechaVivo);
        if (!autores.isEmpty()){

        autores.forEach(a -> {
            List<String> librosAutor=a.getLibros().stream()
                    .map(Libro::getNombre)
                    .toList();
            var textoAutor= String.format("""
                            Autor: %s
                            Fecha de Nacimiento: %d
                            Fecha de Fallecimiento: %d
                            Libros: %s
                            """,a.getNombre(),a.getFechaNacimiento(),a.getFechaMuerte(),librosAutor);
            System.out.println(textoAutor);
        });
        }else{
            System.out.println(String.format("""
                    ---------------------------------------------------------
                    No se encontro ningun Autor vivo registrado en el año %d 
                    ---------------------------------------------------------
                    """,fechaVivo));
        }

    }
    private void mostrarLibrosIdioma() {
        List<String> lenguajes=repositorio.buscarLenguajes();
        if (!lenguajes.isEmpty()){
        String menuLenguajes= """
                ---------------IDIOMA--------------------
                Seleccione el lenguaje desea buscar:
                """;
            String textoLibro="""
                        ------- LIBRO ----------
                        Titulo: %s
                        Autor: %s
                        Idioma: %s
                        Numero de Descargas: %d
                        ------------------------
                        """;
        System.out.println(menuLenguajes);
        lenguajes.forEach(l-> {
            System.out.println((lenguajes.indexOf(l) + 1) + " - " + l);
        });
        var lenguajeLibro = teclado.nextInt();
            libros = repositorio.buscarLibrosLenguajes(lenguajes.get(lenguajeLibro - 1));
            if (!libros.isEmpty()){
                libros.forEach(l-> System.out.printf((textoLibro) + "%n",l.getNombre(),l.getAutores().getNombre(),l.getLenguajes(),l.getDescargas()));
            }else{
                System.out.println("""
                    ---------------------------------------------------------
                    No se encontro ningun Autor registrado 
                    ---------------------------------------------------------
                    """);
            }
        }else {
            System.out.println("""
                    ---------------------------------------------------------
                    No se encontro ningun idioma registrado 
                    ---------------------------------------------------------
                    """);
        }



    }
    private void buscarAutorPorNombre(){
        System.out.println("Escribe el nombre del autor que deseas buscar");
        var nombreAutor = teclado.nextLine();
        Optional<Autor> escritor=autorRepositorio.autorPorNombre(nombreAutor);
        if (escritor.isPresent()){
            Autor escritorEncontrado=escritor.get();
            List<String> librosAutor=escritorEncontrado.getLibros().stream()
                    .map(Libro::getNombre)
                    .toList();
            var textoAutor= String.format("""
                            Autor: %s
                            Fecha de Nacimiento: %d
                            Fecha de Fallecimiento: %d
                            Libros: %s
                            """,escritorEncontrado.getNombre(),escritorEncontrado.getFechaNacimiento(),escritorEncontrado.getFechaMuerte(),librosAutor);
            System.out.println(textoAutor);

        }else{
            System.out.printf("""
                    ---------------------------------------------------------
                    No se encontro ningun Autor registrado con el nombre: %s
                    ---------------------------------------------------------
                    %n""",nombreAutor);
        }
    }






}
