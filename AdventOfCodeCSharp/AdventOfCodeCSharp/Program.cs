using System;

namespace AdventOfCodeCSharp
{
    class Program
    {
        static void Main(string[] args)
        {
            SolveDayOne();
            SolveDayTwo();

            Console.ReadKey();
        }

        private static void SolveDayOne()
        {
            Console.WriteLine("Day One");

            var dayOneValues = Inputs.DayOne.Split(';');

            Console.WriteLine(FrequencyReader.AddFrequencies(dayOneValues));

            // Console.WriteLine(FrequencyReader.FindFirstRepeat(dayOneValues));
            Console.WriteLine("Part Two Disabled Due To Performance");
        }

        private static void SolveDayTwo()
        {
            Console.WriteLine("Day Two");

            var dayTwoValues = Inputs.DayTwo.Split(';');

            Console.WriteLine(IDReader.FindChecksum(dayTwoValues));
            Console.WriteLine(IDReader.FindDifferingCharacters(dayTwoValues));
        }
    }
}
