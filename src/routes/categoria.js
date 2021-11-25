const express = require('express');
const router = express.Router();

const pool = require('../database.js');

router.get('/',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    let listCategoria = await pool.query('SELECT * FROM categoria');
    res.json({
        status:"200",
        message:"Se ha recuperado correctamente",
        listCategoria: listCategoria
    });
});

router.get('/:id',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {id} = req.params;
    let categoria = await pool.query('SELECT * FROM categoria WHERE id=?',[id]);
    res.json({
        status:"200",
        message:"Se ha recuperado correctamente",
        categoria: categoria
    });
});

router.post('/create',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {nombre} = req.body;
    const categoria={nombre};
    await pool.query('INSERT INTO categoria set ?', [categoria]);
    res.json({
        status:"200",
        message:"Se ha registrado correctamente",
        categoria: categoria
    });
});

router.post('/update/:id',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {id} = req.params;
    const {nombre} = req.body;
    const categoria={nombre};
    await pool.query('UPDATE categoria SET ? WHERE id = ?',[categoria,id]);
    res.json({
        status:"200",
        message:"Se ha actualizado correctamente",
        categoria: categoria
    });
});

router.post('/delete/:id',async(req,res)=>{
    res.setHeader('Access-Control-Allow-Origin', '*');
    const {id} = req.params;
    await pool.query('DELETE FROM categoria WHERE id = ?',[id]);
    res.json({
        status:"200",
        message:"Se ha eliminado correctamente"
    });
});

module.exports = router;