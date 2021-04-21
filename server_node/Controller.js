const Cancion = require('./Cancion.js');

class Controller{
    constructor(){
        this.canciones = {};
        this.size = 0;
    }

    addCancion(cancion, artista, duracion, productores){
        var nueva_cancion = new Cancion(cancion, artista, duracion, productores);
        this.canciones[this.size] = nueva_cancion;
        this.size++;
    }

    getCanciones(){
        return this.canciones;
    }
}

module.exports = Controller;