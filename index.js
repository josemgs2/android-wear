const { response } = require('express')
const express = require('express')
const path = require('path')
const PORT = process.env.PORT || 5000
var bodyParser = require('body-parser')
const tokenDevicesURI = "registrar-usuario";
const likeURI = "send-like";
var admin = require("firebase-admin");

var serviceAccount = require("./petagram-dcp-firebase-adminsdk-x5wvg-0c8e49e87c.json");
admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: "https://petagram-dcp-default-rtdb.europe-west1.firebasedatabase.app"
});

var FCM = require('fcm-push');


function generarRespuestaAToken(db, idAutoGenerado, idIG) {
    var respuesta = {};
    var ref = db.ref("token-device");
    ref.startAt(null, idAutoGenerado).on("child_added", function(snapshot, prevChildKey) {
        const usuario = snapshot.val();
        respuesta = {
            id: idAutoGenerado,
            tokenFCM: usuario.tokenFCM,
            idIG: idIG
        };
    })



    return respuesta;
}


var enviarNotificacion = function(tokenDestinatario, mensaje) {
    var serverKey = 'AAAAjr_rA14:APA91bEGYucmbxkdAXZF4F2VZBAyZeQtVMvant9uPTP_d5vdcYFnzi41KYZjLeMYic244WhoX0lgBhV74wWjTj2NJ5NE275bm0OI6LhahqfpTpEhcmpWC68cFJPHtqI03qEfLmINabz3';
    var fcm = new FCM(serverKey);
    var message = {
        to: tokenDestinatario, // required fill with device token or topics
        collapse_key: '',
        data: {},
        notification: {
            title: 'Notificación desde el servidor',
            body: mensaje
        }
    };

    //callback style
    fcm.send(message, function(err, response) {
        if (err) {
            console.log("Something has gone wrong!");
        } else {
            console.log("Successfully sent with response: ", response);
        }
    });
}
express()
    .use(express.static(path.join(__dirname, 'public')))
    .use(bodyParser.json())
    .use(bodyParser.urlencoded({ extended: true }))
    .set('views', path.join(__dirname, 'views'))
    .set('view engine', 'ejs')
    .get('/', (req, res) => res.render('pages/index'))
    .post("/" + likeURI, (req, res) => {
        console.log("entra likes");
        var pTokenFCM = req.body.tokenFCM
        var pIdIG = req.body.idIG;
        var pNombreMascota = req.body.nombreMascota;
        var pLikes = req.body.likes;

        enviarNotificacion(pTokenFCM, "Tu mascota " + pNombreMascota + " lleva ya " + pLikes + " likes.");
        console.log("manda 200");
        res.status(200).send('OK');
    })
    .post("/" + tokenDevicesURI, (req, res) => {
        console.log("entra registro");
        var pTokenFCM = req.body.tokenFCM;
        var pIdIG = req.body.idIG;

        //POST
        var db = admin.database();
        var tokenDevices = db.ref("token-device").push();
        tokenDevices.set({ tokenFCM: pTokenFCM, idIG: pIdIG });

        //Enviar notificacion
        enviarNotificacion(pTokenFCM, "mensajito enviado");
        //Recupero el registro generado
        var path = tokenDevices.toString();
        var pathSplit = path.split("token-device/");
        var idAutoGenerado = pathSplit[1];
        var respuesta = generarRespuestaAToken(db, idAutoGenerado, pIdIG);



        //RESPONSE
        res.setHeader("Content-Type", "application/json");
        res.send(JSON.stringify(respuesta));
    })
    .listen(PORT, () => console.log(`Listening on ${ PORT }`))