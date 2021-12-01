import {App} from "./app";
import axios from "axios";

describe('app', () => {
    let restApi;

    beforeAll(async () => {
        await App.start({port: 4000});
        restApi = axios.create({
            baseURL: 'http://localhost:4000'
        })
    });

    test('when getting todos then returns each todo', async () => {
        const response = await restApi.get('/todos');

        expect(response.status).toEqual(200);
    })

    afterAll(async () => {
        await App.stop();
    })
})