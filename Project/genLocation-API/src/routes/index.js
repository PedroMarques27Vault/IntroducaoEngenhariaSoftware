const express = require('express');
const router = express.Router();
const randomLocation = require('random-location')

//Index
router.get('/', function (req, res) {
    res.status(200).send({
        title: "Gen Location API",
        description:"Generates a random lat and long inside a circle given a center",
        use: "/gen-location/lat,long"
    });
});
//Gen Location
router.get('/gen-location/:lat,:long', function (req, res) {
    var centerPoint = {"latitude":req.params.lat, "longitude": req.params.long};
    res.status(200).send({
        title: "Random Location",  
        center: centerPoint["latitude"]+","+centerPoint["longitude"],  
        location: randomLocation.randomCirclePoint(centerPoint,200000) 
        //Just for tests alter later
        //Radius is big so we can see some changes on the lat and long
    });
});
module.exports = router;