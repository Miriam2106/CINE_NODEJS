const express = require('express');
const router = express.Router();

const pool = require('../database.js');

router.get('/',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    let listPelicula = await pool.query('SELECT * FROM pelicula');
    res.json({
        status:"200",
        message:"Se ha recuperado correctamente",
        listPelicula: listPelicula
    });
});

router.get('/:id',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {id} = req.params;
    let pelicula = await pool.query('SELECT * FROM pelicula WHERE id=?',[id]);
    res.json({
        status:"200",
        message:"Se ha recuperado correctamente",
        pelicula: pelicula
    });
});

router.post('/create',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {titulo, descripcion, sinopsis, rating, categoria} = req.body;
    const dateNow = Date.now();
    const fecha_registro = new Date(dateNow);
    const fecha_actualizacion = new Date(dateNow);
    const estado = 1;

    const pelicula={
        titulo, descripcion, sinopsis, rating, fecha_registro, fecha_actualizacion, estado, categoria
    };
    await pool.query('INSERT INTO pelicula set ?', [pelicula]);
    res.json({
        status:"200",
        message:"Se ha registrado correctamente",
        pelicula: pelicula
    });
});

router.post('/update/:id',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {id} = req.params;
    const {titulo, descripcion, sinopsis, rating, categoria} = req.body;
    const dateNow = Date.now();
    const fecha_actualizacion = new Date(dateNow);

    const pelicula={
        titulo, descripcion, sinopsis, rating, fecha_actualizacion, categoria
    };
    await pool.query('UPDATE pelicula SET ? WHERE id = ?',[pelicula,id]);
    res.json({
        status:"200",
        message:"Se ha actualizado correctamente",
        pelicula: pelicula
    });
});

router.post('/delete/:id',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {id} = req.params;
    await pool.query('UPDATE pelicula SET estado = 0 WHERE id = ?',[id]);
    res.json({
        status:"200",
        message:"Se ha desactivado correctamente"
    });
});

module.exports = router;