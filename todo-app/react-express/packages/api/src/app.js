import express from 'express';
import bodyParser from "body-parser";
import winston from 'winston';
import expressWinston from 'express-winston';

const app = express();
app.use(expressWinston.logger({
    transports: [
        new winston.transports.Console()
    ],
    format: winston.format.combine(
        winston.format.colorize(),
        winston.format.simple(),
        winston.format.timestamp()
    ),
    expressFormat: true
}));
app.use(bodyParser.json());
let server = null;

function start({port = 3333}) {
    return new Promise((resolve, reject) => {
        server = app.listen(port, () => {
            console.log(`Now listening at http://localhost:${port}...`);
            resolve();
        });
    })
}

function stop() {
    return new Promise((resolve, reject) => {
        if (server) {
            server.close();
        }
        resolve();
    });
}

export const App = {start, stop};