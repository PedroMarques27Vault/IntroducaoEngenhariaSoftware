const express = require('express');
const router = express.Router();
const randomLocation = require('random-location');
router.get('/gen-location', function (req, res, next) {
    res.status(200).send({
        lat: "Location",
        long: "0.0.1"
    });
});
module.exports = router;