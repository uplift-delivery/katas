namespace Advanced.Gilded.Rose
{
    public record Item(int Id, string Name, int Quality, int SellIn)
    {
        public int Id { get; set; } = Id;
        public string Name { get; set; } = Name;
        public int Quality { get; set; } = Quality;
        public int SellIn { get; set; } = SellIn;
    }
}