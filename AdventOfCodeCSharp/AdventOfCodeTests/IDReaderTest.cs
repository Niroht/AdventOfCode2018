using AdventOfCodeCSharp;
using System;
using System.Collections.Generic;
using System.Text;
using Xunit;

namespace AdventOfCodeTests
{
    public class IDReaderTest
    {
        [Fact]
        public void FindChecksum_SimpleInput()
        {
            // arrange
            var input = new[]
            {
                "abcdef",
                "bababc",
                "abbcde",
                "abcccd",
                "aabcdd",
                "abcdee",
                "ababab"
            };

            // act
            var result = IDReader.FindChecksum(input);

            // assert
            Assert.Equal(12, result);
        }

        [Fact]
        public void FindDifferingCharacters_SimpleInput()
        {
            // arrange
            var input = new[]
            {
                "abcde",
                "fghij",
                "klmno",
                "pqrst",
                "fguij",
                "axcye",
                "wvxyz"
            };

            // act
            var result = IDReader.FindDifferingCharacters(input);

            // assert
            Assert.Equal("fgij", result);
        }
    }
}
