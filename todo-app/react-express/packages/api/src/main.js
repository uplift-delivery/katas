import {App} from './app';

const port = process.env.PORT;
App.start({port})
    .catch(err => {
        console.log('Failed to start server', err);
        process.exit(1);
    })