//Vars
const express = require('express');     
const app = express();
//JS Files
const index = require('./routes/index');
//Rotas
app.get('/', index);
app.get('/gen-location/:lat,:long', index)
//Export
module.exports = app;