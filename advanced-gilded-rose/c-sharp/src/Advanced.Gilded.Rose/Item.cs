namespace Advanced.Gilded.Rose
{
    public record Item(int Id, string Name, int Quantity, int Quality, int SellIn)
    {
        public int Id { get; set; } = Id;
        public string Name { get; set; } = Name;
        public int Quantity { get; set; } = Quantity;
        public int Quality { get; set; } = Quality;
        public int SellIn { get; set; } = SellIn;
    }
}