const Cancion = require('./Cancion.js');

class Controller{
    constructor(){
        this.canciones = {};
        this.size = 0;
    }

    addCancion(cancion, artista, duracion, productores){
        var nueva_cancion = new Cancion(cancion, artista, duracion, productores);
        this.canciones[parseInt(this.size)] = nueva_cancion;
        this.size++;
    }

    getCanciones(){
        return this.canciones;
    }

    getCancion(id){
        return this.canciones[id];
    }

    getSize(){
        return this.size;
    }

    deleteCancion(id){
        delete this.canciones[id];
    }
}

module.exports = Controller;