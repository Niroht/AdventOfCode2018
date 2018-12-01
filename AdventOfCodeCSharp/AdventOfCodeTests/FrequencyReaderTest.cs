using AdventOfCodeCSharp;
using System;
using Xunit;

namespace AdventOfCodeTests
{
    public class FrequencyReaderTest
    {
        [Fact]
        public void AddFrequencies_AllPositive()
        {
            // arrange

            // act
            var result = FrequencyReader.AddFrequencies(new [] { "+1", "+1", "+1" });

            // assert
            Assert.Equal(3, result);
        }

        [Fact]
        public void AddFrequencies_PositiveAndNegative()
        {
            // arrange

            // act
            var result = FrequencyReader.AddFrequencies(new[] { "+1", "+1", "-2" });

            // assert
            Assert.Equal(0, result);
        }

        [Fact]
        public void AddFrequencies_AllNegative()
        {
            // arrange

            // act
            var result = FrequencyReader.AddFrequencies(new[] { "-1", "-2", "-3" });

            // assert
            Assert.Equal(-6, result);
        }

        [Fact]
        public void FindFirstRepeat_SingleLoop()
        {
            // arrange

            // act
            var result = FrequencyReader.FindFirstRepeat(new[] { "+1", "-1" });

            // assert
            Assert.Equal(0, result);
        }

        [Fact]
        public void FindFirstRepeat_IncrementThenDecrement()
        {
            // arrange

            // act
            var result = FrequencyReader.FindFirstRepeat(new[] { "+3", "+3", "+4", "-2", "-4" });

            // assert
            Assert.Equal(10, result);
        }

        [Fact]
        public void FindFirstRepeat_DecrementIncrementDecrement()
        {
            // arrange

            // act

            var result = FrequencyReader.FindFirstRepeat(new[] { "-6", "+3", "+8", "+5", "-6" });

            // assert
            Assert.Equal(5, result);
        }

        [Fact]
        public void FindFirstRepeat_SeveralDecrements()
        {
            // arrange

            // act
            var result = FrequencyReader.FindFirstRepeat(new[] { "+7", "+7", "-2", "-7", "-4" });

            // assert
            Assert.Equal(14, result);
        }
    }
}
