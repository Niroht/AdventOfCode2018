using System;

namespace AdventOfCodeCSharp
{
    class Program
    {
        static void Main(string[] args)
        {
            SolveDayOne();

            Console.ReadKey();
        }

        private static void SolveDayOne()
        {
            Console.WriteLine("Day One");

            var dayOneValues = Inputs.DayOne.Split(';');

            Console.WriteLine(FrequencyReader.AddFrequencies(dayOneValues));
            Console.WriteLine(FrequencyReader.FindFirstRepeat(dayOneValues));
        }
    }
}
