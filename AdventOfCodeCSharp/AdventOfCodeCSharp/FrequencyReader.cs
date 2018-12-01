using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AdventOfCodeCSharp
{
    public static class FrequencyReader
    {
        public static int AddFrequencies(IEnumerable<String> frequencies)
        {
            var result = 0;

            foreach(var frequency in frequencies)
            {
                if (String.IsNullOrWhiteSpace(frequency))
                {
                    continue;
                }

                var value = int.Parse(frequency.Substring(1).Trim());

                var movement = frequency[0] == '-' ? -value : value;

                result += movement;
            }

            return result;
        }

        public static int FindFirstRepeat(IEnumerable<String> frequencies)
        {
            var index = 0;
            var currentFrequency = 0;

            var foundResults = new List<int> { currentFrequency };

            while (true)
            {
                var frequency = frequencies.ElementAt(index);
                if (String.IsNullOrWhiteSpace(frequency))
                {
                    index = index == frequencies.Count() - 1 ? 0 : index + 1;
                    continue;
                }

                var value = int.Parse(frequency.Substring(1).Trim());

                var movement = frequency[0] == '-' ? -value : value;

                currentFrequency += movement;
                
                if (foundResults.Contains(currentFrequency))
                {
                    return currentFrequency;
                }
                else
                {
                    foundResults.Add(currentFrequency);
                }

                index = index == frequencies.Count() - 1 ? 0 : index + 1;
            }
        }
    }
}
