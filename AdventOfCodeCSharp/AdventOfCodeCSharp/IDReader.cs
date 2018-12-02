using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AdventOfCodeCSharp
{
    public static class IDReader
    {
        public static int FindChecksum(IEnumerable<string> ids)
        {
            var containsExactlyTwoCount = 0;
            var containsExactlyThreeCount = 0;

            foreach(var id in ids)
            {
                var foundCharacters = new Dictionary<char, int>();

                foreach(var character in id)
                {
                    if (foundCharacters.ContainsKey(character))
                    {
                        foundCharacters[character]++;
                    }
                    else
                    {
                        foundCharacters.Add(character, 1);
                    }
                }

                if (foundCharacters.Any(x => x.Value == 2))
                {
                    containsExactlyTwoCount++;
                }
                if (foundCharacters.Any(x => x.Value == 3))
                {
                    containsExactlyThreeCount++;
                }
            }

            return containsExactlyTwoCount * containsExactlyThreeCount;
        }

        public static string FindDifferingCharacters(IEnumerable<string> ids)
        {
            var parsedStrings = new List<string>();

            foreach(var id in ids)
            {
                var mismatchedByOne = "";
                mismatchedByOne = FindIdThatMismatchesByOne(parsedStrings, id, mismatchedByOne);
                if (!string.IsNullOrWhiteSpace(mismatchedByOne))
                {
                    var output = new StringBuilder();

                    for (var i = 0; i < id.Length; i++)
                    {
                        if (id[i] == mismatchedByOne[i])
                        {
                            output.Append(id[i]);
                        }
                    }
                    return output.ToString();
                    
                }

                parsedStrings.Add(id);
            }

            return "";
        }

        private static string FindIdThatMismatchesByOne(List<string> parsedStrings, string id, string mismatchedByOne)
        {
            foreach (var parsedString in parsedStrings)
            {
                var mismatchCount = 0;

                if (id.Length != parsedString.Length)
                {
                    continue;
                }

                for (var i = 0; i < id.Length; i++)
                {
                    if (id[i] != parsedString[i])
                    {
                        mismatchCount++;
                    }
                }

                if (mismatchCount == 1)
                {
                    mismatchedByOne = parsedString;
                }
            }

            return mismatchedByOne;
        }
    }
}
