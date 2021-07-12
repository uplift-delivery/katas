using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Npgsql;

namespace Advanced.Gilded.Rose
{
    [ApiController]
    [Route("items")]
    public class ItemsController : Controller
    {
        private readonly NpgsqlConnectionStringBuilder _builder;

        public ItemsController()
        {
            _builder = new NpgsqlConnectionStringBuilder();
        }

        [HttpGet]
        public async Task<IActionResult> Get()
        {
            await using var connection = new NpgsqlConnection(_builder.ConnectionString);
            await connection.OpenAsync();
            await using var command = connection.CreateCommand();
            command.CommandText = "select * from items";
            await using var reader = await command.ExecuteReaderAsync();
            var items = new List<Item>();
            while (await reader.NextResultAsync())
            {
                items.Add(new Item(
                    reader.GetInt32(0),
                    reader.GetString(1),
                    reader.GetInt32(2),
                    reader.GetInt32(3),
                    reader.GetInt32(4))
                );
            }

            return Ok(items);
        }

        [HttpPost]
        public async Task<IActionResult> Update()
        {
            await using var connection = new NpgsqlConnection(_builder.ConnectionString);
            await connection.OpenAsync();

            await using var getAllCommand = connection.CreateCommand();
            getAllCommand.CommandText = "select * from items";
            await using var reader = await getAllCommand.ExecuteReaderAsync();
            var items = new List<Item>();
            while (await reader.NextResultAsync())
            {
                items.Add(new Item(
                    reader.GetInt32(0),
                    reader.GetString(1),
                    reader.GetInt32(2),
                    reader.GetInt32(3),
                    reader.GetInt32(4))
                );
            }

            for (var i = 0; i < items.Count; i++)
            {
                if (items[i].Name != "Aged Brie" && items[i].Name != "Backstage passes to a TAFKAL80ETC concert")
                {
                    if (items[i].Quality > 0)
                    {
                        if (items[i].Name != "Sulfuras, Hand of Ragnaros")
                        {
                            items[i].Quality = items[i].Quality - 1;
                            await using var updateCommand = connection.CreateCommand();
                            updateCommand.CommandText = $"update items set quality = {items[i].Quality} where id = {items[i].Id}";
                            await updateCommand.ExecuteNonQueryAsync();
                        }
                    }
                }
                else
                {
                    if (items[i].Quality < 50)
                    {
                        items[i].Quality = items[i].Quality + 1;
                        if (items[i].Name == "Backstage passes to a TAFKAL80ETC concert")
                        {
                            if (items[i].SellIn < 11)
                            {
                                if (items[i].Quality < 50)
                                {
                                    items[i].Quality = items[i].Quality + 1;
                                    await using var updateCommand = connection.CreateCommand();
                                    updateCommand.CommandText = $"update items set quality = {items[i].Quality} where id = {items[i].Id}";
                                    await updateCommand.ExecuteNonQueryAsync();
                                }
                            }

                            if (items[i].SellIn < 6)
                            {
                                if (items[i].Quality < 50)
                                {
                                    items[i].Quality = items[i].Quality + 1;
                                    await using var updateCommand = connection.CreateCommand();
                                    updateCommand.CommandText = $"update items set quality = {items[i].Quality} where id = {items[i].Id}";
                                    await updateCommand.ExecuteNonQueryAsync();
                                }
                            }
                        }
                    }
                }

                if (items[i].Name != "Sulfuras, Hand of Ragnaros")
                {
                    items[i].SellIn = items[i].SellIn - 1;
                    await using var updateCommand = connection.CreateCommand();
                    updateCommand.CommandText = $"update items set sellIn = {items[i].SellIn} where id = {items[i].Id}";
                    await updateCommand.ExecuteNonQueryAsync();
                }

                if (items[i].SellIn < 0)
                {
                    if (items[i].Name != "Aged Brie")
                    {
                        if (items[i].Name != "Backstage passes to a TAFKAL80ETC concert")
                        {
                            if (items[i].Quality > 0)
                            {
                                if (items[i].Name != "Sulfuras, Hand of Ragnaros")
                                {
                                    items[i].Quality = items[i].Quality - 1;
                                    await using var updateCommand = connection.CreateCommand();
                                    updateCommand.CommandText = $"update items set quality = {items[i].Quality} where id = {items[i].Id}";
                                    await updateCommand.ExecuteNonQueryAsync();
                                }
                            }
                        }
                        else
                        {
                            items[i].Quality = items[i].Quality - items[i].Quality;
                            await using var updateCommand = connection.CreateCommand();
                            updateCommand.CommandText = $"update items set quality = {items[i].Quality} where id = {items[i].Id}";
                            await updateCommand.ExecuteNonQueryAsync();
                        }
                    }
                    else
                    {
                        if (items[i].Quality < 50)
                        {
                            items[i].Quality = items[i].Quality + 1;
                            await using var updateCommand = connection.CreateCommand();
                            updateCommand.CommandText = $"update items set quality = {items[i].Quality} where id = {items[i].Id}";
                            await updateCommand.ExecuteNonQueryAsync();
                        }
                    }
                }
            }

            return Ok();
        }
    }
}