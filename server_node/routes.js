const Router = require('@koa/router');
const router = new Router();
const Controller = require('./Controller.js');

controller = new Controller();

controller.addCancion("Chun-Li", "Nicki Minaj", "3:11", "Nicki Minaj &  J. Reid");
controller.addCancion("Starboy", "The Weeknd", "3:50", "Daft Punk");
controller.addCancion("Formation", "Beyoncé", "3:26", "Mike Will Made It");

router.get('/canciones', (ctx) => {
    ctx.status = 200;
    ctx.body = controller.getCanciones();
});

router.get('/canciones/:id', (ctx) => {
    ctx.status = 200;
    ctx.body = controller.getCancion(ctx.params.id);
});

router.post('/canciones', (ctx) => {
    body = ctx.request.body;
    ctx.status = 201;
    controller.addCancion(body.cancion, body.artista, body.duracion, body.productores);
    ctx.body = {msg : 'Canción creada con éxito.'};
});

router.delete('/canciones/:id', (ctx) => {
    controller.deleteCancion(ctx.params.id);
});

module.exports = router;