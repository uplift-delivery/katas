import express from 'express';
import cors from 'cors';
import {json} from 'body-parser';
import {Pool} from 'pg';
import config from './config.json';

const db = new Pool({
    user: config.db.user,
    host: config.db.host,
    database: config.db.database,
    password: config.db.password,
    port: config.db.port
});
const app = express();
app.use(cors());
app.use(json());
app.get('/', async (req, res) => {
    try {
        const client = await db.connect();
        try {
            const result = await client.query('select * from items');
            res.json(result.rows)
                .status(200)
                .end();
        } finally {
            client.release();
        }
    } catch (e) {
        res.status(500)
            .json(e)
            .end();
    }


});

app.post('/', async (req, res) => {
    const client = await db.connect();
    try {
        const result = await client.query('select * from items');
        for (let i = 0; i < result.rows.length; i++) {
            if (result.rows[i].name != 'Aged Brie' && result.rows[i].name != 'Backstage passes to a TAFKAL80ETC concert') {
                if (result.rows[i].quality > 0) {
                    if (result.rows[i].name != 'Sulfuras, Hand of Ragnaros') {
                        result.rows[i].quality = result.rows[i].quality - 1;
                        await client.query('update items set quality = $1 where id = $3', [result.rows[i].quality, result.rows[i].id]);
                    }
                }
            } else {
                if (result.rows[i].quality < 50) {
                    result.rows[i].quality = result.rows[i].quality + 1;
                    if (result.rows[i].name == 'Backstage passes to a TAFKAL80ETC concert') {
                        if (result.rows[i].sell_in < 11) {
                            if (result.rows[i].quality < 50) {
                                result.rows[i].quality = result.rows[i].quality + 1;
                                await client.query('update items set quality = $1 where id = $3', [result.rows[i].quality, result.rows[i].id]);
                            }
                        }
                        if (result.rows[i].sell_in < 6) {
                            if (result.rows[i].quality < 50) {
                                result.rows[i].quality = result.rows[i].quality + 1;
                                await client.query('update items set quality = $1 where id = $3', [result.rows[i].quality, result.rows[i].id]);
                            }
                        }
                    }
                }
            }
            if (result.rows[i].name != 'Sulfuras, Hand of Ragnaros') {
                result.rows[i].sell_in = result.rows[i].sell_in - 1;
                await client.query('update items set sellIn = $1 where id = $3', [result.rows[i].sellIn, result.rows[i].id]);
            }
            if (result.rows[i].sell_in < 0) {
                if (result.rows[i].name != 'Aged Brie') {
                    if (result.rows[i].name != 'Backstage passes to a TAFKAL80ETC concert') {
                        if (result.rows[i].quality > 0) {
                            if (result.rows[i].name != 'Sulfuras, Hand of Ragnaros') {
                                result.rows[i].quality = result.rows[i].quality - 1;
                                await client.query('update items set quality = $1 where id = $3', [result.rows[i].quality, result.rows[i].id]);
                            }
                        }
                    } else {
                        result.rows[i].quality = result.rows[i].quality - result.rows[i].quality;
                        await client.query('update items set quality = $1 where id = $3', [result.rows[i].quality, result.rows[i].id]);
                    }
                } else {
                    if (result.rows[i].quality < 50) {
                        result.rows[i].quality = result.rows[i].quality + 1;
                        await client.query('update items set quality = $1 where id = $3', [result.rows[i].quality, result.rows[i].id]);
                    }
                }
            }
        }
    } finally {
        client.release();
    }
});

app.listen(process.env.PORT, () => {
    console.log(`Listening at http://localhost:${process.env.PORT}`);
})
